package edu.iis.mto.serverloadbalancer;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static edu.iis.mto.serverloadbalancer.CurrentLoadOfServerMatcher.*;
import static edu.iis.mto.serverloadbalancer.ServerBuilder.*;

import org.hamcrest.Matcher;
import org.junit.Test;

public class ServerLoadBalancerTest {
	@Test
	public void itCompiles() {
		assertThat(true, equalTo(true));
	}

	@Test
	public void balancingServerWithNoVms_serverStaysEmpty(){
		Server theServer = a(server().withCapacity(1));
		
		balancing(aServersListWith(theServer), anEmptyVmsList());
		
		assertThat(theServer, hasCurrentLoadOf(0.0d));
	}




	private void balancing(Server[] servers, Vm[] vms) {
		new ServerLoadBalancer().balance(servers, vms);
		
	}

	private Vm[] anEmptyVmsList() {
		return new Vm[0];
	}

	private Server a(ServerBuilder builder) {
		return builder.build();
	}

	private Server[] aServersListWith(Server... servers) {
		return servers;
	}




}
