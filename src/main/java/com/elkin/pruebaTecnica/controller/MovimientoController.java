package com.elkin.pruebaTecnica.controller;

import com.elkin.pruebaTecnica.persistence.entity.Movimiento;
import com.elkin.pruebaTecnica.service.MovimientosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {
    private final MovimientosService movimientosService;

    public MovimientoController(MovimientosService movimientosService) {
        this.movimientosService = movimientosService;
    }

    @PostMapping
    public Movimiento createClient(@RequestBody Movimiento movimiento) {
        return this.movimientosService.crearMovimiento(movimiento);
    }

    @GetMapping
    public List<Movimiento> findAll() {
        return this.movimientosService.findAll();
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id")Long id){
        this.movimientosService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
