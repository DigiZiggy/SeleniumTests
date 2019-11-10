package gm.taltech.ee.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;

public class FormAuthenticationPage {

    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By submitButton = By.xpath("//button[@type='submit']");
    private By logoutButton = By.xpath("//*[@id=\"content\"]/div/a");
    private By successNotification = By.cssSelector(".flash.success");
    private By errorNotification = By.cssSelector(".flash.error");

    private WebDriver driver;

    public FormAuthenticationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickSubmit() {
        driver.findElement(submitButton).click();
    }

    public void clickLogout() {
        driver.findElement(logoutButton).click();
    }

    public boolean isSuccessNotificationDisplayed() {
        try {
            driver.findElement(successNotification);
            return true;
        } catch (ElementNotVisibleException e) {
            // log exception
            return false;
        }
    }

    public boolean isSuccessLoginNotificationDisplayed() {
        try {
            isSuccessNotificationDisplayed();
            if (driver.findElement(successNotification).getText().contains("You logged into a secure area!")) {
                return true;
            } else {
                return false;
            }
        } catch (ElementNotVisibleException e) {
            // log exception
            return false;
        }
    }

    public boolean isSuccessLogoutNotificationDisplayed() {
        try {
            isSuccessNotificationDisplayed();
            if (driver.findElement(successNotification).getText().contains("You logged out of the secure area!")) {
                return true;
            } else {
                return false;
            }
        } catch (ElementNotVisibleException e) {
            // log exception
            return false;
        }
    }

    public boolean isErrorNotificationDisplayed() {
        try {
            driver.findElement(errorNotification);
            return true;
        } catch (ElementNotVisibleException e) {
            // log exception
            return false;
        }
    }

    public boolean isUsernameInvalidNotificationDisplayed() {
        try {
            isErrorNotificationDisplayed();
            if (driver.findElement(errorNotification).getText().contains("Your username is invalid!")) {
                return true;
            } else {
                return false;
            }
        } catch (ElementNotVisibleException e) {
            // log exception
            return false;
        }
    }

    public boolean isPasswordInvalidNotificationDisplayed() {
        try {
            isErrorNotificationDisplayed();
            if (driver.findElement(errorNotification).getText().contains("Your password is invalid!")) {
                return true;
            } else {
                return false;
            }
        } catch (ElementNotVisibleException e) {
            // log exception
            return false;
        }
    }
}
