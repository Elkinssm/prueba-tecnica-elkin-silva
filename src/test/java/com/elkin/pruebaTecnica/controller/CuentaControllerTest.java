package com.elkin.pruebaTecnica.controller;

import com.elkin.pruebaTecnica.exceptions.AppExceptions;
import com.elkin.pruebaTecnica.persistence.entity.TipoCuentaEnum;
import com.elkin.pruebaTecnica.service.CuentaService;
import com.elkin.pruebaTecnica.service.dto.CrearCuentaDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class CuentaControllerTest {
    @Mock
    private CuentaService cuentaService;

    private CuentaController cuentaController;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cuentaController = new CuentaController(cuentaService);
    }

    @Test
    void guardarCuentaTest() {
        // Arrange
        CrearCuentaDTO cuenta = new CrearCuentaDTO();
        cuenta.setClienteId(1L);
        cuenta.setNumeroCuenta("45678");
        cuenta.setTipoCuenta(TipoCuentaEnum.Ahorros);
        cuenta.setSaldoInicial(2000.0);
        cuenta.setEstado(Boolean.TRUE);
        doNothing().when(cuentaService).guardarCuenta(cuenta);

        // Act
        ResponseEntity<?> response = cuentaController.guardarCuenta(cuenta);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(cuentaService, times(1)).guardarCuenta(cuenta);
    }

    @Test
    void buscarCuentaExistentePorIdTest() {
        // Arrange
        CrearCuentaDTO cuenta = new CrearCuentaDTO();
        cuenta.setClienteId(1L);
        cuenta.setNumeroCuenta("45678");
        cuenta.setTipoCuenta(TipoCuentaEnum.Ahorros);
        cuenta.setSaldoInicial(2000.0);
        cuenta.setEstado(Boolean.TRUE);
        when(cuentaService.buscarCuentaPorId(cuenta.getClienteId())).thenReturn(cuenta);

        // Act
        ResponseEntity<?> response = cuentaController.cuentaPorId(cuenta.getClienteId());

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cuenta, response.getBody());
        verify(cuentaService, times(1)).buscarCuentaPorId(cuenta.getClienteId());
    }

}