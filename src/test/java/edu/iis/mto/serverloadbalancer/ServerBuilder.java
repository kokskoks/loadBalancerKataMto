package edu.iis.mto.serverloadbalancer;

public class ServerBuilder implements Builder<Server>{

	private int capacity;
	private double initialLoad;

	public ServerBuilder(){
	}

	public ServerBuilder withCapacity(int capacity) {
		this.capacity = capacity;
		return this;
	}

	public Server build() {
		Server server = new Server(capacity);
		
		addInitialLoad(server);
		
		return server;
	}

	private void addInitialLoad(Server server) {
		if(initialLoad > 0){
			int initalVmSize = (int) (initialLoad/(double)capacity * Server.MAXIMUM_LOAD);
			Vm initialVm = VmBuilder.vm().ofSize(initalVmSize).build();
			server.addVm(initialVm);
		}
	}
	
	public static ServerBuilder server() {
		return new ServerBuilder();
	}

	public ServerBuilder withCurrentLoadOf(double initialLoad) {
		this.initialLoad = initialLoad;
		return this;
	}
}
