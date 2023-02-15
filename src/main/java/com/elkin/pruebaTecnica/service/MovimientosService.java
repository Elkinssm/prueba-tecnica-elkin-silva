package com.elkin.pruebaTecnica.service;

import com.elkin.pruebaTecnica.exceptions.AppExceptions;
import com.elkin.pruebaTecnica.persistence.entity.Cuenta;
import com.elkin.pruebaTecnica.persistence.entity.Movimiento;
import com.elkin.pruebaTecnica.persistence.entity.TransaccionEnum;
import com.elkin.pruebaTecnica.persistence.repository.CuentaRepository;
import com.elkin.pruebaTecnica.persistence.repository.MovimientoRepository;
import com.elkin.pruebaTecnica.service.dto.MovimientoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.AbstractMap.SimpleEntry;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MovimientosService {

    private MovimientoRepository movimientoRepository;
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
            SimpleEntry<Boolean, Double> result = actualizarSaldoCuenta(movimientoDTO, movimiento);

            if (!result.getKey()) {
                throw new AppExceptions("Saldo no disponible", HttpStatus.NOT_FOUND);
            }
            String textoTransaccion = movimiento.getTransaccionEnum().name().equals(TransaccionEnum.CREDITO.name()) ? "Deposito de : " + movimiento.getValor().toString() : "Retiro de :" + movimiento.getValor().toString();
            movimiento.setSaldoInicial(movimiento.getCuenta().getSaldoInicial());
            movimiento.setMovimiento(textoTransaccion);
            movimiento.setSaldo(result.getValue());
            movimientoRepository.save(movimiento);
        } else {
            throw new AppExceptions("Cuenta no encontrada", HttpStatus.NOT_FOUND);
        }
    }

    private SimpleEntry<Boolean, Double> actualizarSaldoCuenta(MovimientoDTO movimientoDTO, Movimiento movimiento) {
        double saldo = movimiento.getCuenta().getSaldoInicial() - movimientoDTO.getValor();
        if (saldo < 0) {
            return new SimpleEntry<>(false, saldo);
        }
        Cuenta cuenta = movimiento.getCuenta();
        cuenta.setSaldoInicial(saldo);
        cuentaRepository.save(cuenta);
        return new SimpleEntry<>(true, saldo);
    }


    public Collection<MovimientoDTO> listarMovimientos() {
        List<Movimiento> movimientoList = movimientoRepository.findAll();
        Set<MovimientoDTO> movimientoDTO = new HashSet<>();
        for (Movimiento movimiento : movimientoList) {
            movimientoDTO.add(mapper.convertValue(movimiento, MovimientoDTO.class));

        }
        return movimientoDTO;
    }


    public MovimientoDTO buscarMovimientoPorId(Long id) {
        Movimiento movimiento = movimientoRepository.findById(id).get();
        MovimientoDTO movimientoDTO = null;
        if (movimiento.getId() != null) {
            movimientoDTO = mapper.convertValue(movimiento, MovimientoDTO.class);
        }
        return movimientoDTO;
    }

    public void guardarMovimiento(MovimientoDTO movimientoDTO) {
        saveMethod(movimientoDTO);
    }

    public void borrarMovimiento(Long id) {
        Movimiento movimiento = movimientoRepository.findById(id).get();
        movimientoRepository.deleteById(id);
    }

    public void actualizarMovimiento(MovimientoDTO movimientoDTO) {
        saveMethod(movimientoDTO);
    }


}




