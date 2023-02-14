package com.elkin.pruebaTecnica.mapper;

import com.elkin.pruebaTecnica.persistence.entity.Cliente;
import com.elkin.pruebaTecnica.service.dto.UsuarioInDTO;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDTOtoCliente implements IMapper<UsuarioInDTO, Cliente> {
    @Override
    public Cliente map(UsuarioInDTO in) {
        Cliente cliente = new Cliente();
        cliente.setNombre(in.getNombre());
        cliente.setDireccion(in.getDireccion());
        cliente.setTelefono(in.getTelefono());
        cliente.setContrasenia(in.getContrasenia());
        cliente.setEstado(in.getEstado());
        return cliente;
    }
}

