package com.elkin.pruebaTecnica.service;

import com.elkin.pruebaTecnica.exceptions.AppExceptions;
import com.elkin.pruebaTecnica.persistence.entity.Cuenta;
import com.elkin.pruebaTecnica.persistence.entity.Movimiento;
import com.elkin.pruebaTecnica.persistence.repository.CuentaRepository;
import com.elkin.pruebaTecnica.persistence.repository.MovimientoRepository;
import com.elkin.pruebaTecnica.service.dto.MovimientoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovimientosService {

    private final MovimientoRepository movimientoRepository;
    ObjectMapper mapper;
    private final CuentaRepository cuentaRepository;

    public MovimientosService(MovimientoRepository movimientoRepository, ObjectMapper mapper, CuentaRepository cuentaRepository) {
        this.movimientoRepository = movimientoRepository;
        this.mapper = mapper;
        this.cuentaRepository = cuentaRepository;
    }


    public void saveMethod(MovimientoDTO movimientoDTO) {
        if (movimientoDTO != null) {
            Movimiento movimiento = mapper.convertValue(movimientoDTO, Movimiento.class);
            Cuenta cuenta = cuentaRepository.findById(movimientoDTO.getCuentaId()).orElseThrow(() ->
                    new AppExceptions("Cuenta no encontrada", HttpStatus.NOT_FOUND)
            );
            double saldoAnterior = cuenta.getSaldoInicial();
            double saldoActual = movimientoDTO.getMovimiento().equals("DEBITO") ?
                    saldoAnterior - movimiento.getValor() :
                    saldoAnterior + movimiento.getValor();
            if (saldoActual < 0) {
                throw new AppExceptions("Saldo no disponible", HttpStatus.NOT_FOUND);
            }
            cuenta.setSaldoInicial(saldoActual);
            cuentaRepository.save(cuenta);
            movimiento.setSaldoAnterior(saldoAnterior);
            movimiento.setSaldoActual(saldoActual);
            String movimientoStr = movimientoDTO.getMovimiento().equals("CREDITO") ?
                    "Depósito de " + movimiento.getValor() :
                    "Retiro de " + movimiento.getValor();
            movimiento.setMovimiento(movimientoStr);
            movimientoRepository.save(movimiento);
        } else {
            throw new AppExceptions("Movimiento nulo", HttpStatus.BAD_REQUEST);
        }
    }

    public Collection<MovimientoDTO> findAll() {
        List<Movimiento> movimientoList = movimientoRepository.findAll();
        Set<MovimientoDTO> movimientoDTO = new HashSet<>();
        for (Movimiento movimiento : movimientoList) {
            movimientoDTO.add(mapper.convertValue(movimiento, MovimientoDTO.class));

        }
        return movimientoDTO;
    }

    public MovimientoDTO findMovimientoById(Long id) {
        Movimiento movimiento = movimientoRepository.findById(id).get();
        MovimientoDTO movimientoDTO = null;
        if (movimiento.getId() != null) {
            movimientoDTO = mapper.convertValue(movimiento, MovimientoDTO.class);
        }
        return movimientoDTO;
    }

    public void saveMovimiento(MovimientoDTO movimientoDTO) {
        saveMethod(movimientoDTO);
    }

    public void deleteMovimiento(Long id) {
        Movimiento movimiento = movimientoRepository.findById(id).get();
        movimientoRepository.deleteById(id);
    }

    public void updateMovimiento(MovimientoDTO movimientoDTO) {
        saveMethod(movimientoDTO);
    }


}