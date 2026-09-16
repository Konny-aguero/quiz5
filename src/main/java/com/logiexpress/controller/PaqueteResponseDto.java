package com.logiexpress.controller;

public class PaqueteResponseDto {
    private String codigoRastreo;
    private String descripcion;
    private Double pesoKg;
    private String estado;
    
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
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}