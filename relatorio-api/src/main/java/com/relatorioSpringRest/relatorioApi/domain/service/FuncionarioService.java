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
        if(!isFuncinario(funcionarioId)){
            throw new RegraDeNegocioException("Usuário encontrado pelo id " + funcionarioId + " não é um funcionário!");
        }
        Usuario funcioario = usuarioRepository.findById(funcionarioId).get();
        Relatorio relatorioNovo = relatorioService.adicionarRelatorio(funcioario, relatorio);
        funcioario.getRelatorios().add(relatorioNovo);
        return relatorioNovo;
    }

    @Transactional
    public List<Relatorio> listarRelatorios(Long funcionarioId){
        if(!isFuncinario(funcionarioId)){
            throw new RegraDeNegocioException("Usuário encontrado pelo id " + funcionarioId + " não é um funcionário!");
        }
        Usuario funcionario = usuarioRepository.findById(funcionarioId).get();
        return funcionario.getRelatorios();
    }

    @Transactional
    public Topico adicionarTopico(Long funcionarioId,
                                  Long relatorioId,
                                  Topico topico){
        if(!isFuncinario(funcionarioId)){
            throw new RegraDeNegocioException("Usuário encontrado pelo id " + funcionarioId + " não é um funcionário!");
        }
        Relatorio relatorio = relatorioService.relatorioExiste(relatorioId);
        Topico topicoNovo = topicoService.adicionarTopico(relatorio, topico);
        relatorio.getTopicos().add(topicoNovo);
        return topicoNovo;
    }

    private boolean isFuncinario(Long funcionarioId){
        return usuarioRepository.findById(funcionarioId)
                                .orElseThrow(() -> new RecursoNaoEncontradoException("Funcionário com id " + funcionarioId + " não encontrado!"))
                                .getTipoUsuario()
                                .equals(TipoUsuario.FUNCIONARIO);
    }

}
