package com.Olimpia.demo.modelo;

public class idActEmpl {
    private Long idActividad;
    private String emailEmpleado;

    public idActEmpl() {
    }

    public idActEmpl(Long idActividad, String emailEmpleado) {
        this.idActividad = idActividad;
        this.emailEmpleado = emailEmpleado;
    }

    public Long getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Long idActividad) {
        this.idActividad = idActividad;
    }

    public String getEmailEmpleado() {
        return emailEmpleado;
    }

    public void setEmailEmpleado(String emailEmpleado) {
        this.emailEmpleado = emailEmpleado;
    }
}
