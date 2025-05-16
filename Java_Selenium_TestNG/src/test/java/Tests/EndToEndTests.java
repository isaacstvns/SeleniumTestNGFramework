package Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.List;
import Pages.CartPage;
import Pages.CheckoutPage;
import Pages.ConfirmationPage;
import Pages.DashboardPage;
import Pages.LoginPage;
import Pages.OrderHistoryPage;
import Utils.BaseTest;

public class EndToEndTests extends BaseTest {

	LoginPage login;
	DashboardPage dashboardPage;
	CartPage cartPage;
	CheckoutPage checkoutPage;
	ConfirmationPage confirmationPage;
	OrderHistoryPage orderHistoryPage;
	public String orderId;

	@Test(groups = { "E2E" }, dataProvider = "getData")
	public void submitOrder(HashMap<String, String> input) throws InterruptedException {

		login = new LoginPage(driver);
		login.Login(input.get("email"), input.get("password"));

		dashboardPage = new DashboardPage(driver);
		dashboardPage.addProductToCart(input.get("productName"));
		dashboardPage.goToCart();

		cartPage = new CartPage(driver);
		Boolean match = cartPage.validateCartProducts(input.get("productName"));

		Assert.assertTrue(match);

		cartPage.goToCheckoutPage();

		checkoutPage = new CheckoutPage(driver);

		checkoutPage.selectCountry("United");
		checkoutPage.submitOrder();

		confirmationPage = new ConfirmationPage(driver);
		Assert.assertTrue(confirmationPage.validateConfirmationMessage());
		orderId = confirmationPage.returnOrderId();
		
		dashboardPage.goToOrders();
		
		orderHistoryPage = new OrderHistoryPage(driver);
		Assert.assertTrue(orderHistoryPage.validateOrderId(orderId));
	}

//	@Test(dependsOnMethods = { "submitOrder" })
//	public void validateOrderHistory() {
//		LoginPage login = new LoginPage(driver);
//		login.Login("hello_world@abc.com", "123456789Hello_World");
//
//		dashboardPage = new DashboardPage(driver);
//		dashboardPage.goToOrders();
//
//		orderHistoryPage = new OrderHistoryPage(driver);
//		Assert.assertTrue(orderHistoryPage.validateOrderId(orderId));
//	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\main\\java\\Resource\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) }, { data.get(2) } };
	}

//	return new Object[][] { { "hello_world@abc.com", "123456789Hello_World", "ZARA COAT 3" },
//		{ "hello_world!@abc.com", "1234Hello", "ADIDAS ORIGINAL" } };
}
