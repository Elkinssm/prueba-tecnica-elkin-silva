package com.elkin.pruebaTecnica.service;

import com.elkin.pruebaTecnica.exceptions.AppExceptions;
import com.elkin.pruebaTecnica.persistence.entity.Cliente;
import com.elkin.pruebaTecnica.persistence.entity.Cuenta;
import com.elkin.pruebaTecnica.persistence.entity.TipoCuentaEnum;
import com.elkin.pruebaTecnica.persistence.repository.ClienteRepository;
import com.elkin.pruebaTecnica.persistence.repository.CuentaRepository;
import com.elkin.pruebaTecnica.service.dto.CrearCuentaDTO;
import com.elkin.pruebaTecnica.service.dto.CuentaOutDTO;
import com.elkin.pruebaTecnica.service.dto.UsuarioDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CuentaService {
    //    private final CuentaRepository cuentaRepository;
//    private final ClienteRepository clienteRepository;
//
//    ObjectMapper mapper;
//
//    public CuentaService(CuentaRepository cuentaRepository, ClienteRepository clienteRepository, ObjectMapper mapper) {
//        this.cuentaRepository = cuentaRepository;
//        this.clienteRepository = clienteRepository;
//        this.mapper = mapper;
//    }
//
//    //    public Cuenta crearCuenta(Long clienteId, Cuenta cuenta) {
////        if (cuenta.getNumeroCuenta() == null || cuenta.getTipoCuenta() == null || cuenta.getSaldoInicial() == null || cuenta.getEstado() == null) {
////            throw new AppExceptions("Faltan datos para completar la cuenta", HttpStatus.BAD_REQUEST);
////        }
////        Optional<Cliente> optionalCliente = clienteRepository.findById(clienteId);
////        if (!optionalCliente.isPresent()) {
////            throw new AppExceptions("El cliente con el id " + clienteId + " no existe", HttpStatus.NOT_FOUND);
////        }
////        Cliente cliente = optionalCliente.get();
////        cuenta.setCliente(cliente);
////        return this.cuentaRepository.save(cuenta);
////    }
////    public Cuenta crearCuenta(Cuenta nuevaCuenta) {
////        Cliente cliente = clienteRepository.findById(nuevaCuenta.getCliente().getId())
////                .orElseThrow(() -> new AppExceptions("El cliente no existe", HttpStatus.NOT_FOUND));
////        nuevaCuenta.setCliente(cliente);
////        return cuentaRepository.save(nuevaCuenta);
////    }
//    public Cuenta crearCuenta(CrearCuentaDTO crearCuentaDTO) {
//        Cuenta cuenta = mapper.convertValue(crearCuentaDTO, Cuenta.class);
//        Cliente cliente = clienteRepository.findById(crearCuentaDTO.getClienteId()).orElseThrow(() -> new AppExceptions("El cliente no existe", HttpStatus.NOT_FOUND));
//        cuenta.setCliente(cliente);
//        return cuentaRepository.save(cuenta);
//
//    }
//
//    public Collection<CrearCuentaDTO> findAll() {
//        List<Cuenta> cuentasBuscar = this.cuentaRepository.findAll();
//        if (cuentasBuscar.isEmpty()) {
//            throw new AppExceptions("No se encontraron resultados", HttpStatus.NOT_FOUND);
//        }
//        Set<CrearCuentaDTO> cuentaOutDTO = new HashSet<>();
//        for (Cuenta cuentas : cuentasBuscar) {
//            cuentaOutDTO.add(mapper.convertValue(cuentas, CrearCuentaDTO.class));
//        }
//        return cuentaOutDTO;
//    }
//
//
//    private void validarId(Long id, Cuenta cuentaActualizada) {
//        if (!id.equals(cuentaActualizada.getId())) {
//            throw new AppExceptions("El id de la cuenta no es válido", HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    public void deleteById(Long id) {
//        Optional<Cuenta> optionalCuenta = this.cuentaRepository.findById(id);
//        if (optionalCuenta.isEmpty()) {
//            throw new AppExceptions("No se encontró la cuenta especificada", HttpStatus.NOT_FOUND);
//        }
//        this.cuentaRepository.deleteById(id);
//    }
//
//    public void actualizarCuenta(Long id, Cuenta nuevaCuenta) {
//        Optional<Cuenta> optionalCuenta = this.cuentaRepository.findById(id);
//        if (optionalCuenta.isEmpty()) {
//            throw new AppExceptions("Cuenta no encontrada", HttpStatus.NOT_FOUND);
//        }
//        validarId(id, nuevaCuenta);
//        Cuenta cuentaExistente = optionalCuenta.get();
//        cuentaExistente.setNumeroCuenta(nuevaCuenta.getNumeroCuenta());
//        cuentaExistente.setTipoCuenta(nuevaCuenta.getTipoCuenta());
//        cuentaExistente.setSaldoInicial(nuevaCuenta.getSaldoInicial());
//        cuentaExistente.setEstado(nuevaCuenta.getEstado());
//        this.cuentaRepository.save(cuentaExistente);
//
//    }
//
//    public void actualizarCuentaParcial(Long id, Cuenta cuentaActualizada) {
//        Optional<Cuenta> optionalCuenta = this.cuentaRepository.findById(id);
//        if (optionalCuenta.isEmpty()) {
//            throw new AppExceptions("Cuenta no encontrada", HttpStatus.NOT_FOUND);
//        }
//        validarId(id, cuentaActualizada);
//        Cuenta cuentaExistente = optionalCuenta.get();
//        cuentaExistente.setNumeroCuenta(cuentaActualizada.getNumeroCuenta() != null ? cuentaActualizada.getNumeroCuenta() : cuentaExistente.getNumeroCuenta());
//        cuentaExistente.setTipoCuenta(cuentaActualizada.getTipoCuenta() != null ? cuentaActualizada.getTipoCuenta() : cuentaExistente.getTipoCuenta());
//        cuentaExistente.setSaldoInicial(cuentaActualizada.getSaldoInicial() != null ? cuentaActualizada.getSaldoInicial() : cuentaExistente.getSaldoInicial());
//        cuentaExistente.setEstado(cuentaActualizada.getEstado() != null ? cuentaActualizada.getEstado() : cuentaExistente.getEstado());
//        this.cuentaRepository.save(cuentaExistente);
//    }
    private CuentaRepository cuentaRepository;
    ObjectMapper mapper;
    private final ClienteRepository clienteRepository;

    public CuentaService(CuentaRepository cuentaRepository, ObjectMapper mapper,
                         ClienteRepository clienteRepository) {
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
            cuentaRepository.save(cuenta);
        } else {
            throw new AppExceptions("Cuenta no encontrada", HttpStatus.NOT_FOUND);
        }
    }

