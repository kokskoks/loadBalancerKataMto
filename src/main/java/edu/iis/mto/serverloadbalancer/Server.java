package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matcher;

public class Server {

	public static final double MAXIMUM_LOAD = 100.0d;
	public double currentLoad;
	public int capacity;
	
	private List<Vm> vms = new ArrayList<Vm>();

	public boolean contains(Vm theVm) {
		return vms.contains(theVm);
	}

	public Server(int capacity) {
		super();
		this.capacity = capacity;
	}

	public void addVm(Vm vm) {
		currentLoad += loadOfVm(vm);
		vms.add(vm);
		
	}

	private double loadOfVm(Vm vm) {
		return vm.size / (double) capacity * MAXIMUM_LOAD;
	}

	public int countVms() {
		return vms.size();
	}

	public boolean canFit(Vm vm) {
		return (currentLoad + loadOfVm(vm)) <= MAXIMUM_LOAD;
	}
	
	

}
