package com.Olimpia.demo.modelo;

import java.util.Date;

public class CentroFechas {

    private String emailCentro;
    private Date fechaInicio;
    private Date fechaFin;

    public CentroFechas(){}

    public CentroFechas(String emailCentro, Date fechaInicio, Date fechaFin) {
        this.emailCentro = emailCentro;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public String getEmailCentro() {
        return emailCentro;
    }

    public void setEmailCentro(String emailCentro) {
        this.emailCentro = emailCentro;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
}
