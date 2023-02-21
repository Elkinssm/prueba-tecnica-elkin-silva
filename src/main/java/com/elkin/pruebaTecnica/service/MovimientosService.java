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

import java.util.*;

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


    public Collection<MovimientoDTO> listarMovimientos() {
        List<Movimiento> movimientoList = movimientoRepository.findAll();
        if (movimientoList.isEmpty()) {
            throw new AppExceptions("No se encontraron movimientos registrados", HttpStatus.NOT_FOUND);
        }
        Set<MovimientoDTO> movimientoDTOs = new HashSet<>();
        for (Movimiento movimiento : movimientoList) {
            MovimientoDTO dto = mapper.convertValue(movimiento, MovimientoDTO.class);
            dto.setCuentaId(movimiento.getCuenta().getId());
            movimientoDTOs.add(dto);
        }
        return movimientoDTOs;
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

    public MovimientoDTO buscarMovimientoPorId(Long id) {
        Movimiento movimiento = movimientoRepository.findById(id).orElse(null);
        if (movimiento == null) {
            throw new AppExceptions("No se encontró el movimiento con el id proporcionado", HttpStatus.NOT_FOUND);
        }
        MovimientoDTO dto = mapper.convertValue(movimiento, MovimientoDTO.class);
        dto.setCuentaId(movimiento.getCuenta().getId());
        return dto;
    }

    public void guardarMovimiento(MovimientoDTO movimientoDTO) {
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

    public void borrarMovimiento(Long id) {
        Optional<Movimiento> movimientoOptional = movimientoRepository.findById(id);
        if (movimientoOptional.isPresent()) {
            Movimiento movimiento = movimientoOptional.get();
            movimientoRepository.delete(movimiento);
        } else {
            throw new AppExceptions("El movimiento con ID " + id + " no existe", HttpStatus.NOT_FOUND);
        }
    }


    public void actualizarMovimiento(Long id, MovimientoDTO movimientoDTO) {
        Optional<Movimiento> movimientoOptional = movimientoRepository.findById(id);
        if (movimientoOptional.isPresent()) {
            Movimiento movimiento = movimientoOptional.get();
            movimiento.setFecha(movimientoDTO.getFecha());
            movimiento.setMovimiento(movimientoDTO.getMovimiento());
            movimiento.setValor(movimientoDTO.getValor());
            movimiento.setSaldo(movimientoDTO.getSaldo());
            movimientoRepository.save(movimiento);
        } else {
            throw new AppExceptions("La movimiento con ID " + id + " no existe", HttpStatus.NOT_FOUND);
        }
    }

}