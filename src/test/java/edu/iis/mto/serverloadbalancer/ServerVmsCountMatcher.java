package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class ServerVmsCountMatcher extends TypeSafeMatcher<Server> {

	private int expectedCountOfVms;

	public ServerVmsCountMatcher(int expectedCountOfVms) {
		this.expectedCountOfVms = expectedCountOfVms;
	}

	public void describeTo(Description description) {
		description.appendText("a server with vms count of ").appendValue(expectedCountOfVms);
		
	}

	@Override
	protected void describeMismatchSafely(Server item, Description mismatchDescription) {
		mismatchDescription.appendText("a server with vms count of ").appendValue(item.countVms());
	}
	
	@Override
	protected boolean matchesSafely(Server item) {
		return expectedCountOfVms == item.countVms();
	}

}
