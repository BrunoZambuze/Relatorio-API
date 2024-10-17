package com.relatorioSpringRest.relatorioApi.domain.model;

import com.relatorioSpringRest.relatorioApi.domain.model.Enum.TipoUsuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank
    private String nome;

    @Column
    @NotBlank
    @Email
    private String email;

    @Column
    @Enumerated(EnumType.STRING)
    @NotNull
    private TipoUsuario tipoUsuario;

}
