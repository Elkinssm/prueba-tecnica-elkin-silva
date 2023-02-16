package com.elkin.pruebaTecnica.controller;

import com.elkin.pruebaTecnica.persistence.entity.TipoMovimientoEnum;
import com.elkin.pruebaTecnica.service.MovimientoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {
    private final MovimientoService movimientoService;

    public MovimientoController(MovimientoService movimientosService) {
        this.movimientoService = movimientosService;

    }


    @PostMapping
    public ResponseEntity<String> crearMovimiento(
            @RequestParam Long idCuenta,
            @RequestParam TipoMovimientoEnum tipoMovimiento,
            @RequestParam Double valor) {
        movimientoService.crearMovimiento(idCuenta, tipoMovimiento, valor);
        return new ResponseEntity<>("Movimiento creado exitosamente", HttpStatus.OK);
    }
}
