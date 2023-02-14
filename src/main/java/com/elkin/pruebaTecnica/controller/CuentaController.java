package com.elkin.pruebaTecnica.controller;

import com.elkin.pruebaTecnica.persistence.entity.Cuenta;
import com.elkin.pruebaTecnica.service.CuentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {


    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @PostMapping
    public Cuenta crearCuenta(@RequestBody Cuenta cuenta) {
        return this.cuentaService.crearCuenta(cuenta);
    }

    @GetMapping
    public List<Cuenta> findAll() {
        return this.cuentaService.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        this.cuentaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> actualizarCuenta(@PathVariable("id") Long id, @RequestBody Cuenta cuenta) {
        cuentaService.actualizarCuenta(id, cuenta);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Void> actualizarCuentaParcial(@PathVariable("id") Long id, @RequestBody Cuenta cuentaActualizada) {
        cuentaService.actualizarCuentaParcial(id, cuentaActualizada);
        return ResponseEntity.noContent().build();
    }


}
