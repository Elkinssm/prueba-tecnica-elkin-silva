package com.elkin.pruebaTecnica.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class MovimientoDTO {
    private Long id;
    private Long cuentaId;
    private Double valor;
    private Double saldo;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private String fecha;
    private String movimiento;
}
