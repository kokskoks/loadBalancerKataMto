package edu.iis.mto.serverloadbalancer;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static edu.iis.mto.serverloadbalancer.CurrentLoadOfServerMatcher.*;
import static edu.iis.mto.serverloadbalancer.ServerBuilder.*;
import static edu.iis.mto.serverloadbalancer.VmBuilder.*;
import static edu.iis.mto.serverloadbalancer.ServerVmsCountMatcher.*;

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
	
	@Test
	public void balancingOneServerWithTenSlotsCapacity_andOneSlotVm_fillsTheServerWithTenPercent(){
		Server theServer = a(server().withCapacity(10));
		
		Vm theVm = a(vm().ofSize(1));
		
		balancing(aServersListWith(theServer), aVmsListWith(theVm));
		
		assertThat(theServer, hasCurrentLoadOf(10.0d));
		assertThat("server should contain the vm", theServer.contains(theVm));
	}
	
	@Test
	public void balancingTheServerWithEnoughRoom_fillsTheServerWitAllVms(){
		Server theServer = a(server().withCapacity(100));
		
		Vm theFirstVm = a(vm().ofSize(1));
		Vm theSecondVm = a(vm().ofSize(1));
		
		balancing(aServersListWith(theServer), aVmsListWith(theFirstVm, theSecondVm));
		
		assertThat(theServer, hasAVmsCountOf(2));
		assertThat("server should contain the first vm", theServer.contains(theFirstVm));
		assertThat("server should contain the second vm", theServer.contains(theSecondVm));
	}

	@Test
	public void vmsShouldBeBalancedOnLessLoadedServerFirst(){
		Server moreLoadedServer = a(server().withCapacity(100).withCurrentLoadOf(50.0d));
		Server lessLoadedServer = a(server().withCapacity(100).withCurrentLoadOf(45.0d));
		Vm theVm = a(vm().ofSize(10));
		
		balancing(aServersListWith(moreLoadedServer, lessLoadedServer), aVmsListWith(theVm));
		
		assertThat("server should contain the first vm", lessLoadedServer.contains(theVm));
		assertThat("server should not contain the first vm", !moreLoadedServer.contains(theVm));
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
