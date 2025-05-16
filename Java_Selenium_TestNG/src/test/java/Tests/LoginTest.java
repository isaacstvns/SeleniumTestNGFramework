package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.DashboardPage;
import Pages.LoginPage;
import Utils.BaseTest;

public class LoginTest extends BaseTest {

	LoginPage loginPage;
	DashboardPage dashboardPage;
	
	@Test(retryAnalyzer = Utils.Retry.class)
	public void validLogin() {
		loginPage = new LoginPage(driver);
		dashboardPage = new DashboardPage(driver);
		loginPage.Login("hello_world@abc.com", "123456789Hello_World");
		Assert.assertTrue(dashboardPage.footerText().equalsIgnoreCase("Design and Developed By - Kunal Sharma"));
	}
	
	@Test
	public void invalidLogin() {
		loginPage = new LoginPage(driver);
		loginPage.Login("hello_world@abc.com", "123456789Hello");
		Assert.assertTrue(loginPage.errorMessage().equalsIgnoreCase("Incorrect email or password."));
	}
}
