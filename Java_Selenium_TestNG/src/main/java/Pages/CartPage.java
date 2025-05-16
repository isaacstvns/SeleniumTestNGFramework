package Pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import Utils.ReusableMethods;

public class CartPage extends ReusableMethods {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='cart']//h3")
	List<WebElement> cartProducts;
	
	@FindBy(xpath = "//div[@class='cart']//h3")
	WebElement cartProduct;
	
	@FindBy(xpath = "//button[contains(text(), 'Checkout')]")
	WebElement checkoutBtn;

	public List<WebElement> listOfCartProducts() throws InterruptedException {
		waitForElementToAppear(cartProduct);
		return cartProducts;
	}
	
	public Boolean validateCartProducts(String productName) throws InterruptedException {
		Boolean match = listOfCartProducts().stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public void goToCheckoutPage() {
		checkoutBtn.click();
	}

}
