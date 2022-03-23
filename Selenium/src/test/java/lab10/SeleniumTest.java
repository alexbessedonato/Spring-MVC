package lab10;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumTest extends AbstractSeleniumTest {

	private WebDriver driver;
	
	@Before
	public void setupDriver() {
		Capabilities capabilities = DesiredCapabilities.firefox();
		driver = new FirefoxDriver(capabilities);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@After
	public void closeDriver() {
		driver.close();
	}
	
	@Test
	public void testPageTitle() {
		driver.navigate().to("https://www.google.com/ncr");  

		assertEquals("Google", driver.getTitle());
	}
	
	@Test
	public void testUoLLandingPageTitle() {
		driver.navigate().to("https://le.ac.uk");  

		assertTrue(driver.getTitle().contains("A Leading UK University"));
	}
	
	@Test
	public void testRightMove() {
		driver.navigate().to("https://rightmove.co.uk");  

		assertTrue(driver.getTitle().startsWith("Rightmove"));
	}
	
	@Test
	public void testGoogleSearchForCO2103() {
		driver.navigate().to("https://www.google.com/ncr");
		WebElement element = driver.findElement(By.name("q"));
		element.sendKeys("CO2103 Leicester");
		element.submit();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement firstResult = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h3")));
		String textContent = firstResult.getAttribute("textContent");
		assertThat(textContent, CoreMatchers.containsString("Software Architecture and System Development"));
	}
	
	 
}
