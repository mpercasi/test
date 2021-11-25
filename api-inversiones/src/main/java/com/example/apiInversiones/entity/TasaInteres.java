package com.example.apiInversiones.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name="Tasas_Interes")
public class TasaInteres {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private double tasa;

    public TasaInteres() {
    }

    public TasaInteres(double tasa) {
        this.tasa = tasa;
    }
}
