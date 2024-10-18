package com.relatorioSpringRest.relatorioApi.api.representationmodel.input;

import com.relatorioSpringRest.relatorioApi.domain.model.Enum.TipoUsuario;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDtoInput {

    @Column
    @NotBlank
    private String nome;

    @Column
    @NotBlank
    @Email
    private String email;

    @Column
    @Enumerated(EnumType.STRING)
    @NotNull
    private TipoUsuario tipoUsuario;

}
