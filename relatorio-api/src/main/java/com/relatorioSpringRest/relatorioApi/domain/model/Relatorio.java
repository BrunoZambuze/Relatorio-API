package com.relatorioSpringRest.relatorioApi.domain.model;

import com.relatorioSpringRest.relatorioApi.domain.model.Enum.StatusRelatorio;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Relatorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column
    @NotBlank
    private String titulo;

    @Column
    @Enumerated(EnumType.STRING)
    private StatusRelatorio status;

    @Column
    private OffsetDateTime data;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "relatorio", cascade = CascadeType.ALL)
    private List<Topico> topicos = new ArrayList<>();

    public Topico adicionarTopico(Topico topico){
        //Sempre que adicionarmos um novo tópico, o relatório muda o status para NÃO VISUALIZADO
        if(this.getStatus().equals(StatusRelatorio.VISUALIZADO)){
            this.setStatus(StatusRelatorio.NAO_VISUALIZADO);
        }
        topico.setData(OffsetDateTime.now());
        topico.setRelatorio(this);
        this.getTopicos().add(topico);
        return topico;
    }

}
