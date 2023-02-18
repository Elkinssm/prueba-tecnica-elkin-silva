package com.elkin.pruebaTecnica.controller;

import com.elkin.pruebaTecnica.persistence.entity.Movimiento;
import com.elkin.pruebaTecnica.service.MovimientosService;
import com.elkin.pruebaTecnica.service.dto.MovimientoClienteDTO;
import com.elkin.pruebaTecnica.service.dto.MovimientoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {
    private final MovimientosService movimientosService;

    public MovimientoController(MovimientosService movimientosService) {
        this.movimientosService = movimientosService;

    }


    @PostMapping
    public ResponseEntity<Void> createMovimiento(@RequestBody MovimientoDTO movimientoDTO) {
        movimientosService.saveMovimiento(movimientoDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<Object> getMovimientosConDatos() {
        List<Map<String, Object>> movimientosConDatos = movimientosService.getMovimientosConDatosDeCuenta();
        return ResponseEntity.ok(movimientosConDatos);
    }


    @GetMapping("/todos")
    public ResponseEntity<List<Movimiento>> listarMovimientos() {
        List<Movimiento> movimientos = movimientosService.listarMovimientos();
        return new ResponseEntity<>(movimientos, HttpStatus.OK);
    }


    @GetMapping("/por-fecha-y-usuario")
    public ResponseEntity<List<MovimientoClienteDTO>> getMovimientosByClienteAndFecha(
            @RequestParam("clienteId") Long clienteId,
            @RequestParam("fecha") String fecha) {
        List<MovimientoClienteDTO> movimientos = movimientosService.getMovimientosByClienteAndFecha(clienteId, fecha);
        return ResponseEntity.ok(movimientos);
    }
}





