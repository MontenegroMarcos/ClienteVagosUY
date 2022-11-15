package com.Olimpia.demo.modelo;

public class ModeloEmpleado {
    private String email;
    private String nombre;
    private String psw;
    private Long saldoMensual;
    private Long gastoAdicional;
    private Long saldoActual;
    private ModeloEmpresa empresa;

    public ModeloEmpleado() {
    }

    public ModeloEmpleado(String email, String nombre, String psw, Long saldoMensual, Long gastoAdicional, Long saldoActual, ModeloEmpresa empresa) {
        this.email = email;
        this.nombre = nombre;
        this.psw = psw;
        this.saldoMensual = saldoMensual;
        this.gastoAdicional = gastoAdicional;
        this.saldoActual = saldoActual;
        this.empresa = empresa;
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

    public Long getGastoAdicional() {
        return gastoAdicional;
    }

    public void setGastoAdicional(Long gastoAdicional) {
        this.gastoAdicional = gastoAdicional;
    }

    public Long getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(Long saldoActual) {
        this.saldoActual = saldoActual;
    }

    public ModeloEmpresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(ModeloEmpresa empresa) {
        this.empresa = empresa;
    }
}
