package com.lejr.callcentersimulator.negocio.impl;

import com.lejr.callcentersimulator.interfaces.Producer;
import com.lejr.callcentersimulator.model.Call;

public class CallTask implements Producer, Runnable {
	
	private int numLlamadas;
	
	public CallTask (int numLlamadas) {
		this.numLlamadas = numLlamadas;
	}

	@Override
	public void run() {
		try {
			produce();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void produce() throws InterruptedException {
		for (int i = 0; i < numLlamadas; i++) {
			Call call = new Call(i);
			System.out.println("Llamada entrante: " + call.getId());
			DispatcherImpl.getDispatcher().queueCall(call);
		}
	}

}
