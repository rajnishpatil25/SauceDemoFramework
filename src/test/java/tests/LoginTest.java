package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.InventoryPage;
import pages.LoginPage;
import org.testng.internal.annotations.IListeners;
import org.testng.annotations.Listeners;

@Listeners(utils.TestListener.class)
public class LoginTest extends BaseTest {

	@Test(priority = 1, description = "Verify login with valid credentials")
	public void validLoginTest() {
		log.info("Verify with valid credentails");
		LoginPage loginpage = new LoginPage(getDriver());
		InventoryPage inventoryPage = new InventoryPage(getDriver());

		String username = config.getProperty("username");
		String password	= config.getProperty("password");

		loginpage.login(username, password);
		log.info("Login Test with valid cred Completed");
		
		
		boolean isLogoVisible = inventoryPage.isAppLogoDisplayed();
		System.out.println("Is Logo Visible: " + isLogoVisible);

		Assert.assertTrue(isLogoVisible, "Login failed");
	}

	@Test(priority = 2, description = "Verify login with Invalid credentials")
	public void inValidLoginTest() {
		log.info("Verify Login with In-valid credentails");
		LoginPage loginpage = new LoginPage(getDriver());

		String username = config.getProperty("invalidUname");
		String password = config.getProperty("invalidPass");

		loginpage.login(username, password);
		Assert.assertTrue(loginpage.isErrorDisplayed(), "Error messgae is not displayed");

		log.info("Login with invalid cred test cases completed");
	}
	
	@Test(priority=3, description = "Verify user can add item to cart")
	public void addProductToCart() {
		log.info("Verify user can add item to cart");
		LoginPage loginpage = new LoginPage(getDriver());
		InventoryPage inventoryPage = new InventoryPage(getDriver());
		
		String username = config.getProperty("username");
		String password	= config.getProperty("password");
		loginpage.login(username, password);
		
		inventoryPage.clickAddToCart();
		
		//Assert.assertTrue(inventoryPage.isShoppingCartBadgedDisplayed(), "Cart badge should visible");
		Assert.assertEquals(inventoryPage.getCartCount(), "1", "Cart Badge count should be 1");
		Assert.assertTrue(inventoryPage.isRemoveButtonDisplayed(), "Remove button should visible");
			
		
		log.info("Item added to the cart sucessfully");
	}
	
	@Test(priority = 4, description = "Verify locked out user")
	public void lockedOutUser(){
		log.info("Verify user can locked out and error message displayed");
		LoginPage loginPage = new LoginPage(getDriver());

		String username = config.getProperty("lockedUname");
		String passwrod = config.getProperty("password");
		loginPage.login(username, passwrod);

		Assert.assertTrue(loginPage.isLockaedErrorMsgDisplayed(), "Error messgae not displayed");

		log.info("Error message displayed sucessfully");

	}
	

}




















