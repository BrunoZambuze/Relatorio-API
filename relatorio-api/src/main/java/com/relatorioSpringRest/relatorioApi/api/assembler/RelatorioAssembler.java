package com.relatorioSpringRest.relatorioApi.api.assembler;

import com.relatorioSpringRest.relatorioApi.api.representationmodel.input.RelatorioDtoInput;
import com.relatorioSpringRest.relatorioApi.api.representationmodel.output.RelatorioDtoOutput;
import com.relatorioSpringRest.relatorioApi.domain.model.Relatorio;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class RelatorioAssembler {

    private final ModelMapper modelMapper;

    public Relatorio toEntity(RelatorioDtoInput relatorioInput){
        return modelMapper.map(relatorioInput, Relatorio.class);
    }

    public RelatorioDtoOutput toRelatorioOutput(Relatorio relatorio){
        return modelMapper.map(relatorio, RelatorioDtoOutput.class);
    }

    public List<RelatorioDtoOutput> toCollectionRelatorioOutput(List<Relatorio> listaRelatorios){
        return listaRelatorios.stream()
                              .map(this::toRelatorioOutput)
                              .toList();
    }

}
