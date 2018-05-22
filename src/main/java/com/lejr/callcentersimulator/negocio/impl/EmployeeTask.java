package com.lejr.callcentersimulator.negocio.impl;

import com.lejr.callcentersimulator.interfaces.Consumer;
import com.lejr.callcentersimulator.model.Call;
import com.lejr.callcentersimulator.model.Rol;

public class EmployeeTask implements Consumer, Runnable{
	
	private int id;
	private Rol rol;
	
	public EmployeeTask (int id, Rol rol) {
		this.id = id;
		this.rol = rol;
	}
	
	@Override
	public void run() {
		try {
			consume();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void consume() throws InterruptedException {
		while(true) {
			
			Call call = DispatcherImpl.getDispatcher().dispatchCall(id, rol);
			if (call != null) {
				Thread.sleep(call.getDuration());
				DispatcherImpl.getDispatcher().remove(call, rol);
			}
			
			if (DispatcherImpl.getDispatcher().getCallToWaiting().isEmpty()) {
				break;
			}
		}
	}

}
