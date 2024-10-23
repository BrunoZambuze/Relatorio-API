package com.relatorioSpringRest.relatorioApi.domain.service;

import com.relatorioSpringRest.relatorioApi.domain.exception.RecursoNaoEncontradoException;
import com.relatorioSpringRest.relatorioApi.domain.exception.RegraDeNegocioException;
import com.relatorioSpringRest.relatorioApi.domain.model.Enum.TipoUsuario;
import com.relatorioSpringRest.relatorioApi.domain.model.Relatorio;
import com.relatorioSpringRest.relatorioApi.domain.model.Topico;
import com.relatorioSpringRest.relatorioApi.domain.model.Usuario;
import com.relatorioSpringRest.relatorioApi.domain.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class FuncionarioService {

    private final UsuarioRepository usuarioRepository;
    private final RelatorioService relatorioService;
    private final TopicoService topicoService;

    @Transactional
    public List<Usuario> listarFuncionarios(){
        return usuarioRepository.findAll()
                                .stream()
                                .filter(user -> user.getTipoUsuario().equals(TipoUsuario.FUNCIONARIO))
                                .toList();
    }

    @Transactional
    public Relatorio adicionarRelatorio(Long funcionarioId, Relatorio relatorio){
        funcionarioNaoEncontrado(funcionarioId);
        Usuario funcioario = usuarioRepository.findById(funcionarioId).get();
        Relatorio relatorioNovo = relatorioService.adicionarRelatorio(funcioario, relatorio);
        funcioario.getRelatorios().add(relatorioNovo);
        return relatorioNovo;
    }

    @Transactional
    public List<Relatorio> listarRelatorios(Long funcionarioId){
        funcionarioNaoEncontrado(funcionarioId);
        Usuario funcionario = usuarioRepository.findById(funcionarioId).get();
        return funcionario.getRelatorios();
    }

    @Transactional
    public Topico adicionarTopico(Long funcionarioId,
                                  Long relatorioId,
                                  Topico topico){
        funcionarioNaoEncontrado(funcionarioId);
        Relatorio relatorio = relatorioService.relatorioExiste(relatorioId);
        funcionarioNaoPossuiRelatorioComEsseId(funcionarioId, relatorioId);
        Topico topicoNovo = topicoService.adicionarTopico(relatorio, topico);
        return topicoNovo;
    }

    @Transactional
    public Topico atualizarTopicoDoRelatorio(Long funcionarioId,
                                                Long relatorioId,
                                                Long topicoId,
                                                Topico topico){
        funcionarioNaoEncontrado(funcionarioId);
        funcionarioNaoPossuiRelatorioComEsseId(funcionarioId, relatorioId);
        Relatorio relatorioEncontrado = relatorioService.relatorioExiste(relatorioId);
        return topicoService.atualizarTopico(topicoId, topico, relatorioEncontrado);
    }

    private boolean isFuncinario(Long funcionarioId){
        return usuarioRepository.findById(funcionarioId)
                                .orElseThrow(() -> new RecursoNaoEncontradoException("Funcionário com id " + funcionarioId + " não encontrado!"))
                                .getTipoUsuario()
                                .equals(TipoUsuario.FUNCIONARIO);
    }

    private void funcionarioNaoEncontrado(Long funcionarioId){
        if(!isFuncinario(funcionarioId)){
            throw new RegraDeNegocioException("Usuário encontrado pelo id " + funcionarioId + " não é um funcionário!");
        }
    }

    private boolean verificaSeFuncionarioTemRelatorioComEsseId(Long funcionarioId, Long relatorioId){
        Usuario funcionario = usuarioRepository.findById(funcionarioId).get();
        Relatorio relatorio = relatorioService.relatorioExiste(relatorioId);
        return funcionario.getRelatorios()
                .stream()
                .anyMatch(rel -> rel.equals(relatorio));
    }

    private void funcionarioNaoPossuiRelatorioComEsseId(Long funcionarioId, Long relatorioId){
        if(!verificaSeFuncionarioTemRelatorioComEsseId(funcionarioId, relatorioId)){
            throw new RegraDeNegocioException("Funcionário não possui o relatório com id " + relatorioId + " especificado. Favor corrigir");
        }
    }

}
