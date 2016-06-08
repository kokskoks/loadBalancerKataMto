package edu.iis.mto.serverloadbalancer;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static edu.iis.mto.serverloadbalancer.CurrentLoadOfServerMatcher.*;
import static edu.iis.mto.serverloadbalancer.ServerBuilder.*;
import static edu.iis.mto.serverloadbalancer.VmBuilder.*;

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

	@Test
	public void balancingOneServerWithOneSlotCapacity_andOneSlotVm_fillsServerWithTheVm(){
		Server theServer = a(server().withCapacity(1));
		
		Vm theVm = a(vm().ofSize(1));
		
		balancing(aServersListWith(theServer), aVmsListWith(theVm));
		
		assertThat(theServer, hasCurrentLoadOf(100.0d));
		assertThat("server should contain the vm", theServer.contains(theVm));
	}



	private Vm[] aVmsListWith(Vm... vms) {
		return vms;
	}





	private void balancing(Server[] servers, Vm[] vms) {
		new ServerLoadBalancer().balance(servers, vms);
		
	}

	private Vm[] anEmptyVmsList() {
		return new Vm[0];
	}
	
	private <T> T a(Builder<T> builder){
		return builder.build();
	}


	private Server[] aServersListWith(Server... servers) {
		return servers;
	}




}
