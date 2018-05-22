package com.lejr.callcentersimulator.app;

import java.util.Scanner;

import com.lejr.callcentersimulator.conf.ConfigurationSimulator;
import com.lejr.callcentersimulator.model.Rol;
import com.lejr.callcentersimulator.negocio.impl.CallTask;
import com.lejr.callcentersimulator.negocio.impl.EmployeeTask;

public class CallCenterSimulatorApp {

	public static void main(String[] args) {
		//SOLICITAR DATOS PARA LA SIMULACIÓN
		Scanner scan = new Scanner(System.in);
		int numMaxOperadores = getNumFromIn(scan, "Operadores");
		int numMaxSupervisores = getNumFromIn(scan, "Supervisores");
		int numMaxDirectores = getNumFromIn(scan, "Directores");
		int numLlamadas = getNumFromIn(scan, "Llamadas");
		scan.close();
		
		//CONFIGURAR DATOS PARA LA SIMULACIÓN
		ConfigurationSimulator.getConfigurationSimulator().setNumMaxOperadores(numMaxOperadores);
		ConfigurationSimulator.getConfigurationSimulator().setNumMaxSupervisores(numMaxSupervisores);
		ConfigurationSimulator.getConfigurationSimulator().setNumMaxDirectores(numMaxDirectores);
		ConfigurationSimulator.getConfigurationSimulator().setNumLlamadas(numLlamadas);
		
		
		//INICIANDO PRODUCTOR DE LLAMADAS	
		Thread producerCallS = new Thread(new CallTask(ConfigurationSimulator.getConfigurationSimulator().getNumLlamadas()));
		producerCallS.start();
		
		
		
		//GENERANDO CONSUMIDORES DE LLAMADAS CONCURRENTES
		//OPERDADORES
		Thread[] operadores = new Thread[ConfigurationSimulator.getConfigurationSimulator().getNumMaxOperadores()];
		
		for (int i = 0; i < ConfigurationSimulator.getConfigurationSimulator().getNumMaxOperadores(); i++) {
			operadores[i] = new Thread(new EmployeeTask(i, Rol.OPERATOR));
			operadores[i].start();
		}
		
		//SUPERVISORES
		Thread[] supervisores = new Thread[ConfigurationSimulator.getConfigurationSimulator().getNumMaxSupervisores()];
		
		for (int i = 0; i < ConfigurationSimulator.getConfigurationSimulator().getNumMaxSupervisores(); i++) {
			supervisores[i] = new Thread(new EmployeeTask(i, Rol.SUPERVISOR));
			supervisores[i].start();
		}
		
		//DIRECTORES
		Thread[] directores = new Thread[ConfigurationSimulator.getConfigurationSimulator().getNumMaxDirectores()];
		
		for (int i = 0; i < ConfigurationSimulator.getConfigurationSimulator().getNumMaxDirectores(); i++) {
			directores[i] = new Thread(new EmployeeTask(i, Rol.DIRECTOR));
			directores[i].start();
		}
	}

	private static Integer getNumFromIn(Scanner scan, String rol) {
		Integer result = -1;
		do {
			System.out.print("Ingrese el número de " + rol + ": ");
			String line = scan.nextLine();
			result = (isNumeric(line)) ? Integer.parseInt(line) : -1;
			
			if (result == -1)
				System.out.println("¡Error! Ingrese un número válido para la cantidad de " + rol + ".");
			
		} while(result==-1);
		return result;
	}
	
	private static boolean isNumeric (String number) {
		boolean result;
		try {
			Integer.parseInt(number);
			result = true;
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

}
