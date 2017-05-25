package com.pages.bdd;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.xml.sax.SAXException;
import com.utility.bdd.DriverFactory;

public class HomePage {

	@FindBy(css = "#flights-search-origin-0")
	private WebElement _orignTextBox;

	@FindBy(css = "#flights-search-destination-0")
	private WebElement _destinationTextBox;

	@FindBy(css = "div.www-srchf__opt__drop__cabin.dropdown-toggle > p")
	private WebElement _classDropDwonList;

	@FindBy(id = "flights-search-departureDate-RoundTrip-0")
	private WebElement _departureDate;

	@FindBy(id = "flights-search-returnDate-RoundTrip-0")
	private WebElement _returnDate;

	@FindBy(id = "flights-search-cta")
	private WebElement _searchBTN;
	
	@FindBy(xpath="//div[@id='flights-search-open-cabin-btn']/ul/li[1]/label")
	private WebElement _economyClassDropDownList;

	@FindBy(xpath="//div[@id='flights-search-open-pax-btn']")
	private WebElement _passengarDropDownList;
	
	@FindBy(css="i.tj-icon.tj-icon--add-c")
	private WebElement _addPassengaBtn;
	
	public HomePage() throws ParserConfigurationException, SAXException, IOException {

		if (DriverFactory.Driver != null) {
			PageFactory.initElements(DriverFactory.Driver, this);
		} else {
			try {
				throw new Exception("Driver doesn't instintiated");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void EnterTripInformation() throws InterruptedException {
		
		_orignTextBox.sendKeys("cairo");

		_destinationTextBox.sendKeys("Dubai");

		_departureDate.click();

		Thread.sleep(300);

		List<WebElement> DepartureAllDates = DriverFactory.Driver
				.findElements(By.xpath("/html/body/div[3]/div[1]/table/tbody/tr/td"));

		for (WebElement ele : DepartureAllDates) {
			String date = ele.getText();

			if (date.equalsIgnoreCase("28")) {
				ele.click();
				break;
			}

		}
		_returnDate.click();

		Thread.sleep(300);
		
		List<WebElement> ReturnAllDates = DriverFactory.Driver
				.findElements(By.xpath("/html/body/div[4]/div[2]/table/tbody/tr/td"));

		for (WebElement ele : ReturnAllDates) {
			String date = ele.getText();

			if (date.equalsIgnoreCase("31")) {
				ele.click();
				break;
			}

		}
		
		_classDropDwonList.click();
		
		_economyClassDropDownList.click();
		
		_passengarDropDownList.click();
		
		_addPassengaBtn.click();

	}

	public void PressSearchFlights() {
		_searchBTN.click();
	}

}
