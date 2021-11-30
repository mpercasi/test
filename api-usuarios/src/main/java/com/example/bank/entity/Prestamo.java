package com.example.bank.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name="Prestamos")
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String estado;
    private double monto;
    private int numPrestamo;
    private double cuota;
    private int plazoMeses;
    private int numCliente;
    private double interes = 0.10;

    public Prestamo() {
    }

    public Prestamo(String estado, double monto, int numPrestamo, int plazoMeses, int numCliente) {
        this.estado = estado;
        this.monto = monto;
        this.numPrestamo = numPrestamo;
        this.plazoMeses = plazoMeses;
        this.numCliente = numCliente;
        this.cuota = (monto + (monto * interes)) / plazoMeses;
    }
}
