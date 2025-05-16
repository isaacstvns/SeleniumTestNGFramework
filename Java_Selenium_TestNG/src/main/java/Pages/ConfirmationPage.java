package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utils.ReusableMethods;

public class ConfirmationPage extends ReusableMethods {
	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h1[@class='hero-primary']")
	WebElement confirmationMessage;

	@FindBy(xpath = "//label[@class='ng-star-inserted']")
	WebElement orderId;

	public Boolean validateConfirmationMessage() {
		waitForElementToAppear(confirmationMessage);
		return confirmationMessage.getText().equalsIgnoreCase("Thankyou for the order.");
	}

	public String returnOrderId() {
		return removeSpecialChar(orderId.getText());
	}

}
