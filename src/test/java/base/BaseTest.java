package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import utils.ConfigReader;

public class BaseTest {

	//public WebDriver driver;
	public ConfigReader config;
	public static Logger log = LogManager.getLogger(BaseTest.class);
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	public WebDriver getDriver() {
		return driver.get();
		
	}

@BeforeMethod
@Parameters("browser")
public void setup(@Optional("chrome") String browser) {

    log.info("============= Test Execution Started =============");
    config = new ConfigReader();

    String browserName = System.getProperty("browser", browser); // ✅ IMPORTANT

    String headless = config.getProperty("headless");
    boolean isHeadless = headless.equalsIgnoreCase("true");

    WebDriver localDriver;

    switch(browserName.toLowerCase()) {

        case "chrome":
            ChromeOptions chromeoption = new ChromeOptions();
            if(isHeadless) {
                chromeoption.addArguments("--headless=new");
            }
            localDriver = new ChromeDriver(chromeoption);
            break;

        case "edge":
            EdgeOptions edgeoption = new EdgeOptions();
            if(isHeadless) {
                edgeoption.addArguments("--headless=new");
            }
            localDriver = new EdgeDriver(edgeoption);
            break;

        case "firefox":
            FirefoxOptions firefoxoption = new FirefoxOptions();
            if(isHeadless) {
                firefoxoption.addArguments("--headless=new");
            }
            localDriver = new FirefoxDriver(firefoxoption);
            break;

        default:
            throw new RuntimeException("Invalid Browser: " + browserName);
    }

    driver.set(localDriver);
    localDriver.manage().window().maximize();
    localDriver.get(config.getProperty("url"));
    log.info("Website launched on " + browserName);
}

	@AfterMethod
	public void teardown() {
	    if(getDriver() != null) {
	        getDriver().quit();
	        driver.remove(); // important
	    }
	}

}
