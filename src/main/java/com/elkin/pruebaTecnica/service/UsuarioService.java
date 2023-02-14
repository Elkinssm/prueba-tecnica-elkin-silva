package com.elkin.pruebaTecnica.service;

import com.elkin.pruebaTecnica.exceptions.AppExceptions;
import com.elkin.pruebaTecnica.mapper.UsuarioMapper;
import com.elkin.pruebaTecnica.persistence.entity.Cliente;
import com.elkin.pruebaTecnica.persistence.repository.ClienteRepository;
import com.elkin.pruebaTecnica.persistence.repository.PersonaRepository;
import com.elkin.pruebaTecnica.service.dto.UsuarioDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Logica de negocio
@Service
public class UsuarioService {

    private final ClienteRepository clienteRepository;
    private final UsuarioMapper usuarioMapper;
    private final PersonaRepository personaRepository;

    public UsuarioService(ClienteRepository clienteRepository, UsuarioMapper usuarioMapper, PersonaRepository personaRepository) {
        this.clienteRepository = clienteRepository;
        this.usuarioMapper = usuarioMapper;
        this.personaRepository = personaRepository;
    }


//    public UsuarioService(ClienteRepository clienteRepository, PersonaRepository personaRepository, UsuarioMapper usuarioMapper) {
//        this.clienteRepository = clienteRepository;
//        this.personaRepository = personaRepository;
//
//        this.usuarioMapper = usuarioMapper;
//    }

    public Cliente crearCliente(UsuarioDTO usuarioDTO) {
        Cliente cliente = usuarioMapper.toCliente(usuarioDTO);
        return clienteRepository.save(cliente);
    }


//            public Cliente crearCliente(UsuarioInDTO usuarioInDTO) {
//        Cliente cliente = mapper.map(usuarioInDTO);
//        return this.clienteRepository.save(cliente);
//
//    }
//    public Cliente crearCliente(UsuarioInDTO usuarioInDTO) {
//        Cliente cliente = usuarioMapper.toEntity(usuarioInDTO);
//        return clienteRepository.save(cliente);
//    }
//    public Persona crearPersona(UsuarioInDTO usuarioInDTO) {
//        Persona persona = usuarioMapper.toPersona(usuarioInDTO);
//        return personaRepository.save(persona);
//    }


//    public UsuarioDTO crearCliente(UsuarioDTO usuarioDTO) {
//        Cliente cliente = usuarioMapper.toCliente(usuarioDTO);
//        Persona persona = usuarioMapper.toDTO(usuarioDTO);
//        cliente = clienteRepository.save(cliente);
//        persona = personaRepository.save(persona);
//        return usuarioMapper.toDTO(cliente);
//    }


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
