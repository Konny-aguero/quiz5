package com.logiexpress.domain;

import jakarta.persistence.*;

@Entity
public class Paquete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String codigoRastreo;

    @Column(nullable = false)
    private String descripcion;
    @Column(nullable = false)
    private Double pesoKg;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    
    private Cliente cliente;

    public Long getId() {
       return id;
    }

    public void setId(Long id) {
       this.id = id;
    }

    public String getCodigoRastreo() {
       return codigoRastreo;
    }

    public void setCodigoRastreo(String codigoRastreo) {
       this.codigoRastreo = codigoRastreo;
    }

    public String getDescripcion() {
       return descripcion;
    }

    public void setDescripcion(String descripcion) {
       this.descripcion = descripcion;
    }

    public Double getPesoKg() {
       return pesoKg;
    }

    public void setPesoKg(Double pesoKg) {
       this.pesoKg = pesoKg;
    }

    public Estado getEstado() {
       return estado;
    }

    public void setEstado(Estado estado) {
       this.estado = estado;
    }

    public Cliente getCliente() {
       return cliente;
    }

    public void setCliente(Cliente cliente) {
       this.cliente = cliente;
    }

    
}