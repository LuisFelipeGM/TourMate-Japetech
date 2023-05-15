package com.japetech.tourmate.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "T_TRMT_VIAJEM")
public class ViajemModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @OrderColumn(name = "1")
    private Long id;

    @Column(nullable = false, length = 45)
    @OrderColumn(name = "2")
    private String lugarDesejado;

    @Column(nullable = false, length = 30)
    @OrderColumn(name = "3")
    private String clima;

    @Column(nullable = false, length = 7)
    @OrderColumn(name = "4")
    private Integer numeroPessoas;

    @FutureOrPresent
    @Column(nullable = false)
    @OrderColumn(name = "5")
    private LocalDate dataInicio;

    @FutureOrPresent
    @Column(nullable = false)
    @OrderColumn(name = "6")
    private LocalDate dataVolta;

    @OrderColumn(name = "7")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario", foreignKey = @ForeignKey(name = "FK_USUARIO_VIAJEM"))
    private UsuarioModel usuario;

    @ManyToMany(mappedBy = "viajem")
    private List<PlanoViajemModel> planoViajem;

    @ManyToMany(mappedBy = "viajem")
    private List<PreferenciaModel> preferencia;

    @ManyToMany(mappedBy = "viajem")
    private List<ParceirosModel> parceiros;

}
