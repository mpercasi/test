package com.example.apiInversiones.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name="Plazos")
public class PlazoFijo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private double monto;
    private int plazoDias;
    //private Cuenta cuenta;
    @OneToOne(cascade = {CascadeType.ALL})
    private TasaInteres tasaInteres;
    private double montoTotal;

    public PlazoFijo() {
    }

    public PlazoFijo(double monto, int plazoDias, TasaInteres tasaInteres) {
        this.monto = monto;
        this.plazoDias = plazoDias;
        this.tasaInteres = tasaInteres;
        this.montoTotal = monto * ( tasaInteres.getTasa() * ((double) plazoDias/365) ) + monto;
    }

    // Getters && Setters ---------------------------------------------------

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getPlazoDias() {
        return plazoDias;
    }

    public void setPlazoDias(int plazoDias) {
        this.plazoDias = plazoDias;
    }

    public TasaInteres getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(TasaInteres tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }
}
