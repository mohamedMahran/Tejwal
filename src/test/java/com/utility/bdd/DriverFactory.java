package com.utility.bdd;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class DriverFactory {

	static String mainWindowsHandle; // Stores current window handle

	public static WebDriver Driver;

	public static WebDriver Initialize(String Browser) {

		if (Browser.equalsIgnoreCase("Firefox")) {

			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\MMahran\\Downloads\\geckodriver-v0.11.1-win64\\geckodriver.exe");

			Driver = new FirefoxDriver();

			Driver.manage().window().maximize();

		}

		else if (Browser.equalsIgnoreCase("Chrome"))

		{
			DesiredCapabilities dc = new DesiredCapabilities();

			dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);

			dc.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

			System.setProperty("webdriver.chrome.driver", "D:\\ChromeWebDriver\\chromedriver_win32\\chromedriver.exe");

			Driver = new ChromeDriver(dc);

			Driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			Driver.manage().window().maximize();

		}

		else if (Browser.equalsIgnoreCase("opera"))

		{

			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\MMahran\\Downloads\\operadriver_win64\\operadriver.exe");

			Driver = new ChromeDriver();

			Driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			Driver.manage().window().maximize();

		}
		return Driver;
	}

	public static void takeScreenShot() throws IOException {

		File scrFile = ((TakesScreenshot) DriverFactory.Driver).getScreenshotAs(OutputType.FILE);
		// Now you can do whatever you need to do with it, for example copy
		// somewhere
		String currentDir = System.getProperty("user.dir");
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		try {
			FileUtils.copyFile(scrFile, new File(currentDir + "\\screenshots\\" + timeStamp + ".png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	protected static void checkAlert() throws InterruptedException {
		try {
			Alert alert = Driver.switchTo().alert();
			System.out.print(alert.getText());
			Thread.sleep(2000);
			alert.accept();
		} catch (UnhandledAlertException e) {
			// exception handling
			e.printStackTrace();
		}
	}

	public static String getTitle() {

		return Driver.getTitle();

	}

	public static void NavigateToOurSite(String url) {

		Driver.get(url);

		Driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	public static void destroyDriver() {

		Driver.quit();

	}

	public static void MaximizeWindow() {
		Driver.manage().window().maximize();
	}

	public static void ImplicitWait() {
		Timeouts implicitlyWait = Driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		// ReSharper disable once NotResolvedInText
		if (implicitlyWait == null)
			try {
				throw new Exception("implicitlyWait");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public static void SwitchToWindow(String handle) {

		// Switches to another window
		Driver.switchTo().window(handle);
	}

	public static void SwitchToFrame(String handle) {

		// switches to a different frame

		Driver.switchTo().frame(handle);

	}

	public static void RefreshPage() {

		Driver.navigate().refresh();

	}

	public static boolean swithToWindow(WebDriver driver, String title) {
		mainWindowsHandle = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles(); // Gets all the
															// available windows
		for (String handle : handles) {
			driver.switchTo().window(handle); // switching back to each window
												// in loop
			if (driver.getTitle().equals(title)) // Compare title and if title
													// matches stop loop and
													// return true
				return true; // We switched to window, so stop the loop and come
								// out of funcation with positive response
		}
		driver.switchTo().window(mainWindowsHandle); // Switch back to original
														// window handle
		return false; // Return false as failed to find window with given title.
	}

	public static int NumberOfIFrame() {
		// By executing a java script
		JavascriptExecutor exe = (JavascriptExecutor) Driver;
		Integer numberOfFrames = Integer.parseInt(exe.executeScript("return window.length").toString());
		System.out.println("Number of iframes on the page are " + numberOfFrames);
		return numberOfFrames;
	}

	public static void SwitchToFramesByIndex(int FrameIndex) {
		Driver.switchTo().frame(FrameIndex);
	}

	public static void MouseHoverAction(String elemText, String subElementText) throws InterruptedException {

		WebElement element = Driver.findElement(By.linkText(elemText));

		Actions action = new Actions(Driver);

		action.moveToElement(element).perform();

		WebElement subElement = Driver.findElement(By.partialLinkText(subElementText));

		action.moveToElement(subElement);

		action.click();

		action.perform();
	}

	public static void Back() {
		Driver.navigate().back();
	}

	public static WebElement waitForElement(WebElement elem) {
		WebDriverWait wait = new WebDriverWait(Driver, 30);
		return wait.until(ExpectedConditions.elementToBeClickable(elem));

	}

	public static WebElement waitForElementDisplayed(WebElement elem) {
		WebDriverWait wait = new WebDriverWait(Driver, 50);
		return wait.until(ExpectedConditions.visibilityOf(elem));

	}

	public static String waitForAllLinks(String item) {
		WebDriverWait wait = new WebDriverWait(Driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.tagName(item)));
		return item;
	}

	public static void ClearBrowserCache() throws InterruptedException {
		Driver.manage().deleteAllCookies(); // delete all cookies
		Thread.sleep(2000); // wait 2 seconds to clear cookies.
	}

	public static void SelectItemFromListBox(String Selector, String ListItem) {
		WebElement select = Driver.findElement(By.cssSelector("Selector"));

		List<WebElement> options = select.findElements(By.tagName("option"));

		for (WebElement option : options) {

			if (ListItem.equals(option.getText()))

				option.click();

		}

	}

	public static void MouseHoverAction(String MainElementText, String subElementText, String Message)
			throws InterruptedException {
		Thread.sleep(2000);

		WebElement element = Driver.findElement(By.cssSelector(MainElementText));

		Actions action = new Actions(Driver);

		action.moveToElement(element).perform();

		WebElement subElement = Driver.findElement(By.linkText(subElementText));

		action.moveToElement(subElement);

		action.click();

		action.perform();
	}

	public static void Scroll() {
		JavascriptExecutor jse = (JavascriptExecutor) Driver;
		jse.executeScript("window.scrollBy(0,500)", "");
	}

	public static String cellToString(XSSFCell cell) {
		// TODO Auto-generated method stub
		Object result;
		switch (cell.getCellType()) {

		case Cell.CELL_TYPE_NUMERIC:
			result = cell.getNumericCellValue();
			break;

		case Cell.CELL_TYPE_STRING:
			result = cell.getStringCellValue();
			break;

		case Cell.CELL_TYPE_BOOLEAN:
			result = cell.getBooleanCellValue();
			break;

		case Cell.CELL_TYPE_FORMULA:
			result = cell.getCellFormula();
			break;

		default:
			throw new RuntimeException("Unknown Cell Type");
		}

		return result.toString();
	}

	public static void AlertAction() {
		// Switching to Alert
		Alert alert = Driver.switchTo().alert();

		// Capturing alert message.
		String alertMessage = Driver.switchTo().alert().getText();

		// Displaying alert message
		System.out.println(alertMessage);

		// Accepting alert
		alert.accept();
	}

	public static void checkPageIsReady() {

		JavascriptExecutor js = (JavascriptExecutor) Driver;

		// Initially bellow given if condition will check ready state of page.
		if (js.executeScript("return document.readyState").toString().equals("complete")) {
			System.out.println("Page Is loaded.");
			return;
		}
	}

	public static void dragAnddrop(String Selector) throws InterruptedException {
		WebElement dragElementFrom = Driver.findElement(By.cssSelector(Selector));
		new Actions(Driver).dragAndDropBy(dragElementFrom, 100, 0).build().perform();

	}

	public static boolean Handle_Dynamic_Webtable() {

		// To locate table.
		WebElement mytable = Driver.findElement(By.xpath(".//*[@id='shopping-cart-table']"));
		// To locate rows of table.
		List<WebElement> rows_table = mytable.findElements(By.tagName("tr"));
		// To calculate no of rows In table.
		int rows_count = rows_table.size();

		// Loop will execute till the last row of table.
		for (int row = 0; row < rows_count; row++) {
			// To locate columns(cells) of that specific row.
			List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));
			// To calculate no of columns(cells) In that specific row.
			int columns_count = Columns_row.size();
			System.out.println("Number of cells In Row " + row + " are " + columns_count);

			// Loop will execute till the last cell of that specific row.
			for (int column = 0; column < columns_count; column++) {
				// To retrieve text from that specific cell.
				String celtext = Columns_row.get(column).getText();
				System.out
						.println("Cell Value Of row number " + row + " and column number " + column + " Is " + celtext);
			}
			System.out.println("--------------------------------------------------");
		}
		return true;

	}

}