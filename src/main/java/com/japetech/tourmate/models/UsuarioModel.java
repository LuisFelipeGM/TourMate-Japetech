package com.japetech.tourmate.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.japetech.tourmate.enums.Sexo;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Data
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

}
