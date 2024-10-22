package com.relatorioSpringRest.relatorioApi.domain.service;

import com.relatorioSpringRest.relatorioApi.domain.model.Enum.StatusRelatorio;
import com.relatorioSpringRest.relatorioApi.domain.model.Relatorio;
import com.relatorioSpringRest.relatorioApi.domain.model.Topico;
import com.relatorioSpringRest.relatorioApi.domain.repository.TopicoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class TopicoService {

    private final TopicoRepository topicoRepository;

    @Transactional
    public Topico adicionarTopico(Relatorio relatorio,
                                     Topico topico){
        Topico novoTopico = relatorio.adicionarTopico(topico);
        return topicoRepository.save(novoTopico);
    }

}
