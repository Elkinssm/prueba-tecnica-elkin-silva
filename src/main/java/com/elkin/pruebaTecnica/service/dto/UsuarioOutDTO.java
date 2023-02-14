package com.elkin.pruebaTecnica.service.dto;

import com.elkin.pruebaTecnica.persistence.entity.Cuenta;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UsuarioOutDTO {
    private String nombre;
    private String direccion;
    private String telefono;
    private String contrasenia;
    private Boolean estado;
    private List<Cuenta> cuentas = new ArrayList<>();

}
