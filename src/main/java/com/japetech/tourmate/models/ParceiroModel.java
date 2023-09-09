package com.japetech.tourmate.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "T_TRMT_PARCEIRO")
public class ParceiroModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 80)
    private String nomeFantasia;

    @Column(nullable = false, length = 14, unique = true)
    private String cnpj;

    @Column(length = 30)
    private String telefone;

}
