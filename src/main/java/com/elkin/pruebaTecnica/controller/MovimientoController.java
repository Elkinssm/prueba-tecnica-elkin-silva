package com.elkin.pruebaTecnica.controller;

import com.elkin.pruebaTecnica.service.MovimientosServiceOld;
import com.elkin.pruebaTecnica.service.dto.MovimientoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {
    private final MovimientosServiceOld movimientosServiceOld;

    public MovimientoController(MovimientosServiceOld movimientosServiceOld) {
        this.movimientosServiceOld = movimientosServiceOld;
    }

    @GetMapping()
    public ResponseEntity<Collection<MovimientoDTO>> listarMovimientos() {
        return new ResponseEntity<>(movimientosServiceOld.listarMovimientos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> movimientosPorId(@PathVariable Long id) {
        MovimientoDTO movimientoDTO = movimientosServiceOld.buscarMovimientoPorId(id);
        return new ResponseEntity<>(movimientoDTO, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> guardarMovimiento(@RequestBody MovimientoDTO movimientoDTO) {
        movimientosServiceOld.guardarMovimiento(movimientoDTO);
        return new ResponseEntity<>("Movimiento creado correctamente", HttpStatus.CREATED);
    }
}
