package com.elkin.pruebaTecnica.service.dto;

import com.elkin.pruebaTecnica.persistence.entity.TipoCuentaEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CrearCuentaDTO {

    private Long clienteId;
    private String numeroCuenta;
    private TipoCuentaEnum tipoCuenta;
    private Double saldoInicial;
    private Boolean estado;

}
