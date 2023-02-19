package com.elkin.pruebaTecnica.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UsuarioDTO {

    private Long id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String contrasenia;
    private Boolean estado;


}