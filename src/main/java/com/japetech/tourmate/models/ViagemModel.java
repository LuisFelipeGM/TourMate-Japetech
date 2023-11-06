package com.japetech.tourmate.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "T_TRMT_VIAGEM")
public class ViagemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 60)
    private String lugarDesejado;

    @Column(nullable = false, length = 8000)
    private String resumo;

    @Column(nullable = false)
    private LocalDate dataInicio;

    @Column(nullable = false)
    private LocalDate dataFim;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_usuario", foreignKey = @ForeignKey(name = "FK_VIAGEM_USUARIO"))
    @JsonIgnore
    private UsuarioModel usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLugarDesejado() {
        return lugarDesejado;
    }

    public void setLugarDesejado(String lugarDesejado) {
        this.lugarDesejado = lugarDesejado;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }
}
