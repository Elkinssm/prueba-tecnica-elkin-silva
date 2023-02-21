package com.elkin.pruebaTecnica.controller;

import com.elkin.pruebaTecnica.persistence.entity.Movimiento;
import com.elkin.pruebaTecnica.service.MovimientosService;
import com.elkin.pruebaTecnica.service.dto.CrearCuentaDTO;
import com.elkin.pruebaTecnica.service.dto.MovimientoClienteDTO;
import com.elkin.pruebaTecnica.service.dto.MovimientoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
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
    public ResponseEntity<?> crearMovimiento(@RequestBody MovimientoDTO movimientoDTO) {
        movimientosService.guardarMovimiento(movimientoDTO);
        return new ResponseEntity<>("Movimiento creado correctamente", HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<Object> listarMovimientosConDatos() {
        List<Map<String, Object>> movimientosConDatos = movimientosService.getMovimientosConDatosDeCuenta();
        return ResponseEntity.ok(movimientosConDatos);
    }


    @GetMapping("/todos")
    public ResponseEntity<Collection<MovimientoDTO>> listarCuentas() {
        return new ResponseEntity<>(movimientosService.listarMovimientos(), HttpStatus.OK);
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
        return new ResponseEntity<>("Movimiento actualizado correctamente", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borraMovimiento(@PathVariable Long id) {
        movimientosService.borrarMovimiento(id);
        return ResponseEntity.ok().build();
    }


}





