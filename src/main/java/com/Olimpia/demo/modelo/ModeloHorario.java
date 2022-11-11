package com.Olimpia.demo.modelo;

public class ModeloHorario {
    private horarioKey key;
    private Integer cupos;

    public ModeloHorario() {
    }

    public ModeloHorario(horarioKey key, Integer cupos) {
        this.key = key;
        this.cupos = cupos;
    }

    public horarioKey getKey() {
        return key;
    }

    public void setKey(horarioKey key) {
        this.key = key;
    }

    public Integer getCupos() {
        return cupos;
    }

    public void setCupos(Integer cupos) {
        this.cupos = cupos;
    }
}
