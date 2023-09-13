package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	@FindBy(xpath = "//form[@class='login-form']/label[@for='userName']/following-sibling::input")
	WebElement usernameInput;
	
	@FindBy(xpath = "//form[@class='login-form']/label[@for='password']/following-sibling::input")
	WebElement passwordInput;
	
	@FindBy(xpath = "//form[@class='login-form']/button")
	WebElement loginButton;
	
	@FindBy(xpath = "//h2[text()='Login']/following-sibling::p")
	WebElement errorMessage;
	
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void enterUsername(String username) {
		usernameInput.sendKeys(username);
	}
	
	public void enterPassword(String password) {
		passwordInput.sendKeys(password);
	}
	
	public String getErrorMessage() {
		return errorMessage.getText();
	}
	
	public void clickLogin() {
		loginButton.click();
	}
}
