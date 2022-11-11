package com.Olimpia.demo.modelo;

import java.util.List;

public class ModeloActividad {
    private ActividadKey Key;
    private String categoria;
    private Long precio;
    private List<ModeloHorario> horarios;

    public ModeloActividad() {
    }

    public ModeloActividad(ActividadKey key, String categoria, Long precio, List<ModeloHorario> horarios) {
        Key = key;
        this.categoria = categoria;
        this.precio = precio;
        this.horarios = horarios;
    }

    public ActividadKey getKey() {
        return Key;
    }

    public void setKey(ActividadKey key) {
        Key = key;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Long getPrecio() {
        return precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }

    public List<ModeloHorario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<ModeloHorario> horarios) {
        this.horarios = horarios;
    }
}
