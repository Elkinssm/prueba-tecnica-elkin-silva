package com.elkin.pruebaTecnica.service;

import com.elkin.pruebaTecnica.exceptions.AppExceptions;
import com.elkin.pruebaTecnica.persistence.entity.Cliente;
import com.elkin.pruebaTecnica.persistence.entity.Cuenta;
import com.elkin.pruebaTecnica.persistence.repository.ClienteRepository;
import com.elkin.pruebaTecnica.persistence.repository.CuentaRepository;
import com.elkin.pruebaTecnica.service.dto.CuentaClienteIdNombre;
import com.elkin.pruebaTecnica.service.dto.CrearCuentaDTO;
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
            throw new AppExceptions("No se encontraron resultados", HttpStatus.NOT_FOUND);
        }
        Set<CrearCuentaDTO> crearCuentaDTO = new HashSet<>();
        for (Cuenta cuenta : cuentaList) {
            CrearCuentaDTO dto = mapper.convertValue(cuenta, CrearCuentaDTO.class);
            dto.setClienteId(cuenta.getCliente().getId());
            Optional<Cliente> cliente = clienteRepository.findById(cuenta.getCliente().getId());
            if (cliente.isPresent()){

            }
            crearCuentaDTO.add(dto);

        }
        return crearCuentaDTO;
    }

    public Collection<CuentaClienteIdNombre> listarCuentasConClientes() {
        List<Cuenta> cuentaList = cuentaRepository.findAll();
        if (cuentaList.isEmpty()) {
            throw new AppExceptions("No se encontraron resultados", HttpStatus.NOT_FOUND);
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
        Cuenta cuenta = cuentaRepository.findById(id).get();
        CrearCuentaDTO crearCuentaDTO = null;
        if (cuenta.getId() != null) {
            crearCuentaDTO = mapper.convertValue(cuenta, CrearCuentaDTO.class);
        }
        return crearCuentaDTO;
    }

    public void guardarCuenta(CrearCuentaDTO crearCuentaDTO) {
        saveMethod(crearCuentaDTO);
    }

    public void borrarCuenta(Long id) {
        Cuenta cuenta = cuentaRepository.findById(id).get();
        cuentaRepository.deleteById(id);
    }

    public void actualizarCuenta(CrearCuentaDTO crearCuentaDTO) {
        saveMethod(crearCuentaDTO);
    }
}

