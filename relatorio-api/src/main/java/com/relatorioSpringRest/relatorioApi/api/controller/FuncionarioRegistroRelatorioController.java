package com.relatorioSpringRest.relatorioApi.api.controller;

import com.relatorioSpringRest.relatorioApi.api.assembler.RelatorioAssembler;
import com.relatorioSpringRest.relatorioApi.api.assembler.TopicoAssembler;
import com.relatorioSpringRest.relatorioApi.api.assembler.UsuarioAssembler;
import com.relatorioSpringRest.relatorioApi.api.representationmodel.input.RelatorioDtoInput;
import com.relatorioSpringRest.relatorioApi.api.representationmodel.input.TopicoDtoInput;
import com.relatorioSpringRest.relatorioApi.api.representationmodel.output.RelatorioDtoOutput;
import com.relatorioSpringRest.relatorioApi.api.representationmodel.output.TopicoDtoOutput;
import com.relatorioSpringRest.relatorioApi.api.representationmodel.output.UsuarioDtoOutput;
import com.relatorioSpringRest.relatorioApi.domain.model.Relatorio;
import com.relatorioSpringRest.relatorioApi.domain.model.Topico;
import com.relatorioSpringRest.relatorioApi.domain.model.Usuario;
import com.relatorioSpringRest.relatorioApi.domain.service.FuncionarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/usuarios/funcionarios")
public class FuncionarioRegistroRelatorioController {

    private final FuncionarioService funcionarioService;
    private final UsuarioAssembler usuarioAssembler;
    private final RelatorioAssembler relatorioAssembler;
    private final TopicoAssembler topicoAssembler;

    @GetMapping
    public List<UsuarioDtoOutput> listarFuncionarios(){
        List<Usuario> listaUsuarios = funcionarioService.listarFuncionarios();
        return usuarioAssembler.toCollectionOutput(listaUsuarios);
    }

    @PostMapping("/{funcionarioId}/relatorios")
    @ResponseStatus(HttpStatus.CREATED)
    public RelatorioDtoOutput inserirRelatorio(@PathVariable Long funcionarioId,
                                               @RequestBody @Valid RelatorioDtoInput relatorioInput){

        Relatorio relatorio = relatorioAssembler.toEntity(relatorioInput);
        return relatorioAssembler.toRelatorioOutput(funcionarioService.adicionarRelatorio(funcionarioId, relatorio));
    }

    @GetMapping("/{funcionarioId}/relatorios")
    public List<RelatorioDtoOutput> listarRelatorios(@PathVariable Long funcionarioId){
        List<Relatorio> listaRelatorios = funcionarioService.listarRelatorios(funcionarioId);
        return relatorioAssembler.toCollectionRelatorioOutput(listaRelatorios);
    }

    @PostMapping("/{funcionarioId}/relatorios/{relatorioId}")
    public TopicoDtoOutput inserirTopico(@PathVariable Long funcionarioId,
                                         @PathVariable Long relatorioId,
                                         @RequestBody @Valid TopicoDtoInput topicoInput){
        Topico topico = topicoAssembler.toEntity(topicoInput);
        Topico novoTopico = funcionarioService.adicionarTopico(funcionarioId, relatorioId, topico);
        return topicoAssembler.toTopicoOutput(novoTopico);
    }

}
