package com.elkin.pruebaTecnica.controller;

import com.elkin.pruebaTecnica.persistence.entity.Cliente;
import com.elkin.pruebaTecnica.service.UsuarioService;
import com.elkin.pruebaTecnica.service.dto.UsuarioDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final UsuarioService clienteService;

    public ClienteController(UsuarioService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public Cliente createClient(@RequestBody UsuarioDTO usuarioDTO) {
        return this.clienteService.crearCliente(usuarioDTO);
    }

    @GetMapping
    public List<Cliente> findAll() {
        return this.clienteService.findAll();
    }

//    @GetMapping
//    public Optional<List<Cliente>> findUser() {
//        return this.clienteService.findAllUser();
//    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        this.clienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
