package com.elkin.pruebaTecnica.service.dto;

import com.elkin.pruebaTecnica.persistence.entity.TipoCuentaEnum;
import lombok.Data;

@Data
public class CuentaOutDTO {
    private String numeroCuenta;
    private TipoCuentaEnum tipoCuenta;
    private Double saldoInicial;
    private Boolean estado;

}
