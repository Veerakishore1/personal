package in.automationtesting.demo.tests;

import java.sql.Driver;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import in.automationtesting.demo.pom.DashBoard;
import in.automationtesting.demo.utils.ApplicationUtils;

public class BrowserLaunching extends ApplicationUtils{

	@Test
	public void checkingMainPageTest() {
		DashBoard objDashBoard = PageFactory.initElements(driver,DashBoard.class);
		try {
			//verifying the SignIn is displayed or not
			isDisplayed(objDashBoard.getSignInBtn(), "SignIn");
			
			//verifying the SkipSignIn is displayed or not
			isDisplayed(objDashBoard.getSkipSignInBtn(), "SkipSignIn");
			
			//verifying the Logo is displayed or not
			isDisplayed(objDashBoard.getLogo(), "Company Logo");
			
			//verifying the SignUp is displayed or not
			isDisplayed(objDashBoard.getSignUpBtn(), "SignUp");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}