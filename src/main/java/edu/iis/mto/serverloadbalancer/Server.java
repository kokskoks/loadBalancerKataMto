package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Matcher;

public class Server {

	private static final double MAXIMUM_LOAD = 100.0d;
	public double currentLoad;
	public int capacity;

	public boolean contains(Vm theVm) {
		return true;
	}

	public Server(int capacity) {
		super();
		this.capacity = capacity;
	}

	public void addVm(Vm vm) {
		currentLoad = vm.size / (double) capacity * MAXIMUM_LOAD;
		
	}
	
	

}
