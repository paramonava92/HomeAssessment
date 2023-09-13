package tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.AddAnimalPage;
import pages.AnimalsPage;
import pages.LoginPage;
import pages.SideMenu;
import utils.SeleniumUtils;

public class E2ETest2AddAnimalToShelter {
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

	// Normally we would have parameterized the test data using data providers
	// However I'll keep it simple by just hard coding static values specified in README file
	@Test
	public void addAnimal() {
		driver.get("https://localhost:44429/");

		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterUsername("admin");
		loginPage.enterPassword("@dmin123");
		loginPage.clickLogin();
		
		// wait for home page to load
		SeleniumUtils.waitUntilElementIsPresent(driver, "//p[text()='Happy Tails Animal Shelter']", 5);
		
		SideMenu sideMenu = new SideMenu(driver);
		sideMenu.switchToAnimalsPage();
		
		// Wait for animals page to load
		SeleniumUtils.waitUntilElementIsPresent(driver, "//h1[text()='Animals']", 5);
		
		AnimalsPage animalsPage = new AnimalsPage(driver);
		animalsPage.addAnimal();
		
		AddAnimalPage addAnimalPage = new AddAnimalPage(driver);
		addAnimalPage.enterShelterId("1");
		addAnimalPage.enterType("Dog");
		addAnimalPage.enterName("Max");
		addAnimalPage.enterWeight("15");
		addAnimalPage.uploadFile("src/test/resources/dog.jpeg");
		addAnimalPage.save();
		
	}
}
