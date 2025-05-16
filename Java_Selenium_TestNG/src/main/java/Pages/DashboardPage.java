package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utils.ReusableMethods;

public class DashboardPage extends ReusableMethods {

	WebDriver driver;

	public DashboardPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[contains(@class, 'mb-3')]")
	List<WebElement> products;

	@FindBy(xpath = "//div[contains(@class, 'mb-3')]")
	WebElement prod;

	@FindBy(xpath = "//div[@id='toast-container']")
	WebElement confirmationMessage;

	@FindBy(css = ".ng-animating")
	WebElement loadingIcong;

	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	WebElement cartBtn;

	@FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
	WebElement ordersBtn;

	@FindBy(xpath = "//div[@class='footer']")
	WebElement footer;

	By addTocartBtn = By.xpath(".//div[@class='card-body']/button[last()]");

	public List<WebElement> gatherProducts() {
		waitForElementToAppear(prod);
		return products;
	}

	public WebElement getProductByName(String productName) {
		WebElement desiredProduct = gatherProducts().stream()
				.filter(product -> product.findElement(By.xpath(".//b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return desiredProduct;
	}

	public void addProductToCart(String productName) throws InterruptedException {
		getProductByName(productName).findElement(addTocartBtn).click();
		waitForElementToAppear(confirmationMessage);
		Thread.sleep(1000);
	}

	public void goToCart() {
		cartBtn.click();
	}

	public void goToOrders() {
		waitForElementToAppear(ordersBtn);
		ordersBtn.click();
	}

	public String footerText() {
		waitForElementToAppear(footer);
		return footer.getText();
	}
}
