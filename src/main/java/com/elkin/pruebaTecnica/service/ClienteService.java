package com.elkin.pruebaTecnica.service;

import com.elkin.pruebaTecnica.exceptions.AppExceptions;
import com.elkin.pruebaTecnica.persistence.entity.Cliente;
import com.elkin.pruebaTecnica.persistence.repository.ClienteRepository;
import com.elkin.pruebaTecnica.service.dto.UsuarioDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

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
            throw new AppExceptions("El usuario a guardar no puede ser nulo.", HttpStatus.NOT_FOUND);
        }
    }

    public Collection<UsuarioDTO> listarClientes() {
        List<Cliente> clienteList = clienteRepository.findAll();
        if (clienteList.isEmpty()) {
            throw new AppExceptions("No se encontraron clientes registrados.", HttpStatus.NOT_FOUND);
        }
        Set<UsuarioDTO> usuarioDTO = new HashSet<>();
        for (Cliente cliente : clienteList) {
            usuarioDTO.add(mapper.convertValue(cliente, UsuarioDTO.class));

        }
        return usuarioDTO;
    }


    public UsuarioDTO buscarClientePorId(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if (cliente == null) {
            throw new AppExceptions("No se encontró el cliente con el id proporcionado", HttpStatus.NOT_FOUND);
        }
        UsuarioDTO dto = mapper.convertValue(cliente, UsuarioDTO.class);
        dto.setId(cliente.getId());
        return dto;

    }


    public void guardarCliente(UsuarioDTO usuarioDTO) {
        saveMethod(usuarioDTO);
    }

    public void borrarCliente(Long id) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            clienteRepository.delete(cliente);
        } else {
            throw new AppExceptions("El cliente con ID " + id + " no existe", HttpStatus.NOT_FOUND);
        }
    }

    public void actualizarCliente(Long id, UsuarioDTO usuarioDTO) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            cliente.setNombre(usuarioDTO.getNombre());
            cliente.setEstado(usuarioDTO.getEstado());
            cliente.setDireccion(usuarioDTO.getDireccion());
            cliente.setContrasenia(usuarioDTO.getContrasenia());
            cliente.setTelefono(usuarioDTO.getTelefono());
            saveMethod(usuarioDTO);
        } else {
            throw new AppExceptions("El cliente con ID " + id + " no existe", HttpStatus.NOT_FOUND);
        }
    }

}
