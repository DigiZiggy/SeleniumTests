package gm.taltech.ee.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Set;

public class MultipleWindowsPage {

    private WebDriver driver;
    private By clickHereLink = By.linkText("Click Here");

    public MultipleWindowsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnClickHereLink() {
        driver.findElement(clickHereLink).click();
    }

    public boolean isTextNewWindowDisplayed(String headerText) {
        return driver.findElement(By.tagName("h3")).getText().equals(headerText);
    }

    public void goToSecondWindowOpened() {
        String firstPageWindowHandle;
        String secondPageWindowHandle = null;
        firstPageWindowHandle = driver.getWindowHandle();

        // Store both window handles
        Set<String> testPageWindowHandle = driver.getWindowHandles();

        for (String windowHandle : testPageWindowHandle) {
            if (!firstPageWindowHandle.equals(windowHandle)) {
                secondPageWindowHandle = windowHandle;
            }
        }

        driver.switchTo().window(secondPageWindowHandle);
    }
}
