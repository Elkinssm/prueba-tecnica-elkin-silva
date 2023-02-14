//package com.elkin.pruebaTecnica.mapper;
//
//import com.elkin.pruebaTecnica.persistence.entity.Cliente;
//import com.elkin.pruebaTecnica.persistence.entity.Persona;
//import com.elkin.pruebaTecnica.service.dto.UsuarioInDTO;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.Mappings;
//import org.mapstruct.factory.Mappers;
//
//@Mapper(componentModel = "spring")
//public interface UsuarioMapper {
//    @Mapping(source = "nombre", target = "nombre")
//    @Mapping(source = "direccion", target = "direccion")
//    @Mapping(source = "telefono", target = "telefono")
//    @Mapping(source = "contrasenia", target = "contrasenia")
//    @Mapping(source = "estado", target = "estado")
//    Persona toPersona(UsuarioInDTO usuarioInDTO);
//
//    @Mapping(source = "nombre", target = "nombre")
//    @Mapping(source = "direccion", target = "direccion")
//    @Mapping(source = "telefono", target = "telefono")
//    @Mapping(source = "contrasenia", target = "contrasenia")
//    @Mapping(source = "estado", target = "estado")
//    Cliente toCliente(UsuarioInDTO usuarioInDTO);
//}