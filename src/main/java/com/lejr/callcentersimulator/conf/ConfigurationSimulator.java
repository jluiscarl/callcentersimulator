package com.lejr.callcentersimulator.conf;

public class ConfigurationSimulator {
	private static final ConfigurationSimulator INSTANCE = new ConfigurationSimulator();
	
	private int numMaxOperadores;
	private int numMaxSupervisores;
	private int numMaxDirectores;
	private int numLlamadas;
	
	private ConfigurationSimulator() {}
	
	public static ConfigurationSimulator getConfigurationSimulator() {
		return INSTANCE;
	}

	public int getNumMaxOperadores() {
		return numMaxOperadores;
	}

	public void setNumMaxOperadores(int numMaxOperadores) {
		this.numMaxOperadores = numMaxOperadores;
	}

	public int getNumMaxSupervisores() {
		return numMaxSupervisores;
	}

	public void setNumMaxSupervisores(int numMaxSupervisores) {
		this.numMaxSupervisores = numMaxSupervisores;
	}

	public int getNumMaxDirectores() {
		return numMaxDirectores;
	}

	public void setNumMaxDirectores(int numMaxDirectores) {
		this.numMaxDirectores = numMaxDirectores;
	}

	public int getNumLlamadas() {
		return numLlamadas;
	}

	public void setNumLlamadas(int numLlamadas) {
		this.numLlamadas = numLlamadas;
	}
}
