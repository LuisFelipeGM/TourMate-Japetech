package com.japetech.tourmate.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.japetech.tourmate.enums.Sexo;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "T_TRMT_USUARIO")
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 80)
    private String nome;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 30)
    private String senha;

    @Column(nullable = false, unique = true, length = 12)
    private String cpf;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false, length = 1)
    private Sexo sexo;

    @Column(nullable = false, length = 1)
    private String plus;

    @JsonProperty("idade")
    public int idade(){
        if (dataNascimento != null){
            return Period.between(dataNascimento, LocalDate.now()).getYears();
        }else{
            return 0;
        }
    }

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private EnderecoModel endereco;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private PreferenciaModel preferencia;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ViagemModel> viagem = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getPlus() {
        return plus;
    }

    public void setPlus(String plus) {
        this.plus = plus;
    }

    public EnderecoModel getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoModel endereco) {
        this.endereco = endereco;
    }

    public PreferenciaModel getPreferencia() {
        return preferencia;
    }

    public void setPreferencia(PreferenciaModel preferencia) {
        this.preferencia = preferencia;
    }

    public List<ViagemModel> getViagem() {
        return viagem;
    }

    public void setViagem(List<ViagemModel> viagem) {
        this.viagem = viagem;
    }
}
