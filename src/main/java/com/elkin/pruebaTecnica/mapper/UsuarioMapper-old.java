//package com.elkin.pruebaTecnica.mapper;
//
//import com.elkin.pruebaTecnica.service.dto.UsuarioInDTO;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//
//@Mapper(componentModel = "spring")
//public interface UsuarioMapper {
//
//    @Mapping(source = "nombre", target = "nombre")
//    @Mapping(source = "direccion", target = "direccion")
//    @Mapping(source = "telefono", target = "telefono")
//    @Mapping(source = "contrasenia", target = "contrasenia")
//    @Mapping(source = "estado", target = "estado")
//    UsuarioInDTO toEntity(UsuarioInDTO usuarioInDTO);
//}