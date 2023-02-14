package com.elkin.pruebaTecnica.controller;

import com.elkin.pruebaTecnica.service.CuentaService;
import com.elkin.pruebaTecnica.service.dto.CrearCuentaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {


//    private final CuentaService cuentaService;
//
//    public CuentaController(CuentaService cuentaService) {
//        this.cuentaService = cuentaService;
//    }
//
////    @PostMapping
////    public Cuenta crearCuenta(@RequestBody Cuenta cuenta) {
////        return this.cuentaService.crearCuenta(cuenta);
////    }
//
//    //    @PostMapping("/clientes/{clienteId}/cuentas")
////    public ResponseEntity<Cuenta> crearCuenta(@PathVariable Long clienteId, @RequestBody Cuenta cuenta) {
////        Cuenta nuevaCuenta = cuentaService.crearCuenta(clienteId, cuenta);
////        return new ResponseEntity<>(nuevaCuenta, HttpStatus.CREATED);
////    }
//    @PostMapping
//    public ResponseEntity<Cuenta> crearCuenta(@RequestBody CrearCuentaDTO crearCuentaDTO) {
//        Cuenta nuevaCuenta = cuentaService.crearCuenta(crearCuentaDTO);
//        return new ResponseEntity<>(nuevaCuenta, HttpStatus.CREATED);
//    }
//
//    @GetMapping
//    public ResponseEntity<Collection<CrearCuentaDTO>> findAll() {
//        return new ResponseEntity<>(this.cuentaService.findAll(), HttpStatus.OK);
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
//        this.cuentaService.deleteById(id);
//        return ResponseEntity.noContent().build();
//    }
//
//    @PutMapping("/update/{id}")
//    public ResponseEntity<Void> actualizarCuenta(@PathVariable("id") Long id, @RequestBody Cuenta cuenta) {
//        cuentaService.actualizarCuenta(id, cuenta);
//        return ResponseEntity.ok().build();
//    }
//
//    @PatchMapping("/update/{id}")
//    public ResponseEntity<Void> actualizarCuentaParcial(@PathVariable("id") Long id, @RequestBody Cuenta cuentaActualizada) {
//        cuentaService.actualizarCuentaParcial(id, cuentaActualizada);
//        return ResponseEntity.noContent().build();
//    }

    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @GetMapping()
    public ResponseEntity<Collection<CrearCuentaDTO>> getAllCuentas() {
        return new ResponseEntity<>(cuentaService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCuentaById(@PathVariable Long id) {
        CrearCuentaDTO crearCuentaDTO = cuentaService.findCuentaById(id);
        return new ResponseEntity<>(crearCuentaDTO, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> saveCuenta(@RequestBody CrearCuentaDTO crearCuentaDTO) {
        cuentaService.saveCuenta(crearCuentaDTO);
        return new ResponseEntity<>("Address created successfully!!", HttpStatus.CREATED);
    }

}
