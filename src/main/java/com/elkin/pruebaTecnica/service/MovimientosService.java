package com.elkin.pruebaTecnica.service;

import com.elkin.pruebaTecnica.exceptions.AppExceptions;
import com.elkin.pruebaTecnica.persistence.entity.Movimiento;
import com.elkin.pruebaTecnica.persistence.repository.MovimientoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovimientosService {

    private final MovimientoRepository movimientoRepository;

    public MovimientosService(MovimientoRepository movimientoRepository) {
        this.movimientoRepository = movimientoRepository;
    }


    public Movimiento crearMovimiento(Movimiento movimiento) {
//        Cliente cliente = mapper.map(usuarioInDTO);
        return this.movimientoRepository.save(movimiento);
    }

    public List<Movimiento> findAll() {
        return this.movimientoRepository.findAll();
    }

    public void deleteById(Long id) {
        Optional<Movimiento> optionalMovimiento = this.movimientoRepository.findById(id);
        if (optionalMovimiento.isEmpty()) {
            throw new AppExceptions("El movimiento no existe", HttpStatus.NOT_FOUND);
        }
        this.movimientoRepository.deleteById(id);
    }


}




