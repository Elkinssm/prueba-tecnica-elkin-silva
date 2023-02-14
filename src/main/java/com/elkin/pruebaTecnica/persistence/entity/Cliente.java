package com.elkin.pruebaTecnica.persistence.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "cliente")
public class Cliente extends Persona {

    private String clienteId;
    @Column(name = "contrasenia")
    private String contrasenia;
    @Column(name = "estado")
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
