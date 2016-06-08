package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Matcher;

public class Server {

	public double currentLoad;
	public int capacity;

	public boolean contains(Vm theVm) {
		return true;
	}

	public Server(int capacity) {
		super();
		this.capacity = capacity;
	}
	
	

}
