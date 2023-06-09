package com.japetech.tourmate.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "T_TRMT_USUARIO")
public class UsuarioModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @OrderColumn(name = "1")
    private Long id;

    @Column(nullable = false, length = 80)
    @OrderColumn(name = "2")
    private String nome;

    @Email
    @Column(nullable = false, length = 100)
    @OrderColumn(name = "3")
    private String email;

    @Column(nullable = false, length = 30)
    @OrderColumn(name = "4")
    private String senha;

    @Column(unique = true, length = 11)
    @OrderColumn(name = "5")
    private String cpf;

    @Past
    @OrderColumn(name = "6")
    private LocalDate dataNascimento;

    @Column(nullable = false, length = 1)
    @OrderColumn(name = "7")
    private String tipoUsuario;

    @Column(nullable = false, length = 1)
    @OrderColumn(name = "8")
    private String sexo;

    @JsonProperty("idade")
    public int idade(){
        if (dataNascimento != null){
            return Period.between(dataNascimento, LocalDate.now()).getYears();
        }else{
            return 0;
        }
    }

    @OneToMany(mappedBy = "usuario")
    private List<EnderecoModel> endereco = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private List<ViajemModel> viajem = new ArrayList<>();

}
