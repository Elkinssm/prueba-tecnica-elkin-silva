package com.elkin.pruebaTecnica.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "persona")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    @Column(nullable = true)
    private Integer edad;
    @Column(nullable = true)
    private String identificacion;
    private String direccion;
    private int telefono;
    @Column(nullable = true)
    private GeneroEnum genero;

    public Persona(String nombre, String direccion, int telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Persona() {

    }
}
