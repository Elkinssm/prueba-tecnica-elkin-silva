package com.elkin.pruebaTecnica.mapper;

import com.elkin.pruebaTecnica.persistence.entity.Cliente;
import com.elkin.pruebaTecnica.persistence.entity.Persona;
import com.elkin.pruebaTecnica.service.dto.UsuarioDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    private final ModelMapper modelMapper;

    public UsuarioMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public UsuarioDTO toUsuarioDTO(Cliente cliente) {
        return modelMapper.map(cliente, UsuarioDTO.class);
    }

    public Cliente toCliente(UsuarioDTO usuarioDTO) {
        return modelMapper.map(usuarioDTO, Cliente.class);
    }

    public Cliente toCliente(Persona persona, UsuarioDTO usuarioDTO) {
        Cliente cliente = modelMapper.map(persona, Cliente.class);
        modelMapper.map(usuarioDTO, cliente);
        return cliente;
    }

}