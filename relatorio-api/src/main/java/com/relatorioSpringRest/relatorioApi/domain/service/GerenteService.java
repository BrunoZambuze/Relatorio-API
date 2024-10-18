package com.relatorioSpringRest.relatorioApi.domain.service;

import com.relatorioSpringRest.relatorioApi.domain.exception.RegraDeNegocioException;
import com.relatorioSpringRest.relatorioApi.domain.model.Enum.TipoUsuario;
import com.relatorioSpringRest.relatorioApi.domain.model.Usuario;
import com.relatorioSpringRest.relatorioApi.domain.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class GerenteService {

    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario inserirGerente(Usuario usuario){
        if(isGerente(usuario)){
            throw new RegraDeNegocioException("O usuário desejado não é um gerente!");
        }
        return usuarioRepository.save(usuario);
    }

    private boolean isGerente(Usuario usuario){
        return !usuario.getTipoUsuario().equals(TipoUsuario.GERENTE);
    }

}
