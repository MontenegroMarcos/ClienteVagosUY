package com.Olimpia.demo.modelo;

public class ModeloEmpleado {
    private String email;
    private String nombre;
    private String psw;
    private Long saldoMensual;
    private Long tope;
    private ModeloEmpresa empresa;

    public ModeloEmpleado() {
    }

    public ModeloEmpleado(String email, String nombre, String psw, Long saldoMensual, Long tope) {
        this.email = email;
        this.nombre = nombre;
        this.psw = psw;
        this.saldoMensual = saldoMensual;
        this.tope = tope;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public Long getSaldoMensual() {
        return saldoMensual;
    }

    public void setSaldoMensual(Long saldoMensual) {
        this.saldoMensual = saldoMensual;
    }

    public Long getTope() {
        return tope;
    }

    public void setTope(Long tope) {
        this.tope = tope;
    }

    public ModeloEmpresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(ModeloEmpresa empresa) {
        this.empresa = empresa;
    }
}
