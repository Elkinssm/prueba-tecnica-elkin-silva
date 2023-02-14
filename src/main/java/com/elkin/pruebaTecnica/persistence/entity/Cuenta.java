package com.elkin.pruebaTecnica.persistence.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cuenta")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String numeroCuenta;
    private TipoCuentaEnum tipoCuenta;
    private Double saldoInicial;
    private Boolean estado;
}
