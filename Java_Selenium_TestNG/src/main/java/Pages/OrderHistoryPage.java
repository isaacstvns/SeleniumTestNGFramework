package Pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utils.ReusableMethods;

public class OrderHistoryPage extends ReusableMethods {

	WebDriver driver;

	public OrderHistoryPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//table[contains(@class,'table-bordered')]/tbody/tr/th")
	List<WebElement> orderIds;
	
	@FindBy(xpath = "//table[contains(@class,'table-bordered')]/tbody/tr/th")
	WebElement orderIdsForWait;

	public Boolean validateOrderId(String orderId) {
		waitForElementToAppear(orderIdsForWait);
		return orderIds.stream().anyMatch(order -> order.getText().equalsIgnoreCase(orderId));
	}
}
