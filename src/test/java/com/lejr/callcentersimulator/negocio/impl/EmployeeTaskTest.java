package com.lejr.callcentersimulator.negocio.impl;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.lejr.callcentersimulator.model.Call;
import com.lejr.callcentersimulator.model.Rol;

public class EmployeeTaskTest {
	
	private EmployeeTask employeeTask;
	private Queue<Call> callToWaiting;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		callToWaiting = new LinkedList<>();
		callToWaiting.add(new Call(1));
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void EmployeeTaskOperatorTest() throws InterruptedException {
		//GIVE
		employeeTask = new EmployeeTask(1, Rol.OPERATOR);
		Thread thread = new Thread(employeeTask);
		
		DispatcherImpl.getDispatcher().setCallToWaiting(callToWaiting);
		
		//WHEN
		thread.start();
		
		//THEN
		thread.join();
		assertEquals(0, DispatcherImpl.getDispatcher().getCallToWaiting().size());
	}
	
	@Test
	public void EmployeeTaskSupervisorTest() throws InterruptedException {
		//GIVE
		employeeTask = new EmployeeTask(1, Rol.SUPERVISOR);
		Thread thread = new Thread(employeeTask);
		
		DispatcherImpl.getDispatcher().setCallToWaiting(callToWaiting);
		
		//WHEN
		thread.start();
		
		//THEN
		thread.join();
		assertEquals(0, DispatcherImpl.getDispatcher().getCallToWaiting().size());
	}
	
	@Test
	public void EmployeeTaskDirectorTest() throws InterruptedException {
		//GIVE
		employeeTask = new EmployeeTask(1, Rol.DIRECTOR);
		Thread thread = new Thread(employeeTask);
		
		DispatcherImpl.getDispatcher().setCallToWaiting(callToWaiting);
		
		//WHEN
		thread.start();
		
		//THEN
		thread.join();
		assertEquals(0, DispatcherImpl.getDispatcher().getCallToWaiting().size());
	}
	
	@Test
	public void EmployeeTaskMoreTenCallTest() throws InterruptedException {
		//GIVE
		List<EmployeeTask> employeesTask = new ArrayList<EmployeeTask>();
		employeesTask.add(new EmployeeTask(0, Rol.OPERATOR));
		employeesTask.add(new EmployeeTask(0, Rol.SUPERVISOR));
		employeesTask.add(new EmployeeTask(0, Rol.DIRECTOR));
		
		Queue<Call> callsToWaiting = new LinkedList<>();
		
		for (int i = 0; i <= 10; i++) callsToWaiting.add(new Call(i));
		
		DispatcherImpl.getDispatcher().setCallToWaiting(callsToWaiting);
		
		Thread[] thread = new Thread[3];
		for (int i = 0; i < 3; i++) thread[i] = new Thread(employeesTask.get(i));
		
		
		//WHEN
		for (int i = 0; i < 3; i++) thread[i].start();
		
		//THEN
		for (int i = 0; i < 3; i++) thread[i].join();
		assertEquals(0, DispatcherImpl.getDispatcher().getCallToWaiting().size());
	}

}
