package com.example.bank.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    public Cliente(String usuario, String contra, String status) {
        this.usuario = usuario;
        this.contra = contra;
        this.status = status;
    }

    public Cliente() {
    }

}
