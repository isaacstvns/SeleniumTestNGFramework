package Resource;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverFactory {

	public WebDriver CreateDriver(String browserType) {

		switch (browserType.toLowerCase()) {
		case "chrome":
			return createChromeDriver();
		case "edge":
			return createEdgeDriver();
		case "firefox":
			return createFirefoxDriver();
		default:
			throw new IllegalArgumentException("Invalid Browser Type");

		}
	}

	private WebDriver createChromeDriver() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--start-maximized");
		options.addArguments("--headless=new");
		return new ChromeDriver(options);
	}

	private WebDriver createEdgeDriver() {
		WebDriverManager.edgedriver().setup();
		EdgeOptions options = new EdgeOptions();
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--start-maximized");
		options.addArguments("--headless");
		return new EdgeDriver(options);
	}

	private WebDriver createFirefoxDriver() {
		WebDriverManager.firefoxdriver().setup();
		FirefoxOptions options = new FirefoxOptions();
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--start-maximized");
		options.addArguments("--headless");
		return new FirefoxDriver(options);
	}
}
