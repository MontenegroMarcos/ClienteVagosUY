package com.Olimpia.demo.modelo;

import java.util.Date;
import java.util.List;

public class ModeloActividad {

    public byte[] getByteArray() {
        return byteArray;
    }

    public ModeloActividad(byte[] byteArray, String nombreActividad,
                           List<String> categorias, Date fechayhora, float precio, String nombreCentrodeActividad, String direccionCentro) {
        this.byteArray = byteArray;
        this.nombreActividad = nombreActividad;
        this.categorias = categorias;
        this.fechayhora = fechayhora;
        this.precio = precio;
        this.nombreCentrodeActividad = nombreCentrodeActividad;
        this.direccionCentro = direccionCentro;
    }

    private byte [] byteArray;

    private String nombreActividad;
    private List<String> categorias;
    private Date fechayhora;
    private float precio;
    private String nombreCentrodeActividad;
    private String direccionCentro;

    public ModeloActividad(String nombreActividad, List<String> categorias, Date fechayhora,
                           float precio, String nombreCentrodeActividad, String direccionCentro) {
        this.nombreActividad = nombreActividad;
        this.categorias = categorias;
        this.fechayhora = fechayhora;
        this.precio = precio;
        this.nombreCentrodeActividad = nombreCentrodeActividad;
        this.direccionCentro = direccionCentro;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public List<String> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<String> categorias) {
        this.categorias = categorias;
    }

    public Date getFechayhora() {
        return fechayhora;
    }

    public void setFechayhora(Date fechayhora) {
        this.fechayhora = fechayhora;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getNombreCentrodeActividad() {
        return nombreCentrodeActividad;
    }

    public void setNombreCentrodeActividad(String nombreCentrodeActividad) {
        this.nombreCentrodeActividad = nombreCentrodeActividad;
    }

    public String getDireccionCentro() {
        return direccionCentro;
    }

    public void setDireccionCentro(String direccionCentro) {
        this.direccionCentro = direccionCentro;
    }


}
