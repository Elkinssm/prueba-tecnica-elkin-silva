//package com.elkin.pruebaTecnica.mapper;
//
//import com.elkin.pruebaTecnica.persistence.entity.Cliente;
//import com.elkin.pruebaTecnica.persistence.entity.Persona;
//import com.elkin.pruebaTecnica.service.dto.PersonaClienteDTO;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.factory.Mappers;
//
//@Mapper
//public interface PersonaClienteMapper extends Mapper {
//
//    @Mapping(target = "contrasenia", source = "cliente.contrasenia")
//    @Mapping(target = "estado", source = "cliente.estado")
//    PersonaClienteDTO toDto(Persona persona, Cliente cliente);
//
//
//
//}
