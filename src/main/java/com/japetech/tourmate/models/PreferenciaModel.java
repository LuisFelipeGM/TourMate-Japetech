package com.japetech.tourmate.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "T_TRMT_PREFERENCIA")
public class PreferenciaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 1)
    private String climaFrio;

    @Column(nullable = false, length = 1)
    private String climaQuente;

    @Column(nullable = false, length = 1)
    private String viagemTurismo;

    @Column(nullable = false, length = 1)
    private String viagemNegocio;

    @Column(length = 1)
    private String viagemLazer;

    @Column(length = 1)
    private String viagemRomantico;

    @Column(length = 1)
    private String climaChuvoso;

    @Column(length = 1)
    private String climaEnsolarado;

    @Column(length = 1)
    private String climaNublado;

    @Column(length = 1)
    private String climaNeve;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_usuario", foreignKey = @ForeignKey(name = "FK_USUARIO_PREFERENCIA"))
    @JsonIgnore
    private UsuarioModel usuario;

}
