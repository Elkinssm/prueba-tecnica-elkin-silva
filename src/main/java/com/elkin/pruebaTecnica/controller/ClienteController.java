package com.elkin.pruebaTecnica.controller;

import com.elkin.pruebaTecnica.persistence.entity.Cliente;
import com.elkin.pruebaTecnica.service.ClienteService;
import com.elkin.pruebaTecnica.service.dto.UsuarioInDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public Cliente createClient(@RequestBody UsuarioInDTO usuarioInDTO) {
        return this.clienteService.crearCliente(usuarioInDTO);
    }


}
