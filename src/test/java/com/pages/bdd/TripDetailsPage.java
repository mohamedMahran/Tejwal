package com.pages.bdd;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.xml.sax.SAXException;

import com.utility.bdd.DriverFactory;

public class TripDetailsPage {
	
	@FindBy(id="flights-results-select-cta-btn-0")
	private WebElement _SelectBtn;

	@FindBy(css="span.text-chambray.font-weight-700.h2")
	private WebElement _PriceTxt;
	
	public TripDetailsPage() throws ParserConfigurationException, SAXException, IOException {

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
	
	public void isSearchAlignedWithSearchModule(String result)
	{
		WebDriverWait wait = new WebDriverWait(DriverFactory.Driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div > div:nth-child(1) > span.search-result-item__segment-flight-no.margin-right-sm.block.margin-top-xs")));
        System.out.println(DriverFactory.getTitle());
        Assert.assertTrue(DriverFactory.getTitle().contains(result));
	}
	public void selectSpecificCarrier()
	{
		((JavascriptExecutor)DriverFactory.Driver).executeScript("$('#flights-filters-airline-leg-0-check-exclude-3').click();");
	}
	public void isListingResultsAreOnlyTheSelectedCarrier(String text)
	{
		String bodyText = DriverFactory.Driver.findElement(By.cssSelector(".search-result-item__segment-no>span.ellipsis")).getText();
		Assert.assertTrue("Text not found!", bodyText.contains(text));
	}
	
	public String getTravelPrice()
	{
		return _PriceTxt.getText();
	}
	
	public void selectTrip() throws InterruptedException
	{
		_SelectBtn.click();
		Thread.sleep(3000);
	}
}
