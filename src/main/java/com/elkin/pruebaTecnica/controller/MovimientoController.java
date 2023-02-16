package com.elkin.pruebaTecnica.controller;

import com.elkin.pruebaTecnica.service.MovimientosService;
import com.elkin.pruebaTecnica.service.dto.MovimientoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
