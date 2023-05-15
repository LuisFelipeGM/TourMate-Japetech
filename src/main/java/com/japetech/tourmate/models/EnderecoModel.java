package com.japetech.tourmate.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "T_TRMT_ENDERECO")
public class EnderecoModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @OrderColumn(name = "1")
    private Long id;

    @Column(nullable = false, length = 9)
    @OrderColumn(name = "2")
    private Integer cep;

    @Column(nullable = false, length = 80)
    @OrderColumn(name = "3")
    private String logradouro;

    @Column(length = 7)
    @OrderColumn(name = "4")
    private Integer numero;

    @Column(length = 30)
    @OrderColumn(name = "5")
    private String complementoNumero;

    @Column(length = 50)
    @OrderColumn(name = "6")
    private String pontoReferencia;

    @Column(nullable = false, length = 60)
    @OrderColumn(name = "7")
    private String bairro;

    @Column(nullable = false, length = 60)
    @OrderColumn(name = "8")
    private String cidade;

    @Column(nullable = false, length = 45)
    @OrderColumn(name = "9")
    private String estado;

    @Column(nullable = false, length = 2)
    @OrderColumn(name = "10")
    private String siglaEstado;

    @OrderColumn(name = "11")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario", foreignKey = @ForeignKey(name = "FK_USUARIO_ENDERECO"))
    private UsuarioModel usuario;

}
