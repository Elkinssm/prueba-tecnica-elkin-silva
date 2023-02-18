package com.elkin.pruebaTecnica.service.dto;

import com.elkin.pruebaTecnica.persistence.entity.TipoCuentaEnum;
import lombok.Data;

@Data
public class MovimientoClienteDTO {
    private String fecha;
    private String cliente;
    private String numeroCuenta;
    private TipoCuentaEnum tipoCuenta;
    private Double saldoInicial;
    private Boolean estadoCuenta;
    private String movimiento;
    private Double saldoDisponible;
}
