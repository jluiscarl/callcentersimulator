package com.lejr.callcentersimulator.interfaces;

import com.lejr.callcentersimulator.model.Call;
import com.lejr.callcentersimulator.model.Rol;

public interface Dispatcher {
	public Call dispatchCall(int id, Rol rol) throws InterruptedException;
}
