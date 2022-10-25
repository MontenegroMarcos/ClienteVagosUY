package com.Olimpia.demo.modelo;

public class ModeloFile {
    private String originalName;
    private String contentType;
    private byte[] Bytes;

    public ModeloFile(String originalName, String contentType, byte[] bytes) {
        this.originalName = originalName;
        this.contentType = contentType;
        Bytes = bytes;
    }

    public ModeloFile(byte[] bytes) {
        Bytes = bytes;
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
        return Bytes;
    }

    public void setBytes(byte[] bytes) {
        Bytes = bytes;
    }
}
