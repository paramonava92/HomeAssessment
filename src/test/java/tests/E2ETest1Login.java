package tests;

import java.time.Duration;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.LoginPage;
import utils.SeleniumUtils;

public class E2ETest1Login  {
	WebDriver driver;
	
	// HOOKS
	@BeforeTest
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
	// POSITIVE TESTS
	@Test(dataProvider = "credentials")
	public void validLoginTest(String username, String password) throws InterruptedException {
		driver.get("https://localhost:44429/");
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
		loginPage.clickLogin();
		
		// wait for home page to load
		SeleniumUtils.waitUntilElementIsPresent(driver, "//p[text()='Happy Tails Animal Shelter']", 5);
		
		Cookie cookie = driver.manage().getCookieNamed("LPAnimalShelter");
		Assert.assertNotNull(cookie, "Cookie was not set properly");
		System.out.println(cookie); // for debugging
	}
	
	@DataProvider(name = "credentials")
	public String[][] provideCreds() {
		return new String[][] {
			{"admin", "@dmin123"},
			{"sally", "$ally123"}
		};
	}
	
	// NEGATIVE TESTS
	@Test(dataProvider = "invalidCredentials")
	public void invalidLoginTest(String username, String password) throws InterruptedException {
		driver.get("https://localhost:44429/");
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
		loginPage.clickLogin();
		
		// wait for error to show up
		SeleniumUtils.waitUntilElementIsPresent(driver, "//h2[text()='Login']/following-sibling::p", 5);
		
		loginPage = new LoginPage(driver); // re-initialize page to detect error message
		String error = loginPage.getErrorMessage();
		Assert.assertEquals(error, "Invalid username or password");
	}
	
	@DataProvider(name = "invalidCredentials")
	public String[][] provideInvalidCreds() {
		return new String[][] {
			{"dummy", "dummypass"},
			{"dummy", ""},
			{"", "dummypass"},
			{"", ""}
		};
	}
}
