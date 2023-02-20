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
    public ResponseEntity<Void> crearMovimiento(@RequestBody MovimientoDTO movimientoDTO) {
        movimientosService.guardarMovimiento(movimientoDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<Object> listarMovimientosConDatos() {
        List<Map<String, Object>> movimientosConDatos = movimientosService.getMovimientosConDatosDeCuenta();
        return ResponseEntity.ok(movimientosConDatos);
    }


    @GetMapping("/todos")
    public ResponseEntity<List<Movimiento>> listarMovimientos() {
        List<Movimiento> movimientos = movimientosService.listarMovimientos();
        return new ResponseEntity<>(movimientos, HttpStatus.OK);
    }


    @GetMapping("/fecha-y-usuario")
    public ResponseEntity<List<MovimientoClienteDTO>> listarMovimientosPorClienteYFecha(@RequestParam("clienteId") Long clienteId, @RequestParam("fecha") String fecha) {
        List<MovimientoClienteDTO> movimientos = movimientosService.getMovimientosByClienteAndFecha(clienteId, fecha);
        return ResponseEntity.ok(movimientos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> movimientoPorId(@PathVariable Long id) {
        MovimientoDTO movimientoDTO = movimientosService.buscarMovimientoPorId(id);
        return new ResponseEntity<>(movimientoDTO, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarMovimiento(@PathVariable Long id, @RequestBody MovimientoDTO movimientoDTO) {
        movimientosService.actualizarMovimiento(id, movimientoDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borraMovimiento(@PathVariable Long id) {
        movimientosService.borrarMovimiento(id);
        return ResponseEntity.ok().build();
    }


}





