package com.utility.bdd;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;

public class BaseClass {

	PropertyReader prop = new PropertyReader();

	@Before("@chrome")
	public void launchChrome() {

		DriverFactory.Initialize("chrome");
	}

	@Before("@firefox")
	public void launchFirefox() {

		DriverFactory.Initialize("firefox");
	}

	@Given("^Customer Navigate to Tejawal Home Page$")
	public void customer_Navigate_to_Tejawal_Home_Page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	
		DriverFactory.ClearBrowserCache();
		DriverFactory.NavigateToOurSite(prop.readProperty("url"));
		DriverFactory.ImplicitWait();
	}

	@After("@firefox")
	public void firefoxtearDown() {
		DriverFactory.destroyDriver();
	}

	@After("@chrome")
	public void chrometearDown() {
		DriverFactory.destroyDriver();

	}

}
