package com.relatorioSpringRest.relatorioApi.api.controller;

import com.relatorioSpringRest.relatorioApi.api.assembler.FuncionarioAssembler;
import com.relatorioSpringRest.relatorioApi.api.assembler.UsuarioAssembler;
import com.relatorioSpringRest.relatorioApi.api.representationmodel.input.FuncionaroRepresentation;
import com.relatorioSpringRest.relatorioApi.api.representationmodel.input.UsuarioDtoInput;
import com.relatorioSpringRest.relatorioApi.api.representationmodel.output.UsuarioDtoOutput;
import com.relatorioSpringRest.relatorioApi.domain.model.Usuario;
import com.relatorioSpringRest.relatorioApi.domain.service.GerenteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios/gerente")
@AllArgsConstructor
public class RegistroGerenteController {

    private final GerenteService gerenteService;
    private final UsuarioAssembler usuarioAssembler;
    private final FuncionarioAssembler funcionarioAssembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDtoOutput adicionar(@Valid @RequestBody UsuarioDtoInput usuarioInput){
        Usuario novoUsuario = usuarioAssembler.toEntity(usuarioInput);
        return usuarioAssembler.toUsuarioOutput(gerenteService.inserirGerente(novoUsuario));
    }

    @PostMapping("/{gerenteId}/funcionarios")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDtoOutput adicionarFuncionario(@PathVariable Long gerenteId,
                                                 @Valid @RequestBody FuncionaroRepresentation funcionarioInput){
        Usuario novoUsuario = funcionarioAssembler.toEntity(funcionarioInput);
        UsuarioDtoOutput usuarioOutput = usuarioAssembler.toUsuarioOutput(gerenteService.inserirFuncinario(gerenteId, novoUsuario));
        return usuarioOutput;
    }

}
