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
        //Sempre que adicionarmos um novo tópico, o relatório muda o status para NÃO VISUALIZADO
        if(relatorio.getStatus().equals(StatusRelatorio.VISUALIZADO)){
            relatorio.setStatus(StatusRelatorio.NAO_VISUALIZADO);
        }
        topico.setData(OffsetDateTime.now());
        topico.setRelatorio(relatorio);
        return topicoRepository.save(topico);
    }

}
