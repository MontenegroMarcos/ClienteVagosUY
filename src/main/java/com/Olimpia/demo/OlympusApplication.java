package com.Olimpia.demo;

import com.Olimpia.demo.UI.Inicio;
import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OlympusApplication {

	public static void main(String[] args) {
		Application.launch(Inicio.class,args);
	}

}
