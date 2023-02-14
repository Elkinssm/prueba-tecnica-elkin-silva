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
    private Integer edad;
    private String identificacion;
    private String direccion;
    private int telefono;
    private GeneroEnum genero;


}
