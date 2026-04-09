package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.WaitUtils;

public class LoginPage {

	WebDriver driver;
	WaitUtils wait;

	//xpaths
	By username = By.xpath("//input[@id='user-name']");
	By password = By.xpath("//input[@id='password']");
	By loginBtn = By.id("login-button");
	By errorMsg = By.xpath("//h3");
	By lockedMsg = By.xpath("//h3[text()='Epic sadface: Sorry, this user has been locked out.']");

	//functions
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		wait = new WaitUtils(driver);
	}

	public void login(String user, String pass)
	{
		wait.waitForElementVisible(username).sendKeys(user);
		wait.waitForElementVisible(password).sendKeys(pass);
		wait.waitForElementClickable(loginBtn).click();
	}

	public boolean isErrorDisplayed() {
		return driver.findElement(errorMsg).isDisplayed();

	}

	public String getErrorMessage() {
        return driver.findElement(errorMsg).getText();
    }

	public boolean isLockaedErrorMsgDisplayed(){
		return driver.findElement(lockedMsg).isDisplayed();
	}


}


