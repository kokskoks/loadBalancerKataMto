package edu.iis.mto.serverloadbalancer;

public class ServerBuilder {

	public ServerBuilder(){
	}

	public ServerBuilder withCapacity(int i) {
		return this;
	}

	public Server build() {
		return new Server();
	}
	
	public static ServerBuilder server() {
		return new ServerBuilder();
	}
}
