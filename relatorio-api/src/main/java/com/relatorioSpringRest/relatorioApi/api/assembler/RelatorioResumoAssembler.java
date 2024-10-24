package com.relatorioSpringRest.relatorioApi.api.assembler;

import com.relatorioSpringRest.relatorioApi.api.representationmodel.output.RelatorioResumoRepresentation;
import com.relatorioSpringRest.relatorioApi.domain.model.Relatorio;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RelatorioResumoAssembler {

    private final ModelMapper modelMapper;

    public RelatorioResumoRepresentation toRelatorioResumoOutput(Relatorio relatorio){
        return modelMapper.map(relatorio, RelatorioResumoRepresentation.class);
    }

}
