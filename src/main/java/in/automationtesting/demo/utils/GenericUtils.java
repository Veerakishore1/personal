package in.automationtesting.demo.utils;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.Status;

public class GenericUtils extends BasicExtentReport{
	
	@BeforeSuite
	public void killDrivers() {
		try {
			Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
			Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe");
			Runtime.getRuntime().exec("taskkill /F /IM internetexplorerdriver.exe");
			Thread.sleep(5000);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@BeforeMethod
	public void launchBrowser(ITestContext context, Method method) {
		String browser = context.getCurrentXmlTest().getParameter("browser") ;
		try {
			switch(browser.toLowerCase()) {
			case "chrome":
				//set browser property
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/chromedriver.exe");
				
				driver = new ChromeDriver();
				
				driver.manage().window().maximize();
				
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				
				driver.get("http://demo.automationtesting.in/");
				
				break;
				
			case "firefox":
				break;
				
			case "internetexplorer":
				break;
				
			default:
				Assert.fail("Browser is not matched, please check the browser name.");
			}
			test = extent.createTest(method.getName());
		}catch(Exception e) {
			
		}
	}
	
	@AfterMethod
	public void closeDriver() {
		try {
			if(driver!=null)
				// close the current browser
				driver.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@AfterSuite
	public void quitDrivers() {
		try {
			if(driver!=null)
				// close all opened browsers
				driver.quit();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @author Nataraj
	 * @param element
	 * @param value
	 * @purpose to enter values in input field
	 * @date 26-Sep-2020
	 */
	public void enterValues(WebElement element, String value) {
		try {
			if(element.isDisplayed()) {
				element.sendKeys(value);
				test.log(Status.INFO, "Entered value: "+value);
			}else {
				Assert.fail("Given WebElement is not displayed");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @author Nataraj
	 * @param element
	 * @purpose to click the WebElement
	 * @date 26-Sep-2020
	 */
	public void click(WebElement element, String buttonName) {
		try {
			if(element.isEnabled()) {
				element.click();
				test.log(Status.INFO, "Clicked on Elemenet: "+buttonName);
			}else {
				Assert.fail("Given WebElement is not enabled");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @author Nataraj
	 * @param element
	 * @param elementName
	 * @purpose to check the given WebElement is displayed or not
	 * @return boolean
	 */
	public boolean isDisplayed(WebElement element, String elementName) {
		boolean isDisplay = false;
		try {
			element.isDisplayed();
			isDisplay = true;
			test.log(Status.INFO, elementName+" is Displayed");
		}catch(Exception e) {
			test.log(Status.INFO, "Given element is not displayed");
		}
		return isDisplay;
	}
	
	/**
	 * @author Nataraj
	 * @param element
	 * @purpose wait condition for visibility of element
	 * @date 26-Sep-2020
	 */
	public void explicitWait(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(element));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
