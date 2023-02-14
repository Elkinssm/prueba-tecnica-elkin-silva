package com.elkin.pruebaTecnica.persistence.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "cliente")
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public class Cliente extends Persona {


    private String contrasenia;

    private Boolean estado;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cuenta> cuentas = new ArrayList<>();


}
