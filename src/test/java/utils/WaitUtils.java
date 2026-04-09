package utils;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {

    private WebDriver driver;
    private static final int TIMEOUT = 10;

    public WaitUtils(WebDriver driver) {
        this.driver = driver;
        // No shared WebDriverWait - fresh instance per call
    }

    public WebElement waitForElementVisible(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT))
                   .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForElementClickable(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT))
                   .until(ExpectedConditions.elementToBeClickable(locator));
    }
}
