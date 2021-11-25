package com.example.BankMain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name="Cuentas")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String estado;
    private double monto;
    private String tipoMoneda;
    private String tipo;
    private double montoSobreGiro;
    private int mesesAhorro;
    private int numCliente;
    @OneToMany(cascade = {CascadeType.ALL})
    @Column(name="Tarjetas")
    private List<Tarjeta> tarjetas = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.ALL})
    @Column(name="Inversiones")
    private List<Inversion> inversiones = new ArrayList<>();


    public Cuenta() {
    }

    public Cuenta(String estado, double monto, String tipoMoneda, String tipo, double montoSobreGiro, int mesesAhorro, int numCliente, List<Tarjeta> tarjetas, List<Inversion> inversiones) {
        this.estado = estado;
        this.monto = monto;
        this.tipoMoneda = tipoMoneda;
        this.tipo = tipo;
        this.montoSobreGiro = montoSobreGiro;
        this.mesesAhorro = mesesAhorro;
        this.numCliente = numCliente;
        this.tarjetas = tarjetas;
        this.inversiones = inversiones;
    }
}
