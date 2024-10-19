package com.relatorioSpringRest.relatorioApi.api.representationmodel.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RelatorioDtoInput {

    @NotBlank
    private String titulo;

}
