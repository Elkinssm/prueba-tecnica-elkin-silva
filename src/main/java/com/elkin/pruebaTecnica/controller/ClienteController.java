package com.elkin.pruebaTecnica.controller;

import com.elkin.pruebaTecnica.exceptions.AppExceptions;
import com.elkin.pruebaTecnica.service.ClienteService;
import com.elkin.pruebaTecnica.service.dto.UsuarioDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping()
    public ResponseEntity<Collection<UsuarioDTO>> listarClientes() {
        return new ResponseEntity<>(clienteService.listarClientes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> clientePorId(@PathVariable Long id) {
        UsuarioDTO usuarioDTO = clienteService.buscarClientePorId(id);
        return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> guardarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        clienteService.guardarCliente(usuarioDTO);
        return new ResponseEntity<>("Cliente creado correctamente", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        clienteService.actualizarCliente(id, usuarioDTO);
        return new ResponseEntity<>("Cliente actualizado correctamente", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borraUsuario(@PathVariable Long id) {
        clienteService.borrarCliente(id);
        return ResponseEntity.ok().build();
    }


}
