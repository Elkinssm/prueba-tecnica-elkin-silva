package com.elkin.pruebaTecnica.service.dto;

import com.elkin.pruebaTecnica.persistence.entity.TipoCuentaEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "clienteId"})
public class CuentaClienteIdNombre {
    private Long id;

    private String numeroCuenta;

    private TipoCuentaEnum tipoCuenta;

    private Double saldoInicial;

    private Boolean estado;
    private Long clienteId;

    private String nombreCliente;

}