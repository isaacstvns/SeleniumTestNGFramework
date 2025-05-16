package Utils;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReusableMethods {

	WebDriver driver;

	public ReusableMethods(WebDriver driver) {
		this.driver = driver;
	}

	public void waitForElementToAppear(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForElementToDisappear(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	public String removeSpecialChar(String str) {
		return str.replaceAll("[^a-zA-Z0-9]", "");
	}
}
