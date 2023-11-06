package com.japetech.tourmate.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClimaFrio() {
        return climaFrio;
    }

    public void setClimaFrio(String climaFrio) {
        this.climaFrio = climaFrio;
    }

    public String getClimaQuente() {
        return climaQuente;
    }

    public void setClimaQuente(String climaQuente) {
        this.climaQuente = climaQuente;
    }

    public String getViagemTurismo() {
        return viagemTurismo;
    }

    public void setViagemTurismo(String viagemTurismo) {
        this.viagemTurismo = viagemTurismo;
    }

    public String getViagemNegocio() {
        return viagemNegocio;
    }

    public void setViagemNegocio(String viagemNegocio) {
        this.viagemNegocio = viagemNegocio;
    }

    public String getViagemLazer() {
        return viagemLazer;
    }

    public void setViagemLazer(String viagemLazer) {
        this.viagemLazer = viagemLazer;
    }

    public String getViagemRomantico() {
        return viagemRomantico;
    }

    public void setViagemRomantico(String viagemRomantico) {
        this.viagemRomantico = viagemRomantico;
    }

    public String getClimaChuvoso() {
        return climaChuvoso;
    }

    public void setClimaChuvoso(String climaChuvoso) {
        this.climaChuvoso = climaChuvoso;
    }

    public String getClimaEnsolarado() {
        return climaEnsolarado;
    }

    public void setClimaEnsolarado(String climaEnsolarado) {
        this.climaEnsolarado = climaEnsolarado;
    }

    public String getClimaNublado() {
        return climaNublado;
    }

    public void setClimaNublado(String climaNublado) {
        this.climaNublado = climaNublado;
    }

    public String getClimaNeve() {
        return climaNeve;
    }

    public void setClimaNeve(String climaNeve) {
        this.climaNeve = climaNeve;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }
}
