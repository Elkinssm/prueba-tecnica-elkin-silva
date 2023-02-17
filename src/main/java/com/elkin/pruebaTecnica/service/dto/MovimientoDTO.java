package com.elkin.pruebaTecnica.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class MovimientoDTO {
    private Long cuentaId;
    private Long id;
    private String movimiento;
    private Double valor;
    private Double saldo;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private String fecha;


}
