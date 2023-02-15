package com.elkin.pruebaTecnica.service.dto;

import lombok.Data;

@Data
public class MovimientoDTO {
    private Long cuentaId;
    private Long id;
    private String movimiento;
    private Double valor;
    private Double saldo;
}
