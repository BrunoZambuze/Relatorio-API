package com.relatorioSpringRest.relatorioApi.domain.repository;

import com.relatorioSpringRest.relatorioApi.domain.model.Relatorio;
import com.relatorioSpringRest.relatorioApi.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
