package com.Olimpia.demo.modelo;

public class ModeloCentroDeportivo {
    private String email;
    private String psw;
    private String nombre;
    private Long deuda;
    private String direccion;

    public ModeloCentroDeportivo() {
    }

    public ModeloCentroDeportivo(String email, String psw, String nombre, Long deuda, String direccion) {
        this.email = email;
        this.psw = psw;
        this.nombre = nombre;
        this.deuda = deuda;
        this.direccion = direccion;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getDeuda() {
        return deuda;
    }

    public void setDeuda(Long deuda) {
        this.deuda = deuda;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
