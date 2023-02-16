package com.elkin.pruebaTecnica.service;

import com.elkin.pruebaTecnica.exceptions.AppExceptions;
import com.elkin.pruebaTecnica.persistence.entity.Cuenta;
import com.elkin.pruebaTecnica.persistence.entity.Movimiento;
import com.elkin.pruebaTecnica.persistence.entity.TipoMovimientoEnum;
import com.elkin.pruebaTecnica.persistence.repository.CuentaRepository;
import com.elkin.pruebaTecnica.persistence.repository.MovimientoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MovimientoService {


    private final MovimientoRepository movimientoRepository;


    private final CuentaRepository cuentaRepository;

    public MovimientoService(MovimientoRepository movimientoRepository, CuentaRepository cuentaRepository) {
        this.movimientoRepository = movimientoRepository;
        this.cuentaRepository = cuentaRepository;
    }

    @Transactional
    public void crearMovimiento(Long idCuenta, TipoMovimientoEnum tipoMovimiento, Double valor) {
        Optional<Cuenta> optionalCuenta = cuentaRepository.findById(idCuenta);
        if (optionalCuenta.isPresent()) {
            Cuenta cuenta = optionalCuenta.get();
            Double saldoAnterior = cuenta.getSaldoActual();
            Double saldoActual;

            if (tipoMovimiento.equals(TipoMovimientoEnum.DEBITO)) {
                saldoActual = saldoAnterior + valor;
            } else {
                saldoActual = saldoAnterior - valor;
                if (saldoActual < 0) {
                    throw new AppExceptions("Saldo insuficiente en la cuenta", HttpStatus.BAD_REQUEST);
                }
            }

            Movimiento movimiento = new Movimiento();
            movimiento.setTipoMovimiento(tipoMovimiento);
            movimiento.setValor(valor);
            movimiento.setSaldoAnterior(saldoAnterior);
            movimiento.setSaldoActual(saldoActual);
            movimiento.setCuenta(cuenta);

            cuenta.setSaldoActual(saldoActual);
            cuenta.getMovimientos().add(movimiento);

            movimientoRepository.save(movimiento);
            cuentaRepository.save(cuenta);
        } else {
            throw new AppExceptions("Cuenta no encontrada", HttpStatus.BAD_REQUEST);
        }
    }
}
