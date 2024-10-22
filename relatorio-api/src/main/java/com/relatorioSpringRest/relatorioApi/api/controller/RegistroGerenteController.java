package com.relatorioSpringRest.relatorioApi.api.controller;

import com.relatorioSpringRest.relatorioApi.api.assembler.FuncionarioAssembler;
import com.relatorioSpringRest.relatorioApi.api.assembler.RelatorioAssembler;
import com.relatorioSpringRest.relatorioApi.api.assembler.RelatorioGerenteAssembler;
import com.relatorioSpringRest.relatorioApi.api.assembler.UsuarioAssembler;
import com.relatorioSpringRest.relatorioApi.api.representationmodel.input.FuncionaroRepresentation;
import com.relatorioSpringRest.relatorioApi.api.representationmodel.input.UsuarioDtoInput;
import com.relatorioSpringRest.relatorioApi.api.representationmodel.output.RelatorioDtoOutput;
import com.relatorioSpringRest.relatorioApi.api.representationmodel.output.RelatorioGerenteRepresentationOutput;
import com.relatorioSpringRest.relatorioApi.api.representationmodel.output.UsuarioDtoOutput;
import com.relatorioSpringRest.relatorioApi.domain.model.Relatorio;
import com.relatorioSpringRest.relatorioApi.domain.model.Usuario;
import com.relatorioSpringRest.relatorioApi.domain.service.GerenteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios/gerente")
@AllArgsConstructor
public class RegistroGerenteController {

    private final GerenteService gerenteService;
    private final UsuarioAssembler usuarioAssembler;
    private final FuncionarioAssembler funcionarioAssembler;
    private final RelatorioGerenteAssembler relatorioGerenteAssembler;
    private final RelatorioAssembler relatorioAssembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDtoOutput adicionarGerente(@Valid @RequestBody UsuarioDtoInput usuarioInput){
        Usuario novoUsuario = usuarioAssembler.toEntity(usuarioInput);
        return usuarioAssembler.toUsuarioOutput(gerenteService.inserirGerente(novoUsuario));
    }

    @GetMapping
    public List<UsuarioDtoOutput> listarGerente(){
        List<Usuario> listaUsuarios = gerenteService.listarGerente();
        return usuarioAssembler.toCollectionOutput(listaUsuarios);
    }

    @PostMapping("/{gerenteId}/funcionarios")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDtoOutput adicionarFuncionario(@PathVariable Long gerenteId,
                                                 @Valid @RequestBody FuncionaroRepresentation funcionarioInput){
        Usuario novoUsuario = funcionarioAssembler.toEntity(funcionarioInput);
        UsuarioDtoOutput usuarioOutput = usuarioAssembler.toUsuarioOutput(gerenteService.inserirFuncinario(gerenteId, novoUsuario));
        return usuarioOutput;
    }

    @GetMapping("/relatorios")
    public List<RelatorioGerenteRepresentationOutput> listarTodosRelatorios(){
        List<Usuario> listaFuncionarios = gerenteService.listarTodosRelatoriosDosFuncionarios();
        return relatorioGerenteAssembler.toCollectionRelatorioGerenteRepresentationOutput(listaFuncionarios);
    }

    @GetMapping("/relatorios/{relatorioId}")
    public RelatorioDtoOutput listarTopicosRelatorio(@PathVariable Long relatorioId){
        return relatorioAssembler.toRelatorioOutput(gerenteService.listarTodosOsTopicosDoRelatorio(relatorioId));
    }

}