//    public void saveMethod(CrearCuentaDTO crearCuentaDTO) {
//        if (crearCuentaDTO != null) {
//            Cuenta cuenta = mapper.convertValue(crearCuentaDTO, Cuenta.class);
//            cuentaRepository.save(cuenta);
//        } else {
//            throw new AppExceptions("Usuario no encontrado", HttpStatus.NOT_FOUND);
//        }
//    }

    public Collection<CrearCuentaDTO> findAll() {
        List<Cuenta> cuentaList = cuentaRepository.findAll();
        if (cuentaList.isEmpty()) {
            throw new AppExceptions("No se encontraron resultados", HttpStatus.NOT_FOUND);
        }
        Set<CrearCuentaDTO> crearCuentaDTO = new HashSet<>();
        for (Cuenta cuenta : cuentaList) {
            crearCuentaDTO.add(mapper.convertValue(cuenta, CrearCuentaDTO.class));

        }
        return crearCuentaDTO;
    }


    public CrearCuentaDTO findCuentaById(Long id) {
        Cuenta cuenta = cuentaRepository.findById(id).get();
        CrearCuentaDTO crearCuentaDTO = null;
        if (cuenta.getId() != null) {
            crearCuentaDTO = mapper.convertValue(cuenta, CrearCuentaDTO.class);
        }
        return crearCuentaDTO;
    }

    public void saveCuenta(CrearCuentaDTO crearCuentaDTO) {
        saveMethod(crearCuentaDTO);
    }

    public void deleteCuenta(Long id) {
        Cuenta cuenta = cuentaRepository.findById(id).get();
        cuentaRepository.deleteById(id);
    }

    public void updateCuenta(CrearCuentaDTO crearCuentaDTO) {
        saveMethod(crearCuentaDTO);
    }
}

