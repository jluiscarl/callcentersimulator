package com.lejr.callcentersimulator.negocio.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.lejr.callcentersimulator.conf.ConfigurationSimulator;
import com.lejr.callcentersimulator.interfaces.Dispatcher;
import com.lejr.callcentersimulator.model.Call;
import com.lejr.callcentersimulator.model.Rol;

public class DispatcherImpl implements Dispatcher {

	private static DispatcherImpl INSTANCE = new DispatcherImpl();
	
	private Queue<Call> callToWaiting = new LinkedList<>();
		
	private List<Call> callsToOperators = new ArrayList<Call>();
	private List<Call> callsToSupervisors = new ArrayList<Call>();
	private List<Call> callsToDirectores = new ArrayList<Call>();	
	
	private DispatcherImpl () {}
	
	public static synchronized DispatcherImpl getDispatcher() {
		return INSTANCE;
	}
	
	@Override
	public synchronized Call dispatchCall(int id, Rol rol) throws InterruptedException {
		Call call = callToWaiting.peek();
		if (call != null) {
			System.out.println("Gestionando llamada entrante: " + call.getId());
			if (rol.equals(Rol.OPERATOR)) {
				callToWaiting.poll();
				callsToOperators.add(call);
				System.out.println("Llamada: " + call.getId() + " atendida por operador: " + id);
				return call;
			} else if (rol.equals(Rol.SUPERVISOR)) {
				if (callsToOperators.size() == ConfigurationSimulator.getConfigurationSimulator().getNumMaxOperadores()) {
					callToWaiting.poll();
					callsToSupervisors.add(call);
					System.out.println("Llamada: " + call.getId() + " atendida por supervisor: " + id);
					return call;
				}
			} else if (rol.equals(Rol.DIRECTOR)) {
				if (callsToOperators.size() == ConfigurationSimulator.getConfigurationSimulator().getNumMaxOperadores()
						&& callsToSupervisors.size() == ConfigurationSimulator.getConfigurationSimulator().getNumMaxSupervisores()) {
					callToWaiting.poll();
					callsToDirectores.add(call);
					System.out.println("Llamada: " + call.getId() + " atendida por director: " + id);
					return call;
				}
			}
		}
		return null;
	}
	
	public synchronized void remove(Call call, Rol rol) {
		switch (rol) {
		case OPERATOR:
			callsToOperators.remove(call);
			break;
		case SUPERVISOR:
			callsToSupervisors.remove(call);
			break;
		case DIRECTOR:
			callsToDirectores.remove(call);
			break;
		default:
			break;
		}
		System.out.println("Llamada finalizada: " + call.getId());
	}

	public synchronized void queueCall(Call call) {
		System.out.println("Encolando llamada para su atención: " + call.getId());
		callToWaiting.add(call);
	}

	public synchronized Queue<Call> getCallToWaiting() {
		return callToWaiting;
	}

	public synchronized void setCallToWaiting(Queue<Call> callToWaiting) {
		this.callToWaiting = callToWaiting;
	}
}
