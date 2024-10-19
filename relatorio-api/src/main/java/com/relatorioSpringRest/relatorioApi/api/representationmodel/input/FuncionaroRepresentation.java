package com.relatorioSpringRest.relatorioApi.api.representationmodel.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FuncionaroRepresentation {

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;

}
