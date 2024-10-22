package com.relatorioSpringRest.relatorioApi.api.representationmodel.output;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RelatorioGerenteRepresentationOutput {

    private Long id;
    private String nome;
    private List<RelatorioResumoRepresentation> relatorios;

}
