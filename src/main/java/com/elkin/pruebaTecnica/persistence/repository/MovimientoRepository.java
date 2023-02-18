package com.elkin.pruebaTecnica.persistence.repository;

import com.elkin.pruebaTecnica.persistence.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

    @Query("SELECT m.fecha, c.numeroCuenta, cl.nombre, c.tipoCuenta, c.estado, m.movimiento, m.saldoActual, m.saldoInicial "
            + "FROM Movimiento m "
            + "JOIN m.cuenta c "
            + "JOIN c.cliente cl "
            + "WHERE cl.id = :clienteId AND m.fecha = :fecha "
            + "ORDER BY m.fecha ASC")
    List<Object[]> findMovimientosByClienteAndFecha(Long clienteId, String fecha);


}
