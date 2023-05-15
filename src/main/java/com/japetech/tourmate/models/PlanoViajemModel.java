package com.japetech.tourmate.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "T_TRMT_PLANO_VIAJEM")
public class PlanoViajemModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @OrderColumn(name = "1")
    private Long id;

    @Column(length = 80)
    @OrderColumn(name = "2")
    private String nomeFantasia;

    @Column(nullable = false, length = 100)
    @OrderColumn(name = "3")
    private String planoViajem;

    @Column(nullable = false, length = 80)
    @OrderColumn(name = "4")
    private String localViagjem;

    @Column(nullable = false)
    @OrderColumn(name = "5")
    private LocalDate dataInicio;

    @OrderColumn(name = "6")
    private LocalDate dataFim;

    @ManyToMany
    @JoinTable(
            name = "T_TRMT_PLANO_VIAJEM_USUARIO",
            joinColumns = @JoinColumn(name = "id_plano_viajem"), foreignKey = @ForeignKey(name = "FK_PLANO_VIAJEM"),
            inverseJoinColumns = @JoinColumn(name = "id_viajem", foreignKey = @ForeignKey(name = "FK_VIAJEM_USUARIO"))
    )
    private List<ViajemModel> viajem;

}
