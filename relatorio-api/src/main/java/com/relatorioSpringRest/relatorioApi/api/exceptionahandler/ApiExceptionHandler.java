package com.relatorioSpringRest.relatorioApi.api.exceptionahandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.relatorioSpringRest.relatorioApi.domain.exception.RecursoNaoEncontradoException;
import com.relatorioSpringRest.relatorioApi.domain.exception.RegraDeNegocioException;
import lombok.AllArgsConstructor;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@AllArgsConstructor
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatusCode status, WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(status);
        problemDetail.setTitle("Um ou mais campos estão inválidos");
        Map<String, String> fields = ex.getBindingResult()
                                       .getAllErrors()
                                       .stream()
                                       .collect(Collectors.toMap(objectError -> ((FieldError)objectError).getField(),
                                                                 objectError -> messageSource.getMessage(objectError, LocaleContextHolder.getLocale())));
        problemDetail.setProperty("invalidFields", fields);
        return handleExceptionInternal(ex, problemDetail, headers, status, request);
    }

    @ExceptionHandler(RegraDeNegocioException.class)
    public ProblemDetail handleRegraDeNegocio(RegraDeNegocioException e){
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle(e.getMessage());
        return problemDetail;
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ProblemDetail handleRecursoNaoEncontrado(RecursoNaoEncontradoException e){
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle(e.getMessage());
        return problemDetail;
    }

//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public ProblemDetail handleHttpMessageNotReadable(HttpMessageNotReadableException e){
//        Throwable causa = e.getCause();
//        if(causa instanceof InvalidFormatException){
//            InvalidFormatException invalidFormatException = (InvalidFormatException) causa;
//            String fieldName = invalidFormatException.getPath()
//                                                     .stream()
//                                                     .map(reference -> reference.getFieldName())
//                                                     .collect(Collectors.joining("."));
//            if("tipoUsuario".equals(fieldName)){
//                ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
//                problemDetail.setTitle("Tipo do usuário inválido! Por favor preencha o campo com o valor correto (GERENTE ou FUNCIONARIO");
//                return problemDetail;
//            }
//        }
//        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
//        problemDetail.setTitle("A requisição não pode ser lida!");
//        return problemDetail;
//    }

}
