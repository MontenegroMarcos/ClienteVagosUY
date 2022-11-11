package com.Olimpia.demo.modelo;

public class ActividadKey {
    private ModeloCentroDeportivo centroDeportivo;
    private String nombre;

    public ActividadKey() {
    }

    public ActividadKey(ModeloCentroDeportivo centroDeportivo, String nombre) {
        this.centroDeportivo = centroDeportivo;
        this.nombre = nombre;
    }

    public ModeloCentroDeportivo getCentroDeportivo() {
        return centroDeportivo;
    }

    public void setCentroDeportivo(ModeloCentroDeportivo centroDeportivo) {
        this.centroDeportivo = centroDeportivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
