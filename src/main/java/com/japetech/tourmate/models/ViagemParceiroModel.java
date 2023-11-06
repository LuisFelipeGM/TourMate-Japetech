package com.japetech.tourmate.models;

import jakarta.persistence.*;

@Entity
@Table(name = "T_TRMT_VIAGEM_PARCEIRO")
public class ViagemParceiroModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_parceiro", foreignKey = @ForeignKey(name = "FK_PARCEIRO_VIAGEM"))
    private ParceiroModel parceiro;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_viagem", foreignKey = @ForeignKey(name = "FK_VIAGEM_PARCEIRO"))
    private ViagemModel viagem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ParceiroModel getParceiro() {
        return parceiro;
    }

    public void setParceiro(ParceiroModel parceiro) {
        this.parceiro = parceiro;
    }

    public ViagemModel getViagem() {
        return viagem;
    }

    public void setViagem(ViagemModel viagem) {
        this.viagem = viagem;
    }
}
