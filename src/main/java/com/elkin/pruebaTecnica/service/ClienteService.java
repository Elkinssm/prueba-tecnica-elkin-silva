package com.elkin.pruebaTecnica.service;

import com.elkin.pruebaTecnica.exceptions.AppExceptions;
import com.elkin.pruebaTecnica.persistence.entity.Cliente;
import com.elkin.pruebaTecnica.persistence.repository.ClienteRepository;
import com.elkin.pruebaTecnica.service.dto.UsuarioDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

//Logica de negocio
@Service
public class ClienteService {
    private ClienteRepository clienteRepository;
    ObjectMapper mapper;

    public ClienteService(ClienteRepository clienteRepository, ObjectMapper mapper) {
        this.clienteRepository = clienteRepository;
        this.mapper = mapper;
    }

    public void saveMethod(UsuarioDTO usuarioDTO) {
        if (usuarioDTO != null) {
            Cliente cliente = mapper.convertValue(usuarioDTO, Cliente.class);
            clienteRepository.save(cliente);
        } else {
            throw new AppExceptions("Usuario no encontrado", HttpStatus.NOT_FOUND);
        }
    }

    public Collection<UsuarioDTO> listarClientes() {
        List<Cliente> clienteList = clienteRepository.findAll();
        if (clienteList.isEmpty()) {
            throw new AppExceptions("No se encontraron resultados", HttpStatus.NOT_FOUND);
        }
        Set<UsuarioDTO> usuarioDTO = new HashSet<>();
        for (Cliente cliente : clienteList) {
            usuarioDTO.add(mapper.convertValue(cliente, UsuarioDTO.class));

        }
        return usuarioDTO;
    }


    public UsuarioDTO buscarClientePorId(Long id) {
        Cliente cliente = clienteRepository.findById(id).get();
        UsuarioDTO usuarioDTO = null;
        if (cliente.getId() != null) {
            usuarioDTO = mapper.convertValue(cliente, UsuarioDTO.class);
        }
        return usuarioDTO;
    }

    public void guardarCliente(UsuarioDTO usuarioDTO) {
        saveMethod(usuarioDTO);
    }

    public void borrarCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id).get();
        clienteRepository.deleteById(id);
    }

    public void actualizarCliente(UsuarioDTO usuarioDTO) {
        saveMethod(usuarioDTO);
    }
}
