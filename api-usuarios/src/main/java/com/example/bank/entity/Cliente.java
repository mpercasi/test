package com.example.bank.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name="Clientes")
@Getter @Setter
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nombre;
    private String apellido;
    private Integer edad;
    private String celular;
    private String correo;
    private String usuario;
    private String contra;
    private String status;
    private int numCliente;

    @OneToMany(cascade = {CascadeType.ALL})
    @Column(name="Cuentas")
    private List<Cuenta> cuentas = new ArrayList<>();

    public Cliente(String usuario, String contra, String status) {
        this.usuario = usuario;
        this.contra = contra;
        this.status = status;
    }

    public Cliente(String nombre, String apellido, Integer edad, String celular, String correo, String usuario, String contra, String status, int numCliente) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.celular = celular;
        this.correo = correo;
        this.usuario = usuario;
        this.contra = contra;
        this.status = status;
        this.numCliente = numCliente;
    }

    public Cliente() {
    }

}
