package com.lejr.callcentersimulator.model;

import java.util.Random;

public class Call {
	private int id;
	private int duration;
	
	public Call (int id) {
		this.id = id;
		this.duration = new Random().nextInt(5) + 5;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public int getId() {
		return id;
	}
	
	public int getDuration() {
		return duration;
	}
}
