package com.elkin.pruebaTecnica.persistence.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "cliente")
public class Cliente extends Persona {


    private String contrasenia;

    private Boolean estado;


}
