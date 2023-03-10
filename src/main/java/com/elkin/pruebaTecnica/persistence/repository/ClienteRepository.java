package com.elkin.pruebaTecnica.persistence.repository;

import com.elkin.pruebaTecnica.persistence.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {


}
