package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AnimalsPage {
	@FindBy(xpath = "//a[@href='/admin/add-and-place-animal']")
	WebElement addAnimalButton;
	
	public AnimalsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void addAnimal() {
		addAnimalButton.click();
	}
}
