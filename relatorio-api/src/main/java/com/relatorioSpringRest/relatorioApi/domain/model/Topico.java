package com.relatorioSpringRest.relatorioApi.domain.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column
    private String descricao;

    @Column
    private OffsetDateTime data;

    @ManyToOne
    @JoinColumn(name = "id_relatorio")
    private Relatorio relatorio;

}
