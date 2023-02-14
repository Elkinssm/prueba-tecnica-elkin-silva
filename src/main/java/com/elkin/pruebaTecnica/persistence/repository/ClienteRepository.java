package com.elkin.pruebaTecnica.persistence.repository;

import com.elkin.pruebaTecnica.persistence.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
