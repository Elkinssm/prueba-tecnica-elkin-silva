package com.elkin.pruebaTecnica.controller;

import com.elkin.pruebaTecnica.persistence.entity.TipoCuentaEnum;
import com.elkin.pruebaTecnica.service.MovimientosService;
import com.elkin.pruebaTecnica.service.dto.MovimientoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MovimientoControllerTest {
    @Mock
    private MovimientosService movimientoService;

    private MovimientoController movimientoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        movimientoController = new MovimientoController(movimientoService);
    }

    @Test
    void crearMovimientoTest() {
        // Arrange
        MovimientoDTO movimiento = new MovimientoDTO();
        movimiento.setCuentaId(1L);
        movimiento.setFecha("10/2/2022");
        movimiento.setMovimiento("DEBITO");
        movimiento.setValor(2000.0);
        doNothing().when(movimientoService).guardarMovimiento(movimiento);

        // Act
        ResponseEntity<?> response = movimientoController.crearMovimiento(movimiento);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(movimientoService, times(1)).guardarMovimiento(movimiento);
    }

    @Test
    void listarMovimientosConDatosTest() {
        // Arrange
        List<Map<String, Object>> movimientos = new ArrayList<>();
        Map<String, Object> movimiento = new HashMap<>();
        movimiento.put("fecha", "10/2/2022");
        movimiento.put("cliente", "Juan Perez");
        movimiento.put("numeroCuenta", "45678");
        movimiento.put("tipoCuenta", TipoCuentaEnum.Ahorros);
        movimiento.put("saldoInicial", 2000.0);
        movimiento.put("estadoCuenta", Boolean.TRUE);
        movimiento.put("movimiento", "DEBITO");
        movimiento.put("saldoDisponible", 1500.0);
        movimientos.add(movimiento);
        when(movimientoService.getMovimientosConDatosDeCuenta()).thenReturn(movimientos);

        // Act
        ResponseEntity<Object> response = movimientoController.listarMovimientosConDatos();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(movimientos, response.getBody());
        verify(movimientoService, times(1)).getMovimientosConDatosDeCuenta();
    }

    @Test
    void listarMovimientosConDatosCuandoNoHayMovimientosTest() {
        // Arrange
        List<Map<String, Object>> movimientos = new ArrayList<>();
        when(movimientoService.getMovimientosConDatosDeCuenta()).thenReturn(movimientos);

        // Act
        ResponseEntity<Object> response = movimientoController.listarMovimientosConDatos();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(movimientos, response.getBody());
        verify(movimientoService, times(1)).getMovimientosConDatosDeCuenta();
    }

    @Test
    void borrarMovimientoDebeRetornarOk() {
        // Arrange
        Long idMovimiento = 1L;
        doNothing().when(movimientoService).borrarMovimiento(idMovimiento);

        // Act
        ResponseEntity<?> response = movimientoController.borraMovimiento(idMovimiento);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(movimientoService, times(1)).borrarMovimiento(idMovimiento);
    }
}