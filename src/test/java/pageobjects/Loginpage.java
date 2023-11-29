package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Loginpage {

	WebDriver driver;

	
	public Loginpage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	// element for login box
	@FindBy(name = "username")
	@CacheLookup
	WebElement UserLogin_username;

//	element for password box 
	@FindBy(name = "password") WebElement UserLogin_password;

	// locator for submit button
	By submit=By.xpath("//button[@type='submit']");

	// login action method
	public void setusername(String uname) {
		
		UserLogin_username.sendKeys(uname);
	}

	// passwod action method
	public void setpassword(String pwd) {
		
		UserLogin_password.sendKeys(pwd);
	}

	// submit action method
	public void clicksubmit() {

		driver.findElement(submit).click();
	}

}