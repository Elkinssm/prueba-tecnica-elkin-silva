package com.elkin.pruebaTecnica.controller;

import com.elkin.pruebaTecnica.service.ClienteService;
import com.elkin.pruebaTecnica.service.dto.UsuarioDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ClienteControllerTest {

    @Mock
    private ClienteService clienteService;

    private ClienteController clienteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        clienteController = new ClienteController(clienteService);
    }

    @Test
    void guardarUsuarioTestRetornaCreated() {
        // Arrange
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setNombre("Juan");
        usuario.setDireccion("Carrera 123");
        usuario.setTelefono("12345");
        usuario.setEstado(Boolean.TRUE);
        doNothing().when(clienteService).guardarCliente(usuario);

        // Act
        ResponseEntity<?> response = clienteController.guardarUsuario(usuario);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }


    @Test
    void guardarUsuarioTestLlamarMetodo() {
        // Arrange
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setNombre("Juan");
        usuario.setDireccion("Carrera 123");
        usuario.setTelefono("12345");
        usuario.setEstado(Boolean.TRUE);
        doNothing().when(clienteService).guardarCliente(usuario);

        // Act
        ResponseEntity<?> response = clienteController.guardarUsuario(usuario);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(clienteService, times(1)).guardarCliente(usuario);
    }

    @Test
    void listarClientesTest() {
        // Arrange
        List<UsuarioDTO> usuarios = new ArrayList<>();
        UsuarioDTO usuario1 = new UsuarioDTO();
        usuario1.setId(1L);
        usuario1.setNombre("Juan");
        usuario1.setDireccion("Carrera 123");
        usuario1.setTelefono("12345");
        usuario1.setEstado(Boolean.TRUE);
        usuarios.add(usuario1);
        UsuarioDTO usuario2 = new UsuarioDTO();
        usuario2.setId(2L);
        usuario2.setNombre("Pedro");
        usuario2.setDireccion("Carrera 234");
        usuario2.setTelefono("456788");
        usuario2.setEstado(Boolean.FALSE);
        usuarios.add(usuario2);
        when(clienteService.listarClientes()).thenReturn(usuarios);

        // Act
        ResponseEntity<Collection<UsuarioDTO>> response = clienteController.listarClientes();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }


}