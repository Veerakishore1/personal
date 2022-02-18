package in.automationtesting.demo.pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import in.automationtesting.demo.utils.GenericUtils;

public class DashBoard extends GenericUtils{
	
	@FindBy(id="btn1")
	private WebElement signInBtn;

	public WebElement getSignInBtn() {
		explicitWait(signInBtn);
		return signInBtn;
	}
	
	@FindBy(id="btn2")
	private WebElement skipSignInBtn;
	
	public WebElement getSkipSignInBtn() {
		explicitWait(skipSignInBtn);
		return skipSignInBtn;
	}
	
	@FindBy(id="logo")
	private WebElement logo;
	
	public WebElement getLogo() {
		explicitWait(logo);
		return logo;
	}
	
	@FindBy(id="email")
	private WebElement signUpBtn;
	
	public WebElement getSignUpBtn() {
		explicitWait(signUpBtn);
		return signUpBtn;
	}
}
