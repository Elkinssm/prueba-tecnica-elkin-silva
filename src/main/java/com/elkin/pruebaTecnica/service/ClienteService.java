package com.elkin.pruebaTecnica.service;

import com.elkin.pruebaTecnica.mapper.UsuarioDTOtoCliente;
import com.elkin.pruebaTecnica.persistence.entity.Cliente;
import com.elkin.pruebaTecnica.persistence.repository.ClienteRepository;
import com.elkin.pruebaTecnica.service.dto.UsuarioInDTO;
import org.springframework.stereotype.Service;

//Logica de negocio
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final UsuarioDTOtoCliente mapper;

    public ClienteService(ClienteRepository clienteRepository, UsuarioDTOtoCliente mapper) {
        this.clienteRepository = clienteRepository;
        this.mapper = mapper;
    }

    public Cliente crearCliente(UsuarioInDTO usuarioInDTO) {
        Cliente cliente = mapper.map(usuarioInDTO);
        return this.clienteRepository.save(cliente);

    }
}
