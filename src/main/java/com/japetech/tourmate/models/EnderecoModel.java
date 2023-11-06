package com.japetech.tourmate.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;


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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCep() {
        return cep;
    }

    public void setCep(Long cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplementoNumero() {
        return complementoNumero;
    }

    public void setComplementoNumero(String complementoNumero) {
        this.complementoNumero = complementoNumero;
    }

    public String getPontoReferencia() {
        return pontoReferencia;
    }

    public void setPontoReferencia(String pontoReferencia) {
        this.pontoReferencia = pontoReferencia;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getSiglaEstado() {
        return siglaEstado;
    }

    public void setSiglaEstado(String siglaEstado) {
        this.siglaEstado = siglaEstado;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }
}
