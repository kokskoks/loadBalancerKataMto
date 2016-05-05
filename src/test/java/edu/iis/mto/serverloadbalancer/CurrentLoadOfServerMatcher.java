package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class CurrentLoadOfServerMatcher extends  TypeSafeMatcher<Server>{

	private double expectedLoad;

	public CurrentLoadOfServerMatcher(double expectedLoad) {
		this.expectedLoad = expectedLoad;
	}

	public void describeTo(Description desc) {
		desc.appendText("a server with load of ").appendValue(expectedLoad);
	}

	@Override
	protected boolean matchesSafely(Server server) {
		return expectedLoad == server.currentLoad || Math.abs(expectedLoad - server.currentLoad) < 0.01d;
	}

}
