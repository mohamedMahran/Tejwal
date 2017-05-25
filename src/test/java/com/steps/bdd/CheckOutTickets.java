package com.steps.bdd;

import org.junit.Assert;

import com.pages.bdd.CartPage;
import com.pages.bdd.HomePage;
import com.pages.bdd.TripDetailsPage;
import com.sun.tools.javac.code.Attribute.Constant;
import com.utility.bdd.DriverFactory;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CheckOutTickets {
	
	 String expectedPrice="27,880";

	@When("^Client Enter Trip Information$")
	public void client_Enter_Trip_Information() throws Throwable {
	     HomePage homePage = new HomePage();
	     homePage.EnterTripInformation();
	}

	@When("^Press Search Flights$")
	public void press_Search_Flights() throws Throwable {
	     HomePage homePage = new HomePage();
		 homePage.PressSearchFlights();
		 DriverFactory.ImplicitWait();
	}

	@Then("^the search query data will aligned with any specific carrier$")
	public void the_search_query_data_will_aligned_with_any_specific_carrier() throws Throwable {
	    TripDetailsPage tripPage = new TripDetailsPage();
	    tripPage.isSearchAlignedWithSearchModule("tajawal | Complete trips near your ideal times");
	}

	@Then("^Client Filter by Emirates Airways$")
	public void client_Filter_by_British_Airways() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		TripDetailsPage tripPage = new TripDetailsPage();
	    DriverFactory.Scroll();
		tripPage.selectSpecificCarrier();
		DriverFactory.ImplicitWait();
		
	    
	}

	@Then("^Lisitng results are only the selected carrier$")
	public void lisitng_results_are_only_the_selected_carrier() throws Throwable {
	    
		TripDetailsPage tripPage = new TripDetailsPage();
		tripPage.isListingResultsAreOnlyTheSelectedCarrier("Emirates");
		tripPage.selectTrip();
		Assert.assertEquals(expectedPrice, tripPage.getTravelPrice());
	}

	@When("^Client Enter Contact Details$")
	public void client_Enter_Contact_Details() throws Throwable {
		CartPage cart =new CartPage();
		DriverFactory.ImplicitWait();
		Assert.assertEquals(expectedPrice, cart.getTravelPrice());
		cart.fillTravelDetail();
	
	}

	@When("^Press Continue to Payment$")
	public void press_Continue_to_Payment() throws Throwable {
		CartPage cart =new CartPage();
		DriverFactory.ImplicitWait();
		cart.PressContinueOnPayment();
		DriverFactory.takeScreenShot();
	}


}
