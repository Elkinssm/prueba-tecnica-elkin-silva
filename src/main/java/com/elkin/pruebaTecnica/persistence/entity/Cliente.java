package com.elkin.pruebaTecnica.persistence.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cliente")
public class Cliente extends Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String clienteId;
    private String contrasenia;
    private Boolean estado;


    public Cliente(Long id, String nombre, Integer edad, String identificacion, String direccion, int telefono, GeneroEnum genero, String clienteId, String contrasenia, Boolean estado) {
        super(nombre, identificacion, telefono);
        this.clienteId = clienteId;
        this.contrasenia = contrasenia;
        this.estado = estado;
    }

    public Cliente() {
        super();
    }
}
