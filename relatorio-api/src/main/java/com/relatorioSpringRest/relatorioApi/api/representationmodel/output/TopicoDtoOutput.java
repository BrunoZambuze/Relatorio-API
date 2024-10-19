package com.relatorioSpringRest.relatorioApi.api.representationmodel.output;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class TopicoDtoOutput {

    private Long id;
    private String descricao;
    private OffsetDateTime data;

}
