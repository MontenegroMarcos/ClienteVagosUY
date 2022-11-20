package com.Olimpia.demo.UI;

import com.Olimpia.demo.modelo.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HacerReservaController {
    @FXML
    public Label diaSemana;
    @FXML
    public Label fechaElegida;
    @FXML
    public Label cuposDisponibles;
    @FXML
    public Button restarFecha;

    public Calendar fecha;
    public Date primerFecha;
    public ModeloHorario horario;
    public ModeloActividad actividad;
    public ModeloEmpleado empleado;

    public void init(String dia, String horaInicio, String horaFin, Integer cupos,String emailCentro,String nombreActividad,String emailEmpleado){
        diaSemana.setText(dia);
        fecha=Calendar.getInstance(); //Fecha de ahora
        fechaElegida.setText(primerDia(numeroDia(dia),Integer.parseInt(horaInicio)));
        restarFecha.setVisible(false);
        horario=new ModeloHorario(new horarioKey(dia,horaInicio,horaFin,cupos));
        actividad=obtenerActividad(emailCentro,nombreActividad);
        empleado=obtenerEmpleado(emailEmpleado);
        cuposDisponibles.setText(obtenerCupos(horario,actividad,empleado));
    }

    private String primerDia(int diaActividad,int horaInicio){
        if (fecha.get(Calendar.DAY_OF_WEEK) != diaActividad) {
            fecha.add(Calendar.DAY_OF_MONTH, (diaActividad + 7 - fecha.get(Calendar.DAY_OF_WEEK)) % 7);
        } else {
            int minOfDay = fecha.get(Calendar.HOUR_OF_DAY) * 60 + fecha.get(Calendar.MINUTE);
            if (minOfDay >= horaInicio * 60)
                fecha.add(Calendar.DAY_OF_MONTH, 7); // Bump to next week
        }
        this.primerFecha= fecha.getTime();
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yy");
        return format1.format(primerFecha);
    }

    private int numeroDia(String dia){
        int numero=-1;
        switch(dia){
            case "Domingo":
                numero=1;
                break;
            case "Lunes":
                numero=2;
                break;
            case "Martes":
                numero=3;
                break;
            case "Miercoles":
                numero=4;
                break;
            case "Jueves":
                numero=5;
                break;
            case "Viernes":
                numero=6;
                break;
            case "Sabado":
                numero=7;
                break;
        }
        return numero;
    }

    public void siguienteFecha(){
        if(fecha.getTime().compareTo(primerFecha)==0){
            restarFecha.setVisible(true);
        }
        fecha.add(Calendar.DAY_OF_MONTH,7);
        Date date = fecha.getTime();
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yy");
        fechaElegida.setText(format1.format(date));
        cuposDisponibles.setText(obtenerCupos(horario,actividad,empleado));
    }

    public void anteriorFecha(){
        fecha.add(Calendar.DAY_OF_MONTH,-7);
        Date date = fecha.getTime();
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yy");
        fechaElegida.setText(format1.format(date));
        cuposDisponibles.setText(obtenerCupos(horario,actividad,empleado));
        if(fecha.getTime().compareTo(primerFecha)==0){
            restarFecha.setVisible(false);
        }
    }

    public String obtenerCupos(ModeloHorario horario,ModeloActividad actividad,ModeloEmpleado empleado){
        Date date=null;
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yy");
        try {
            date = format1.parse(format1.format(fecha.getTime()));
        }catch(Exception e){
            e.printStackTrace();
        }
        ModeloActividadRealizada actRealizada=new ModeloActividadRealizada(date,actividad,horario,empleado,horario.getKey().getCupos(),empleado);
        HttpResponse<JsonNode> response=null;
        CuposDisponibles respuesta=null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(actRealizada);
            response = Unirest.post("http://localhost:8080/vagouy/actividadRealizada/verCupos")
                    .header("Content-Type", "application/json;charset=utf-8")
                    .body(jsonString)
                    .asJson();
            respuesta = mapper.readValue(response.getBody().toString(), CuposDisponibles.class);
        }catch(Exception e){
            e.printStackTrace();
        }
        return respuesta.getCuposRestantes().toString();
    }

    private ModeloActividad obtenerActividad(String email_centro,String nombre){
        ObjectMapper mapper = new ObjectMapper();
        String strActividad = Unirest.get("http://localhost:8080/vagouy/Actividades/actividad/"+email_centro+"/"+nombre).asString().getBody();
        ModeloActividad actividad = null;
        try {
            actividad = mapper.readValue(strActividad, ModeloActividad.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return actividad;
    }

    private ModeloHorario obtenerHorario(String nombre,String email,String dia,String horaInicio,String horaFin){
        ObjectMapper mapper = new ObjectMapper();
        String strHorario = Unirest.get("http://localhost:8080/vagouy/Actividades/obtenerHorario/"+nombre+"/"+email+"/"+dia+"/"+horaInicio+"/"+horaFin).asString().getBody();
        ModeloHorario horario = null;
        try {
            horario = mapper.readValue(strHorario, ModeloHorario.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return horario;
    }

    private ModeloEmpleado obtenerEmpleado(String email){
        ObjectMapper mapper = new ObjectMapper();
        String strEmpleado = Unirest.get("http://localhost:8080/vagouy/Empleado/"+email).asString().getBody();
        ModeloEmpleado empleado = null;
        try {
            empleado = mapper.readValue(strEmpleado, ModeloEmpleado.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empleado;
    }

    public void hacerReserva(){
        Date date=null;
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yy");
        try {
            date = format1.parse(format1.format(fecha.getTime()));
        }catch(Exception e){
            e.printStackTrace();
        }
        ModeloActividadRealizada reserva = new ModeloActividadRealizada(date,actividad,horario,empleado,horario.getKey().getCupos(),empleado);
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(reserva);
            HttpResponse<JsonNode> response = Unirest.post("http://localhost:8080/vagouy/actividadRealizada/reserva")
                    .header("Content-Type", "application/json;charset=utf-8")
                    .body(jsonString)
                    .asJson();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
