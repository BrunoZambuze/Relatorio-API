package com.relatorioSpringRest.relatorioApi.api.assembler;

import com.relatorioSpringRest.relatorioApi.api.representationmodel.output.RelatorioGerenteRepresentationOutput;
import com.relatorioSpringRest.relatorioApi.domain.model.Usuario;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class RelatorioGerenteAssembler {

    private final ModelMapper modelMapper;

    public RelatorioGerenteRepresentationOutput toRelatorioGerenteOutput(Usuario usuario){
        return modelMapper.map(usuario, RelatorioGerenteRepresentationOutput.class);
    }

    public List<RelatorioGerenteRepresentationOutput> toCollectionRelatorioGerenteRepresentationOutput(List<Usuario> usuarios){
        return usuarios.stream()
                       .map(this::toRelatorioGerenteOutput)
                       .toList();
    }

}
