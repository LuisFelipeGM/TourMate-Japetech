package com.japetech.tourmate.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "T_TRMT_PARCEIROS")
public class ParceirosModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @OrderColumn(name = "1")
    private Long id;

    @Column(nullable = false, length = 80)
    @OrderColumn(name = "2")
    private String nomeFantasia;

    @Column(nullable = false, length = 14)
    @OrderColumn(name = "3")
    private String cnpj;

    @Pattern(regexp = "^\\([1-9]{2}\\) (?:[2-8]|9[1-9])[0-9]{3}\\-[0-9]{4}$")
    @Column(length = 30)
    @OrderColumn(name = "4")
    private String telefone;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "T_TRMT_PLANO_VIAJEM_PARCEIROS",
            joinColumns = @JoinColumn(name = "id_parceiros", foreignKey = @ForeignKey(name = "FK_PARCEIROS")),
            inverseJoinColumns = @JoinColumn(name = "id_viajem", foreignKey = @ForeignKey(name = "FK_VIAJEM_PARCEIRO"))
    )
    private List<ViajemModel> viajem;




}
