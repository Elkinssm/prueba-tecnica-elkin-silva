package com.elkin.pruebaTecnica.service.dto;

import lombok.Data;

@Data
public class UsuarioDTO {
    private String nombre;
    private String direccion;
    private int telefono;
    private String contrasenia;
    private Boolean estado;
}