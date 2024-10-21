package com.relatorioSpringRest.relatorioApi.api.assembler;

import com.relatorioSpringRest.relatorioApi.api.representationmodel.input.TopicoDtoInput;
import com.relatorioSpringRest.relatorioApi.api.representationmodel.output.TopicoDtoOutput;
import com.relatorioSpringRest.relatorioApi.domain.model.Topico;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class TopicoAssembler {

    private final ModelMapper modelMapper;

    public Topico toEntity(TopicoDtoInput topicoInput){
        return modelMapper.map(topicoInput, Topico.class);
    }

    public TopicoDtoOutput toTopicoOutput(Topico topico){
        return modelMapper.map(topico, TopicoDtoOutput.class);
    }

    public List<TopicoDtoOutput> toCollectionOutput(List<Topico> listaTopicos){
        return listaTopicos.stream()
                           .map(this::toTopicoOutput)
                           .toList();
    }

}
