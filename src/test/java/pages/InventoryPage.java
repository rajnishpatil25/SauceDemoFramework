package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.WaitUtils;

public class InventoryPage {

    WebDriver driver;
    WaitUtils wait;

    By appLogo = By.xpath("//div[text()='Swag Labs']");
    By btnAddToCart = By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']");
    By cartBadge = By.className("shopping_cart_badge");
    By btnRemove = By.id("remove-sauce-labs-backpack");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }
    
    public void clickAddToCart() {
    	wait.waitForElementClickable(btnAddToCart).click();
    }
    
    public boolean isShoppingCartBadgedDisplayed() {
     return driver.findElements(cartBadge).size() > 0;
    }
    
    public boolean isRemoveButtonDisplayed() {
    	try {
    		return wait.waitForElementVisible(btnRemove).isDisplayed();
    	}catch(Exception e) {
    		return false;
    	}
    }
    
    public String getCartCount() {
    	return wait.waitForElementVisible(cartBadge).getText();
    	
    }

    public boolean isAppLogoDisplayed() {
    	try {
    		return wait.waitForElementVisible(appLogo).isDisplayed();

    	}catch(Exception e) {
    		return false;
    	}
    }
    
    
}