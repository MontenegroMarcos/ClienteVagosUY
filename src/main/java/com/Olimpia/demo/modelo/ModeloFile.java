package com.Olimpia.demo.modelo;

public class ModeloFile {
    private String originalName;
    private String contentType;
    private byte[] bytes;
    private ModeloActividad actividad;

    public ModeloFile(String originalName, String contentType, byte[] bytes, ModeloActividad actividad) {
        this.originalName = originalName;
        this.contentType = contentType;
        this.bytes = bytes;
        this.actividad = actividad;
    }

    public ModeloFile(byte[] bytes) {
        this.bytes = bytes;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        bytes = bytes;
    }

    public ModeloActividad getActividad() {
        return actividad;
    }

    public void setActividad(ModeloActividad actividad) {
        this.actividad = actividad;
    }
}
