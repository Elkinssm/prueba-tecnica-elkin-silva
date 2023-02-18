package com.elkin.pruebaTecnica.service;

import com.elkin.pruebaTecnica.exceptions.AppExceptions;
import com.elkin.pruebaTecnica.persistence.entity.Cuenta;
import com.elkin.pruebaTecnica.persistence.entity.Movimiento;
import com.elkin.pruebaTecnica.persistence.entity.TipoCuentaEnum;
import com.elkin.pruebaTecnica.persistence.entity.TipoMovimientoEnum;
import com.elkin.pruebaTecnica.persistence.repository.ClienteRepository;
import com.elkin.pruebaTecnica.persistence.repository.CuentaRepository;
import com.elkin.pruebaTecnica.persistence.repository.MovimientoRepository;
import com.elkin.pruebaTecnica.service.dto.MovimientoClienteDTO;
import com.elkin.pruebaTecnica.service.dto.MovimientoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MovimientosService {

    private final MovimientoRepository movimientoRepository;
    ObjectMapper mapper;
    private final CuentaRepository cuentaRepository;


    public MovimientosService(MovimientoRepository movimientoRepository, ObjectMapper mapper, CuentaRepository cuentaRepository, ClienteRepository clienteRepository) {
        this.movimientoRepository = movimientoRepository;
        this.mapper = mapper;
        this.cuentaRepository = cuentaRepository;

    }


    public List<Movimiento> listarMovimientos() {
        return movimientoRepository.findAll();
    }


    public void saveMethod(MovimientoDTO movimientoDTO) {
        if (movimientoDTO != null) {
            Movimiento movimiento = mapper.convertValue(movimientoDTO, Movimiento.class);
            Cuenta cuenta = cuentaRepository.findById(movimientoDTO.getCuentaId()).orElseThrow(() -> new AppExceptions("Cuenta no encontrada", HttpStatus.NOT_FOUND));
            double saldoAnterior = cuenta.getSaldoInicial();
            double saldoActual = movimientoDTO.getMovimiento().equals("DEBITO") ? saldoAnterior - movimiento.getValor() : saldoAnterior + movimiento.getValor();
            if (saldoActual < 0) {
                throw new AppExceptions("Saldo no disponible", HttpStatus.NOT_FOUND);
            }
            movimiento.setSaldoInicial(cuenta.getSaldoInicial());
            movimiento.setCuenta(cuenta);
            cuenta.setSaldoInicial(saldoActual);
            cuentaRepository.save(cuenta);
            movimiento.setSaldoAnterior(saldoAnterior);
            movimiento.setSaldoActual(saldoActual);
            String movimientoStr = movimientoDTO.getMovimiento().equals("CREDITO") ? "Depósito de " + movimiento.getValor() : "Retiro de " + movimiento.getValor();
            movimiento.setMovimiento(movimientoStr);
            movimiento.setTipoMovimiento(TipoMovimientoEnum.valueOf(movimientoDTO.getMovimiento()));
            movimientoRepository.save(movimiento);
        } else {
            throw new AppExceptions("Movimiento nulo", HttpStatus.BAD_REQUEST);
        }
    }

    public List<Map<String, Object>> getMovimientosConDatosDeCuenta() {
        List<Map<String, Object>> movimientosConDatos = new ArrayList<>();
        List<Movimiento> movimientos = movimientoRepository.findAll();
        for (Movimiento movimiento : movimientos) {
            Map<String, Object> movimientoConDatos = new HashMap<>();
            movimientoConDatos.put("numeroCuenta", movimiento.getCuenta().getNumeroCuenta());
            movimientoConDatos.put("tipoCuenta", movimiento.getCuenta().getTipoCuenta().toString());
            movimientoConDatos.put("saldoInicial", movimiento.getCuenta().getSaldoInicial());
            movimientoConDatos.put("estado", movimiento.getCuenta().getEstado());
            movimientoConDatos.put("movimiento", movimiento.getMovimiento());
            movimientosConDatos.add(movimientoConDatos);
        }
        return movimientosConDatos;
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

    public List<MovimientoClienteDTO> getMovimientosByClienteAndFecha(Long clienteId, String fecha) {
        List<Object[]> result = movimientoRepository.findMovimientosByClienteAndFecha(clienteId, fecha);
        if (result == null || result.isEmpty()) {
            throw new AppExceptions("No se encontraron movimientos para el cliente " + clienteId + " y fecha " + fecha, HttpStatus.BAD_REQUEST);
        }
        List<MovimientoClienteDTO> dtos = new ArrayList<>();
        for (Object[] obj : result) {
            MovimientoClienteDTO dto = new MovimientoClienteDTO();
            dto.setFecha((String) obj[0]);
            dto.setNumeroCuenta((String) obj[1]);
            dto.setCliente((String) obj[2]);
            dto.setTipoCuenta((TipoCuentaEnum) obj[3]);
            dto.setEstadoCuenta((Boolean) obj[4]);
            dto.setMovimiento((String) obj[5]);
            dto.setSaldoDisponible((Double) obj[6]);
            dto.setSaldoInicial((Double) obj[7]);
            dtos.add(dto);
        }
        return dtos;
    }

}