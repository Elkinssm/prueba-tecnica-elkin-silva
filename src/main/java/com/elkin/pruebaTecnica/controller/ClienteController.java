package com.elkin.pruebaTecnica.controller;

import com.elkin.pruebaTecnica.persistence.entity.Cliente;
import com.elkin.pruebaTecnica.service.CuentaService;
import com.elkin.pruebaTecnica.service.UsuarioService;
import com.elkin.pruebaTecnica.service.dto.CrearCuentaDTO;
import com.elkin.pruebaTecnica.service.dto.UsuarioDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final UsuarioService usuarioService;

    public ClienteController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping()
    public ResponseEntity<Collection<UsuarioDTO>> getAllUsers() {
        return new ResponseEntity<>(usuarioService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCuentaById(@PathVariable Long id) {
        UsuarioDTO usuarioDTO = usuarioService.findClienteById(id);
        return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> saveUser(@RequestBody UsuarioDTO usuarioDTO) {
        usuarioService.saveCliente(usuarioDTO);
        return new ResponseEntity<>("Address created successfully!!", HttpStatus.CREATED);
    }


}
