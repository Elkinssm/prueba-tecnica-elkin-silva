package com.elkin.pruebaTecnica.service;

import com.elkin.pruebaTecnica.exceptions.AppExceptions;
import com.elkin.pruebaTecnica.persistence.entity.Cliente;
import com.elkin.pruebaTecnica.persistence.entity.Cuenta;
import com.elkin.pruebaTecnica.persistence.repository.ClienteRepository;
import com.elkin.pruebaTecnica.persistence.repository.CuentaRepository;
import com.elkin.pruebaTecnica.service.dto.CrearCuentaDTO;
import com.elkin.pruebaTecnica.service.dto.CuentaClienteIdNombre;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CuentaService {
    private CuentaRepository cuentaRepository;
    ObjectMapper mapper;
    private final ClienteRepository clienteRepository;

    public CuentaService(CuentaRepository cuentaRepository, ObjectMapper mapper, ClienteRepository clienteRepository) {
        this.cuentaRepository = cuentaRepository;
        this.mapper = mapper;
        this.clienteRepository = clienteRepository;
    }

    public void saveMethod(CrearCuentaDTO crearCuentaDTO) {
        if (crearCuentaDTO != null) {
            Long clienteId = crearCuentaDTO.getClienteId();
            Optional<Cliente> cliente = clienteRepository.findById(clienteId);
            if (cliente.isEmpty()) {
                throw new AppExceptions("ID de cliente inválido", HttpStatus.BAD_REQUEST);
            }
            Cuenta cuenta = mapper.convertValue(crearCuentaDTO, Cuenta.class);
            cuenta.setCliente(cliente.get());
            cuentaRepository.save(cuenta);
        } else {
            throw new AppExceptions("Cuenta no encontrada", HttpStatus.NOT_FOUND);
        }
    }


    public Collection<CrearCuentaDTO> listarCuentas() {
        List<Cuenta> cuentaList = cuentaRepository.findAll();
        if (cuentaList.isEmpty()) {
            throw new AppExceptions("No se encontraron cuentas registradas", HttpStatus.NOT_FOUND);
        }
        Set<CrearCuentaDTO> crearCuentaDTO = new HashSet<>();
        for (Cuenta cuenta : cuentaList) {
            CrearCuentaDTO dto = mapper.convertValue(cuenta, CrearCuentaDTO.class);
            dto.setClienteId(cuenta.getCliente().getId());
            Optional<Cliente> cliente = clienteRepository.findById(cuenta.getCliente().getId());
            crearCuentaDTO.add(dto);

        }
        return crearCuentaDTO;
    }

    public Collection<CuentaClienteIdNombre> listarCuentasConClientes() {
        List<Cuenta> cuentaList = cuentaRepository.findAll();
        if (cuentaList.isEmpty()) {
            throw new AppExceptions("No se encontraron cuentas registradas", HttpStatus.NOT_FOUND);
        }
        Set<CuentaClienteIdNombre> crearCuentaDTO = new HashSet<>();
        for (Cuenta cuenta : cuentaList) {
            CuentaClienteIdNombre dto = mapper.convertValue(cuenta, CuentaClienteIdNombre.class);
            dto.setClienteId(cuenta.getCliente().getId());
            Optional<Cliente> cliente = clienteRepository.findById(cuenta.getCliente().getId());
            if (cliente.isPresent()) {
                dto.setNombreCliente(cliente.get().getNombre());
            }
            crearCuentaDTO.add(dto);
        }
        return crearCuentaDTO;
    }


    public CrearCuentaDTO buscarCuentaPorId(Long id) {
        Cuenta cuenta = cuentaRepository.findById(id).orElse(null);
        if (cuenta == null) {
            throw new AppExceptions("No se encontró la cuenta con el id proporcionado", HttpStatus.NOT_FOUND);
        }
        CrearCuentaDTO dto = mapper.convertValue(cuenta, CrearCuentaDTO.class);
        dto.setClienteId(cuenta.getCliente().getId());
        return dto;
    }

    public void guardarCuenta(CrearCuentaDTO crearCuentaDTO) {
        saveMethod(crearCuentaDTO);
    }

    public void borrarCuenta(Long id) {
        Optional<Cuenta> cuentaOptional = cuentaRepository.findById(id);
        if (cuentaOptional.isPresent()) {
            Cuenta cuenta = cuentaOptional.get();
            cuentaRepository.delete(cuenta);
        } else {
            throw new AppExceptions("El cliente con ID " + id + " no existe", HttpStatus.NOT_FOUND);
        }
    }

    public void actualizarCuenta(Long id, CrearCuentaDTO crearCuentaDTO) {
        Optional<Cuenta> cuentaOptional = cuentaRepository.findById(id);
        if (cuentaOptional.isPresent()) {
            Cuenta cuenta = cuentaOptional.get();
            cuenta.setNumeroCuenta(crearCuentaDTO.getNumeroCuenta());
            cuenta.setTipoCuenta(crearCuentaDTO.getTipoCuenta());
            cuenta.setEstado(crearCuentaDTO.getEstado());
            cuenta.setSaldoInicial(crearCuentaDTO.getSaldoInicial());

            saveMethod(crearCuentaDTO);
        } else {
            throw new AppExceptions("El cliente con ID " + id + " no existe", HttpStatus.NOT_FOUND);
        }
    }

}

