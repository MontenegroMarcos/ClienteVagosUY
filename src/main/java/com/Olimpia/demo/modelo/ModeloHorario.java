package com.Olimpia.demo.modelo;

public class ModeloHorario {
    private horarioKey key;

    public ModeloHorario() {
    }

    public ModeloHorario(horarioKey key) {
        this.key = key;
    }

    public horarioKey getKey() {
        return key;
    }

    public void setKey(horarioKey key) {
        this.key = key;
    }
}
