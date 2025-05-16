package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utils.ReusableMethods;

public class LoginPage extends ReusableMethods {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement userPassword;

	@FindBy(id = "login")
	WebElement login;
	
	@FindBy(id = "toast-container")
	WebElement errorMessage;

	public void Login(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		login.click();
	}

	public String errorMessage () {
		waitForElementToAppear(errorMessage);
		return errorMessage.getText();
	}
}
