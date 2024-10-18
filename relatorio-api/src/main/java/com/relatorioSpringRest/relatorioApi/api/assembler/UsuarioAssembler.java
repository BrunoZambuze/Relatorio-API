package com.relatorioSpringRest.relatorioApi.api.assembler;

import com.relatorioSpringRest.relatorioApi.api.representationmodel.input.UsuarioDtoInput;
import com.relatorioSpringRest.relatorioApi.api.representationmodel.output.UsuarioDtoOutput;
import com.relatorioSpringRest.relatorioApi.domain.model.Usuario;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UsuarioAssembler {

    private final ModelMapper modelMapper;

    public Usuario toEntity(UsuarioDtoInput usuarioInput){
        return modelMapper.map(usuarioInput, Usuario.class);
    }

    public UsuarioDtoOutput toUsuarioOutput(Usuario usuario){
        return modelMapper.map(usuario, UsuarioDtoOutput.class);
    }

}
