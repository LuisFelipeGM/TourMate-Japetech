package com.japetech.tourmate.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "T_TRMT_ENDERECO")
public class EnderecoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 9)
    private Long cep;

    @Column(nullable = false, length = 80)
    private String logradouro;

    @Column(length = 7)
    private Integer numero;

    @Column(length = 30)
    private String complementoNumero;

    @Column(length = 50)
    private String pontoReferencia;

    @Column(nullable = false, length = 60)
    private String bairro;

    @Column(nullable = false, length = 60)
    private String cidade;

    @Column(nullable = false, length = 45)
    private String estado;

    @Column(nullable = false, length = 2)
    private String siglaEstado;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_usuario", foreignKey = @ForeignKey(name = "FK_USUARIO_ENDERECO"))
    @JsonIgnore
    private UsuarioModel usuario;

}
