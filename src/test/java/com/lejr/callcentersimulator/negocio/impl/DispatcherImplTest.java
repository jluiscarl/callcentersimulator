package com.lejr.callcentersimulator.negocio.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.lejr.callcentersimulator.model.Call;
import com.lejr.callcentersimulator.model.Rol;

public class DispatcherImplTest {
	
	private Call call;
	
	@Before
	public void setUpBeforeClass() throws Exception {
		call = new Call(new Random().nextInt(10));
	}

	@After
	public void tearDownAfterClass() throws Exception {
		DispatcherImpl.getDispatcher().remove(call, Rol.OPERATOR);
		DispatcherImpl.getDispatcher().remove(call, Rol.SUPERVISOR);
		DispatcherImpl.getDispatcher().remove(call, Rol.DIRECTOR);
	}

	@BeforeClass
	public static void setUp() throws Exception {
	}

	@AfterClass
	public static void tearDown() throws Exception {
	}

	@Test
	public void dispatchCallWithRolOperatorThenNullTest() throws InterruptedException {
		System.out.println("dispatchCallWithRolOperatorThenNullTest");
		//GIVE
		int id = new Random().nextInt(10);
		Rol rol = Rol.OPERATOR;
		
		//WHEN
		Call call = DispatcherImpl.getDispatcher().dispatchCall(id, rol);
		
		//THEN
		assertNull(call);
	}
	
	@Test
	public void dispatchCallWithRolOperatorThenNotNullTest() throws InterruptedException {
		System.out.println("dispatchCallWithRolOperatorThenNotNullTest");
		//GIVE
		int id = new Random().nextInt(10);
		Rol rol = Rol.OPERATOR;
		DispatcherImpl.getDispatcher().queueCall(call);
		
		//WHEN
		Call callActual = DispatcherImpl.getDispatcher().dispatchCall(id, rol);
		
		//THEN
		assertNotNull(callActual);
		assertEquals(call, callActual);
	}
	
	@Test
	public void dispatchCallWithRolSupervisorThenNullTest() throws InterruptedException {
		System.out.println("dispatchCallWithRolSupervisorThenNullTest");
		//GIVE
		int id = new Random().nextInt(10);
		Rol rol = Rol.SUPERVISOR;
		
		//WHEN
		Call call = DispatcherImpl.getDispatcher().dispatchCall(id, rol);
		
		//THEN
		assertNull(call);
	}
	
	@Test
	public void dispatchCallWithRolSupervisorThenNotNullTest() throws InterruptedException {
		System.out.println("dispatchCallWithRolSupervisorThenNotNullTest");
		//GIVE
		int id = new Random().nextInt(10);
		Rol rol = Rol.SUPERVISOR;
		DispatcherImpl.getDispatcher().queueCall(call);
		
		//WHEN
		Call callActual = DispatcherImpl.getDispatcher().dispatchCall(id, rol);
		
		//THEN
		assertNotNull(callActual);
		assertEquals(call, callActual);
	}
	
	@Test
	public void dispatchCallWithRolDirectorThenNullTest() throws InterruptedException {
		System.out.println("dispatchCallWithRolDirectorThenNullTest");
		//GIVE
		int id = new Random().nextInt(10);
		Rol rol = Rol.DIRECTOR;
		
		//WHEN
		Call call = DispatcherImpl.getDispatcher().dispatchCall(id, rol);
		
		//THEN
		assertNull(call);
	}
	
	@Test
	public void dispatchCallWithRolDirectorThenNotNullTest() throws InterruptedException {
		System.out.println("dispatchCallWithRolDirectorThenNotNullTest");
		//GIVE
		int id = new Random().nextInt(10);
		Rol rol = Rol.DIRECTOR;
		DispatcherImpl.getDispatcher().queueCall(call);
		
		//WHEN
		Call callActual = DispatcherImpl.getDispatcher().dispatchCall(id, rol);
		
		//THEN
		assertNotNull(callActual);
		assertEquals(call, callActual);
	}

}
