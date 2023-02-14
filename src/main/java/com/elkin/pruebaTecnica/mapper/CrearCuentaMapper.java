package com.elkin.pruebaTecnica.mapper;

import com.elkin.pruebaTecnica.persistence.entity.Cuenta;
import com.elkin.pruebaTecnica.service.dto.CrearCuentaDTO;

public class CrearCuentaMapper implements IMapper<CrearCuentaDTO, Cuenta> {
    @Override
    public Cuenta map(CrearCuentaDTO in) {
        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta(in.getNumeroCuenta());
        cuenta.setTipoCuenta(in.getTipoCuenta());
        cuenta.setSaldoInicial(in.getSaldoInicial());
        cuenta.setEstado(in.getEstado());
        //cuenta.setCliente(in.getClienteId());
        return null;
    }
}
