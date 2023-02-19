package com.elkin.pruebaTecnica.controller;

import com.elkin.pruebaTecnica.service.CuentaService;
import com.elkin.pruebaTecnica.service.dto.CuentaClienteIdNombre;
import com.elkin.pruebaTecnica.service.dto.CrearCuentaDTO;
import com.elkin.pruebaTecnica.service.dto.UsuarioDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {


    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @GetMapping("/todas")
    public ResponseEntity<Collection<CrearCuentaDTO>> listarCuentas() {
        return new ResponseEntity<>(cuentaService.listarCuentas(), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Collection<CuentaClienteIdNombre>> listarCuentasNombreId() {
        return new ResponseEntity<>(cuentaService.listarCuentasConClientes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> cuentaPorId(@PathVariable Long id) {
        CrearCuentaDTO crearCuentaDTO = cuentaService.buscarCuentaPorId(id);
        return new ResponseEntity<>(crearCuentaDTO, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> guardarCuenta(@RequestBody CrearCuentaDTO crearCuentaDTO) {
        cuentaService.guardarCuenta(crearCuentaDTO);
        return new ResponseEntity<>("Cuenta creada correctamente", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCuentaPorUsuario(@PathVariable Long id, @RequestBody CrearCuentaDTO crearCuentaDTO) {
        cuentaService.actualizarCuenta(id, crearCuentaDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borraCuenta(@PathVariable Long id) {
        cuentaService.borrarCuenta(id);
        return ResponseEntity.ok().build();
    }

}
