package com.Olimpia.demo.modelo;

public class ModeloUsuario {
    private String email;
    private String psw;
    private Integer tipoUsuario;

    public ModeloUsuario() {
    }

    public ModeloUsuario(String email, String psw) {
        this.email = email;
        this.psw = psw;
    }

    public ModeloUsuario(String email, String psw, Integer tipoUsuario) {
        this.email = email;
        this.psw = psw;
        this.tipoUsuario = tipoUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public Integer getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(Integer tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
