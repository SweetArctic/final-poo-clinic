package com.clinica.proyecto.application.dto;

import java.math.BigDecimal;

public class TratamientoDTO {
    private Long id;
    private String descripcion;
    private BigDecimal costo;
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public BigDecimal getCosto() { return costo; }
    public void setCosto(BigDecimal costo) { this.costo = costo; }
}
