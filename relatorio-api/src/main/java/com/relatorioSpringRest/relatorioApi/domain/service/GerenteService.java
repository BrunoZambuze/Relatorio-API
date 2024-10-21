package com.relatorioSpringRest.relatorioApi.domain.service;

import com.relatorioSpringRest.relatorioApi.domain.exception.RecursoNaoEncontradoException;
import com.relatorioSpringRest.relatorioApi.domain.exception.RegraDeNegocioException;
import com.relatorioSpringRest.relatorioApi.domain.model.Enum.TipoUsuario;
import com.relatorioSpringRest.relatorioApi.domain.model.Usuario;
import com.relatorioSpringRest.relatorioApi.domain.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class GerenteService {

    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario inserirGerente(Usuario usuario) throws RegraDeNegocioException{
        if(isGerente(usuario)){
            throw new RegraDeNegocioException("O usuário desejado não é um gerente!");
        }
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario inserirFuncinario(Long gerenteId, Usuario usuario){
        existeGerente(gerenteId);
        usuario.setTipoUsuario(TipoUsuario.FUNCIONARIO);
        return usuarioRepository.save(usuario);
    }

    private boolean isGerente(Usuario usuario){
        return usuario.getTipoUsuario().equals(TipoUsuario.GERENTE);
    }

    private boolean existeGerente(Long gerenteId){
        return usuarioRepository.findById(gerenteId)
                                .orElseThrow(() -> new RecursoNaoEncontradoException("Gerente de id '" + gerenteId + "' não encontrado!"))
                                .getTipoUsuario()
                                .equals(TipoUsuario.GERENTE);
    }

    @Transactional
    public List<Usuario> listarGerente(){
        return usuarioRepository.findAll()
                                .stream()
                                .filter(user -> user.getTipoUsuario().equals(TipoUsuario.GERENTE))
                                .toList();
    }

}
