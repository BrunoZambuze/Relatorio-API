package com.relatorioSpringRest.relatorioApi.api.representationmodel.output;

import com.relatorioSpringRest.relatorioApi.domain.model.Enum.StatusRelatorio;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class RelatorioDtoOutput {

    private Long id;
    private String titulo;
    private StatusRelatorio status;
    private OffsetDateTime data;

}
