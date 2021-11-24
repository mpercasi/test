package com.example.apiInversiones.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name="Inversiones")
public class Inversion {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String Tipo;
    @OneToOne(cascade = {CascadeType.ALL})
    //@Column(name="Plazos")
    private PlazoFijo plazo;
    private String fechaVencimiento;

    public Inversion() {
    }

    public Inversion(String tipo, PlazoFijo plazo, String fechaVencimiento) {
        Tipo = tipo;
        this.plazo = plazo;
        this.fechaVencimiento = fechaVencimiento;
    }
}
