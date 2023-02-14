package com.elkin.pruebaTecnica.service;

import com.elkin.pruebaTecnica.exceptions.AppExceptions;
import com.elkin.pruebaTecnica.persistence.entity.Cuenta;
import com.elkin.pruebaTecnica.persistence.repository.CuentaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaService {
    private final CuentaRepository cuentaRepository;

    public CuentaService(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    public Cuenta crearCuenta(Cuenta cuenta) {
        if (cuenta.getNumeroCuenta() == null || cuenta.getTipoCuenta() == null || cuenta.getSaldoInicial() == null || cuenta.getEstado() == null) {
            throw new AppExceptions("Faltan datos para completar la cuenta", HttpStatus.BAD_REQUEST);
        }
        Optional<Cuenta> optionalCuenta = this.cuentaRepository.findByNumeroCuenta(cuenta.getNumeroCuenta());
        if (optionalCuenta.isPresent()) {
            throw new AppExceptions("La cuenta ya existe", HttpStatus.CONFLICT);
        }
        return this.cuentaRepository.save(cuenta);
    }

    public List<Cuenta> findAll() {
        List<Cuenta> cuentas = this.cuentaRepository.findAll();
        if (cuentas.isEmpty()) {
            throw new AppExceptions("No se encontraron resultados", HttpStatus.NOT_FOUND);
        }
        return cuentas;
    }


    private void validarId(Long id, Cuenta cuentaActualizada) {
        if (!id.equals(cuentaActualizada.getId())) {
            throw new AppExceptions("El id de la cuenta no es válido", HttpStatus.BAD_REQUEST);
        }
    }

    public void deleteById(Long id) {
        Optional<Cuenta> optionalCuenta = this.cuentaRepository.findById(id);
        if (optionalCuenta.isEmpty()) {
            throw new AppExceptions("No se encontró la cuenta especificada", HttpStatus.NOT_FOUND);
        }
        this.cuentaRepository.deleteById(id);
    }

    public void actualizarCuenta(Long id, Cuenta nuevaCuenta) {
        Optional<Cuenta> optionalCuenta = this.cuentaRepository.findById(id);
        if (optionalCuenta.isEmpty()) {
            throw new AppExceptions("Cuenta no encontrada", HttpStatus.NOT_FOUND);
        }
        validarId(id, nuevaCuenta);
        Cuenta cuentaExistente = optionalCuenta.get();
        cuentaExistente.setNumeroCuenta(nuevaCuenta.getNumeroCuenta());
        cuentaExistente.setTipoCuenta(nuevaCuenta.getTipoCuenta());
        cuentaExistente.setSaldoInicial(nuevaCuenta.getSaldoInicial());
        cuentaExistente.setEstado(nuevaCuenta.getEstado());
        this.cuentaRepository.save(cuentaExistente);

    }

    public void actualizarCuentaParcial(Long id, Cuenta cuentaActualizada) {
        Optional<Cuenta> optionalCuenta = this.cuentaRepository.findById(id);
        if (optionalCuenta.isEmpty()) {
            throw new AppExceptions("Cuenta no encontrada", HttpStatus.NOT_FOUND);
        }
        validarId(id, cuentaActualizada);
        Cuenta cuentaExistente = optionalCuenta.get();
        cuentaExistente.setNumeroCuenta(cuentaActualizada.getNumeroCuenta() != null ? cuentaActualizada.getNumeroCuenta() : cuentaExistente.getNumeroCuenta());
        cuentaExistente.setTipoCuenta(cuentaActualizada.getTipoCuenta() != null ? cuentaActualizada.getTipoCuenta() : cuentaExistente.getTipoCuenta());
        cuentaExistente.setSaldoInicial(cuentaActualizada.getSaldoInicial() != null ? cuentaActualizada.getSaldoInicial() : cuentaExistente.getSaldoInicial());
        cuentaExistente.setEstado(cuentaActualizada.getEstado() != null ? cuentaActualizada.getEstado() : cuentaExistente.getEstado());
        this.cuentaRepository.save(cuentaExistente);
    }
}
