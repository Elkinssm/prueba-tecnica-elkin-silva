package com.elkin.pruebaTecnica.controller;

import com.elkin.pruebaTecnica.persistence.entity.Movimiento;
import com.elkin.pruebaTecnica.service.MovimientosService;
import com.elkin.pruebaTecnica.service.dto.CrearCuentaDTO;
import com.elkin.pruebaTecnica.service.dto.MovimientoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {
    private final MovimientosService movimientosService;

    public MovimientoController(MovimientosService movimientosService) {
        this.movimientosService = movimientosService;
    }

    @GetMapping()
    public ResponseEntity<Collection<MovimientoDTO>> getAllMovimientos() {
        return new ResponseEntity<>(movimientosService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCuentaById(@PathVariable Long id) {
        MovimientoDTO movimientoDTO = movimientosService.findMovimientoById(id);
        return new ResponseEntity<>(movimientoDTO, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> saveMovimiento(@RequestBody MovimientoDTO movimientoDTO) {
        movimientosService.saveMovimiento(movimientoDTO);
        return new ResponseEntity<>("Address created successfully!!", HttpStatus.CREATED);
    }
}
