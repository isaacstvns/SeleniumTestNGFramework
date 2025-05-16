package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import Resource.WebDriverFactory;

public class BaseTest {

	public WebDriver driver;

	public WebDriver initializeDriver() throws IOException {
		WebDriverFactory factory = new WebDriverFactory();
		return factory.CreateDriver(System.getProperty("browser") != null ? System.getProperty("browser") : getProperty("browser"));
	}

	@BeforeMethod(alwaysRun = true)
	public void launchApplication() throws IOException {
		driver = initializeDriver();
		driver.get(getProperty("websiteURL"));
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		if (driver != null) {
			try {
				driver.quit();
			} catch (Exception e) {
				System.out.println("Error while quitting driver: " + e.getMessage());
			} finally {
				driver = null;
			}
		}
	}

	public String getProperty(String propertyName) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\Resource\\Global.properties");
		prop.load(fis);
		return prop.getProperty(propertyName);
	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}
	
	public String getScreenshot(String testCaseName) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}
}
