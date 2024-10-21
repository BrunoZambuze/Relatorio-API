package com.relatorioSpringRest.relatorioApi.domain.repository;

import com.relatorioSpringRest.relatorioApi.domain.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
}
