package com.elkin.pruebaTecnica.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cuenta")
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String numeroCuenta;
    private TipoCuentaEnum tipoCuenta;
    private Double saldoInicial;
    private Boolean estado;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

//    public Cuenta(String numeroCuenta, TipoCuentaEnum tipoCuenta, Double saldoInicial, Boolean estado, Long clienteId) {
//        this.numeroCuenta = numeroCuenta;
//        this.tipoCuenta = tipoCuenta;
//        this.saldoInicial = saldoInicial;
//        this.estado = estado;
//        this.cliente = new Cliente();
//        this.cliente.setId(clienteId);
//    }
}
