package com.elkin.pruebaTecnica.service;

import com.elkin.pruebaTecnica.exceptions.AppExceptions;
import com.elkin.pruebaTecnica.mapper.UsuarioDTOtoCliente;
import com.elkin.pruebaTecnica.persistence.entity.Cliente;
import com.elkin.pruebaTecnica.persistence.repository.ClienteRepository;
import com.elkin.pruebaTecnica.service.dto.UsuarioInDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Logica de negocio
@Service
public class UsuarioService {

    private final ClienteRepository clienteRepository;
    private final UsuarioDTOtoCliente mapper;

    public UsuarioService(ClienteRepository clienteRepository, UsuarioDTOtoCliente mapper) {
        this.clienteRepository = clienteRepository;
        this.mapper = mapper;
    }

    public Cliente crearCliente(UsuarioInDTO usuarioInDTO) {
        Cliente cliente = mapper.map(usuarioInDTO);
        return this.clienteRepository.save(cliente);

    }


    public List<Cliente> findAll() {
        return this.clienteRepository.findAll();
    }


    public void deleteById(Long id) {
        Optional<Cliente> optionalCliente = this.clienteRepository.findById(id);
        if (optionalCliente.isEmpty()) {
            throw new AppExceptions("Cliente no encontrado", HttpStatus.NOT_FOUND);
        }
        this.clienteRepository.deleteById(id);
    }
}
