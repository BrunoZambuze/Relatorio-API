package com.relatorioSpringRest.relatorioApi.api.representationmodel.output;

import com.relatorioSpringRest.relatorioApi.domain.model.Enum.StatusRelatorio;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
public class RelatorioDtoOutput {

    private Long id;
    private String titulo;
    private StatusRelatorio status;
    private OffsetDateTime data;
    private List<TopicoDtoOutput> topicos;

}
