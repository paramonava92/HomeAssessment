package pages;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddAnimalPage {
	@FindBy(xpath = "//input[@name='shelterId']")
	WebElement shelterIdInput;
	
	@FindBy(xpath = "//input[@name='type']")
	WebElement typeInput;
	
	@FindBy(xpath = "//input[@name='name']")
	WebElement nameInput;
	
	@FindBy(xpath = "//input[@name='weight']")
	WebElement weightInput;
	
	@FindBy(xpath = "//input[@type='file']")
	WebElement filePicker;
	
	@FindBy(xpath = "//button[text()='Save']")
	WebElement saveButton;
	
	public AddAnimalPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void enterShelterId(String shelterId) {
		shelterIdInput.sendKeys(shelterId);
	}
	
	public void enterType(String type) {
		typeInput.sendKeys(type);
	}
	
	public void enterName(String name) {
		nameInput.sendKeys(name);
	}
	
	public void enterWeight(String weight) {
		weightInput.sendKeys(weight);
	}
	
	public void uploadFile(String path) {
		File file = new File(path);
		path = file.getAbsolutePath();
		filePicker.sendKeys(path);
		
	}
	
	public void save() {
		saveButton.click();
	}
}
