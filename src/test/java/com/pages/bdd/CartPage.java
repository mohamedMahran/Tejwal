package com.pages.bdd;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.xml.sax.SAXException;

import com.utility.bdd.DriverFactory;

public class CartPage {
	
	@FindBy(css="span.h2")
	private WebElement _PriceTxt;
	
	@FindBy(id="flights-summary-travelers-form-first-name-0")
	private WebElement _FirstNameForFirstTraveler;
	
	@FindBy(id="flights-summary-travelers-form-first-name-1")
	private WebElement _FirstNameForSecondTraveler;
	
	@FindBy(id="flights-summary-travelers-form-last-name-0")
	private WebElement _LastNameForFirstTraveler;
	
	@FindBy(id="flights-summary-travelers-form-last-name-1")
	private WebElement _LastNameForSecondTraveler;
	
	@FindBy(id="flights-summary-travelers-form-title-0")
	private WebElement _TitleForFirstTraveller;
	
	
	@FindBy(id="flights-summary-travelers-form-title-1")
	private WebElement _TitleForSecondTraveller;
	
	@FindBy(id="flights-summary-travelers-form-contact-title")
	private WebElement _TitleForContactDetails;
	
	@FindBy(id="flights-summary-travelers-form-contact-fname")
	private WebElement _FirstNameContactDetails;
	
	@FindBy(id="flights-summary-travelers-form-contact-lname")
	private WebElement _LastNameContactDetails;
	
	@FindBy(id="flights-summary-travelers-form-contact-email")
	private WebElement _EmailContact;
	
	@FindBy(id="flights-summary-travelers-form-contact-countryid")
	private WebElement _CountryContact;
	
	@FindBy(id="flights-summary-travelers-form-contact-phone")
	private WebElement _MobileContact;
	
	@FindBy(id="flights-summary-go-to-payment-cta")
	private WebElement _ContinuePayment;
	
	public CartPage() throws ParserConfigurationException, SAXException, IOException {

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
	
	public String getTravelPrice()
	{
		return _PriceTxt.getText();
	}
	
	public void fillTravelDetail() throws InterruptedException
	{
		Select TitleDropDownListForFirstTraveller = new Select(_TitleForFirstTraveller);
		TitleDropDownListForFirstTraveller.selectByVisibleText("Mr");
		_FirstNameForFirstTraveler.sendKeys("Mohamed");
		_LastNameForFirstTraveler.sendKeys("Mahran");
		 Thread.sleep(100);
		DriverFactory.Scroll();
		 Select TitleDropDownListForSecondTraveller = new Select(_TitleForSecondTraveller);
		 TitleDropDownListForSecondTraveller.selectByVisibleText("Mrs");
		 _FirstNameForSecondTraveler.sendKeys("Soha");
		 _LastNameForSecondTraveler.sendKeys("Mohsen");
		 Thread.sleep(100); 
		 DriverFactory.Scroll();
		 _EmailContact.sendKeys("mmahran@yahoo.com");
		 Select Country = new Select(_CountryContact);
		 Country.selectByVisibleText("Saudi Arabia (+966)");
		 _MobileContact.sendKeys("1254466223");
	}
	
	public void PressContinueOnPayment() throws InterruptedException
	{
		_ContinuePayment.click();
		Thread.sleep(5000);
	}
}
