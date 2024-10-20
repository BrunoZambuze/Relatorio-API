package com.relatorioSpringRest.relatorioApi.domain.repository;

import com.relatorioSpringRest.relatorioApi.domain.model.Relatorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelatorioRepository extends JpaRepository<Relatorio, Long> {
}
