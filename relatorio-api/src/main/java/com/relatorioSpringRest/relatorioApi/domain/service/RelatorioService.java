package com.relatorioSpringRest.relatorioApi.domain.service;

import com.relatorioSpringRest.relatorioApi.domain.exception.RecursoNaoEncontradoException;
import com.relatorioSpringRest.relatorioApi.domain.model.Enum.StatusRelatorio;
import com.relatorioSpringRest.relatorioApi.domain.model.Relatorio;
import com.relatorioSpringRest.relatorioApi.domain.model.Usuario;
import com.relatorioSpringRest.relatorioApi.domain.repository.RelatorioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class RelatorioService {

    private final RelatorioRepository relatorioRepository;

    @Transactional
    public Relatorio adicionarRelatorio(Usuario usuario, Relatorio relatorio){
        relatorio.setStatus(StatusRelatorio.NAO_VISUALIZADO);
        relatorio.setData(OffsetDateTime.now());
        relatorio.setUsuario(usuario);
        return relatorioRepository.save(relatorio);
    }

    public Relatorio relatorioExiste(Long relatorioId){
        return relatorioRepository.findById(relatorioId)
                                  .orElseThrow(() -> new RecursoNaoEncontradoException("Relatório de id " + relatorioId + " não encontrado!"));
    }

}
