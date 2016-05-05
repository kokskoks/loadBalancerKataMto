package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class CurrentLoadOfServerMatcher extends  TypeSafeMatcher<Server>{

	private static final double EPSILON = 0.01d;
	private double expectedLoad;

	public CurrentLoadOfServerMatcher(double expectedLoad) {
		this.expectedLoad = expectedLoad;
	}

	public void describeTo(Description desc) {
		desc.appendText("a server with load of ").appendValue(expectedLoad);
	}

	@Override
	protected boolean matchesSafely(Server server) {
		return compareDoubles(expectedLoad, server.currentLoad);
	}

	private boolean compareDoubles(double d2, double d1) {
		return d2 == d1 || Math.abs(d2 - d1) < EPSILON;
	}
	
	public static Matcher<? super Server> hasCurrentLoadOf(double expectedLoad) {
		return new CurrentLoadOfServerMatcher(expectedLoad);
	}

}
