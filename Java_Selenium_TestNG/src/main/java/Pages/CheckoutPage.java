package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utils.ReusableMethods;

public class CheckoutPage extends ReusableMethods {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement selectCountryBox;
	
	@FindBy(xpath = "//section[contains(@class,'ta-results')]")
	WebElement countryList;
	
	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[4]")
	WebElement selectedCountry;
	
	@FindBy(xpath = "//a[contains(@class,'action__submit')]")
	WebElement submitBtn;
	
	public void selectCountry(String country) {
		selectCountryBox.sendKeys(country);
		waitForElementToAppear(countryList);
		selectedCountry.click();
	}
	
	public void submitOrder() {
		submitBtn.click();
	}
}
