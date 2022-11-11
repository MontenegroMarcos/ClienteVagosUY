package com.Olimpia.demo.modelo;

import java.util.*;

public class ModeloActividadRealizada {
    private Date fecha;
    private ModeloActividad actividad;
    private ModeloHorario horarios;
    private List<ModeloEmpleado> empleados;
    private Integer cupos;

    public ModeloActividadRealizada() {
    }

    public ModeloActividadRealizada(Date fecha, ModeloActividad actividad, ModeloHorario horarios, ModeloEmpleado empleado, Integer cupos) {
        this.fecha = fecha;
        this.actividad = actividad;
        this.horarios = horarios;
        this.empleados = new ArrayList<>();
        this.empleados.add(empleado);
        this.cupos = cupos;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ModeloActividad getActividad() {
        return actividad;
    }

    public void setActividad(ModeloActividad actividad) {
        this.actividad = actividad;
    }

    public ModeloHorario getHorarios() {
        return horarios;
    }

    public void setHorarios(ModeloHorario horarios) {
        this.horarios = horarios;
    }

    public List<ModeloEmpleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<ModeloEmpleado> empleados) {
        this.empleados = empleados;
    }

    public Integer getCupos() {
        return cupos;
    }

    public void setCupos(Integer cupos) {
        this.cupos = cupos;
    }
}
