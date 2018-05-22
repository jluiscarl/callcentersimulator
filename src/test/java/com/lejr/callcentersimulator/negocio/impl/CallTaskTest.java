package com.lejr.callcentersimulator.negocio.impl;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CallTaskTest {
	
	private CallTask callTask;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		while(DispatcherImpl.getDispatcher().getCallToWaiting().poll() != null);
	}

	@Test
	public void CallTaskOneTest() throws InterruptedException {
		//GIVE
		callTask = new CallTask(1);
		Thread thread = new Thread(callTask);
		
		//WHEN
		thread.start();
		
		//THEN
		thread.join();
		assertNotNull(DispatcherImpl.getDispatcher().getCallToWaiting());
		assertEquals(DispatcherImpl.getDispatcher().getCallToWaiting().size(), 1);
		
	}
	
	@Test
	public void CallTaskTenTest() throws InterruptedException {
		//GIVE
		callTask = new CallTask(10);
		Thread thread = new Thread(callTask);
		
		//WHEN
		thread.start();
		
		//THEN
		thread.join();
		assertNotNull(DispatcherImpl.getDispatcher().getCallToWaiting());
		assertEquals(DispatcherImpl.getDispatcher().getCallToWaiting().size(), 10);
		
	}
	
	@Test
	public void CallTaskMoreTenTest() throws InterruptedException {
		//GIVE
		int cantCall = new Random().nextInt(100) + 10;
		callTask = new CallTask(cantCall);
		Thread thread = new Thread(callTask);
		
		//WHEN
		thread.start();
		
		//THEN
		thread.join();
		assertNotNull(DispatcherImpl.getDispatcher().getCallToWaiting());
		assertEquals(DispatcherImpl.getDispatcher().getCallToWaiting().size(), cantCall);
		
	}
}
