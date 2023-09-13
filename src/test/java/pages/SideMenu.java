package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SideMenu {
	@FindBy(xpath = "//ul/a[@href='/admin']")
	WebElement sheltersPage;
	
	@FindBy(xpath = "//ul/a[@href='/admin/animals']")
	WebElement animalsPage;
	
	@FindBy(xpath = "//ul/a[@href='/profile']")
	WebElement profilePage;
	
	@FindBy(xpath = "//ul/button")
	WebElement logoutButton;
	
	public SideMenu(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void switchToSheltersPage() {
		sheltersPage.click();
	}
	
	public void switchToAnimalsPage() {
		animalsPage.click();
	}
	
	public void switchToProfilePage() {
		profilePage.click();
	}
	
	public void logOut() {
		logoutButton.click();
	}
}
