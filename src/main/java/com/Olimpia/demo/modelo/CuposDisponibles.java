package com.Olimpia.demo.modelo;

public class CuposDisponibles {
    private Integer cuposRestantes;

    public CuposDisponibles(Integer cuposRestantes) {
        this.cuposRestantes = cuposRestantes;
    }

    public CuposDisponibles() {
    }

    public Integer getCuposRestantes() {
        return cuposRestantes;
    }

    public void setCuposRestantes(Integer cuposRestantes) {
        this.cuposRestantes = cuposRestantes;
    }
}
