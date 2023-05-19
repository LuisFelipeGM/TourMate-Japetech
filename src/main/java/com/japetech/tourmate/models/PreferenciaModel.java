package com.japetech.tourmate.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "T_TRMT_PREFERENCIA")
public class PreferenciaModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @OrderColumn(name = "1")
    private Long id;

    @Column(nullable = false, length = 1)
    @OrderColumn(name = "2")
    private String flowUsuario;

    @Column(length = 1)
    @OrderColumn(name = "3")
    private String climaFrio;

    @Column(length = 1)
    @OrderColumn(name = "4")
    private String climaQuente;

    @Column(length = 1)
    @OrderColumn(name = "5")
    private String viajemTurismo;

    @Column(length = 1)
    @OrderColumn(name = "6")
    private String viajemNegocio;

    @Column(length = 1)
    @OrderColumn(name = "7")
    private String viajemLazer;

    @Column(length = 1)
    @OrderColumn(name = "8")
    private String viajemRomantico;

    @Column(length = 1)
    @OrderColumn(name = "9")
    private String climaChuvoso;

    @Column(length = 1)
    @OrderColumn(name = "10")
    private String climaEnsolarado;

    @Column(length = 1)
    @OrderColumn(name = "11")
    private String climaNublado;

    @Column(length = 1)
    @OrderColumn(name = "12")
    private String climaNeve;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "T_TRMT_PLANO_VIAJEM_PREFERENCIA",
            joinColumns = @JoinColumn(name = "id_preferencia", foreignKey = @ForeignKey(name = "FK_PREFERENCIA")),
            inverseJoinColumns = @JoinColumn(name = "id_viajem", foreignKey = @ForeignKey(name = "FK_VIAJEM_PREFERENCIA"))
    )
    private List<ViajemModel> viajem;

}
