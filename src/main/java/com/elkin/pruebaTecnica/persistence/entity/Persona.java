package com.elkin.pruebaTecnica.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "edad", "identificacion", "direccion", "genero"})
@MappedSuperclass
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private Integer edad;
    private String identificacion;
    private String direccion;
    private String telefono;
    private GeneroEnum genero;


}
