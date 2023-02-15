package com.elkin.pruebaTecnica.service.dto;

import com.elkin.pruebaTecnica.persistence.entity.Cliente;
import lombok.Data;

@Data
public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String contrasenia;
    private Boolean estado;

//    public static UsuarioDTO fromEntity(Cliente cliente) {
//        UsuarioDTO usuarioDTO = new UsuarioDTO();
//        usuarioDTO.setId(cliente.getId());
//        usuarioDTO.setNombre(cliente.getNombre());
//        usuarioDTO.setDireccion(cliente.getDireccion());
//        usuarioDTO.setTelefono(cliente.getTelefono());
//        usuarioDTO.setContrasenia(cliente.getContrasenia());
//        usuarioDTO.setEstado(cliente.getEstado());
//        return usuarioDTO;
//    }

}