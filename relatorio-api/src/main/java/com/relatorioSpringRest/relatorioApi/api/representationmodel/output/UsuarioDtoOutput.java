package com.relatorioSpringRest.relatorioApi.api.representationmodel.output;

import com.relatorioSpringRest.relatorioApi.domain.model.Enum.TipoUsuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDtoOutput {

    private Long id;
    private String nome;
    private TipoUsuario tipoUsuario;

}
