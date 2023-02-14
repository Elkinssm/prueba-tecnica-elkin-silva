package com.elkin.pruebaTecnica.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UsuarioInDTO {
    private String nombre;
    private String direccion;
    private int telefono;
    private String contrasenia;
    private Boolean estado;
}
