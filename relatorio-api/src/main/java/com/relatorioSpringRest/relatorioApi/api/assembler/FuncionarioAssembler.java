package com.relatorioSpringRest.relatorioApi.api.assembler;

import com.relatorioSpringRest.relatorioApi.api.representationmodel.input.FuncionaroRepresentation;
import com.relatorioSpringRest.relatorioApi.domain.model.Usuario;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FuncionarioAssembler {

    private final ModelMapper modelMapper;

    public Usuario toEntity(FuncionaroRepresentation funcionarioInput){
        return modelMapper.map(funcionarioInput, Usuario.class);
    }

}
