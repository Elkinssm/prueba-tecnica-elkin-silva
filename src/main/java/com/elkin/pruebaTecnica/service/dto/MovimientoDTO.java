package com.elkin.pruebaTecnica.service.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MovimientoDTO {
    private Long id;
    private String movimiento;
    private Double valor;
    private Double saldo;
}
