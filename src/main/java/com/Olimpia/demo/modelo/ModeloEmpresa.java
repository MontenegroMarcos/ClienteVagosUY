package com.Olimpia.demo.modelo;

public class ModeloEmpresa {
    private String email;
    private String nombre;
    private String psw;

    public ModeloEmpresa(String email, String nombre, String psw) {
        this.email = email;
        this.nombre = nombre;
        this.psw = psw;
    }

    public ModeloEmpresa(){

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
}
