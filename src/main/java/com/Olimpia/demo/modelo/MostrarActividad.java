package com.Olimpia.demo.modelo;

public class MostrarActividad {
    private String nombre;
    private String precio;
    private String dia;
    private String horaInicio;
    private String horaFin;
    private String cupos;

    public MostrarActividad() {
    }

    public MostrarActividad(String nombre, String precio, String dia, String horaInicio, String horaFin, String cupos) {
        this.nombre = nombre;
        this.precio = precio;
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.cupos = cupos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getCupos() {
        return cupos;
    }

    public void setCupos(String cupos) {
        this.cupos = cupos;
    }
}
