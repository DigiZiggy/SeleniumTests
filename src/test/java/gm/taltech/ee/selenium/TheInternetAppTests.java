package gm.taltech.ee.selenium;

import gm.taltech.ee.page_object.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TheInternetAppTests {

    private WebDriver driver;
    private HomePage homePage;

    @BeforeClass
    public void set_up_driver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
    }

    @BeforeMethod
    public void open_driver() {
        driver.get("https://the-internet.herokuapp.com/");
    }

    @Test
    public void can_go_to_home_page() {
        assertThat(homePage.isAt(), is(true));
    }

    @Test
    public void should_login_to_secure_area_with_valid_credentials() {
        FormAuthenticationPage formAuthenticationPage = new FormAuthenticationPage(driver);

        homePage.clickFormAuthenticationLink();
        formAuthenticationPage.enterUsername("tomsmith");
        formAuthenticationPage.enterPassword("SuperSecretPassword!");
        formAuthenticationPage.clickSubmit();

        assertThat(formAuthenticationPage.isSuccessNotificationDisplayed(), is(true));
    }

    @Test
    public void should_display_successfully_logged_in_message_on_login() {
        FormAuthenticationPage formAuthenticationPage = new FormAuthenticationPage(driver);

        homePage.clickFormAuthenticationLink();
        formAuthenticationPage.enterUsername("tomsmith");
        formAuthenticationPage.enterPassword("SuperSecretPassword!");
        formAuthenticationPage.clickSubmit();

        assertThat(formAuthenticationPage.isSuccessLoginNotificationDisplayed(), is(true));
    }

    @Test
    public void should_display_successfully_logged_out_message_on_logout() {
        FormAuthenticationPage formAuthenticationPage = new FormAuthenticationPage(driver);

        homePage.clickFormAuthenticationLink();
        formAuthenticationPage.enterUsername("tomsmith");
        formAuthenticationPage.enterPassword("SuperSecretPassword!");
        formAuthenticationPage.clickSubmit();
        formAuthenticationPage.clickLogout();

        assertThat(formAuthenticationPage.isSuccessLogoutNotificationDisplayed(), is(true));
    }

    @Test
    public void should_display_username_invalid_message_with_invalid_username() {
        FormAuthenticationPage formAuthenticationPage = new FormAuthenticationPage(driver);

        homePage.clickFormAuthenticationLink();
        formAuthenticationPage.enterUsername("nublugameboytetris");
        formAuthenticationPage.enterPassword("SuperSecretPassword!");
        formAuthenticationPage.clickSubmit();

        assertThat(formAuthenticationPage.isUsernameInvalidNotificationDisplayed(), is(true));
    }

    @Test
    public void should_display_password_invalid_message_with_invalid_password() {
        FormAuthenticationPage formAuthenticationPage = new FormAuthenticationPage(driver);

        homePage.clickFormAuthenticationLink();
        formAuthenticationPage.enterUsername("tomsmith");
        formAuthenticationPage.enterPassword("oksana");
        formAuthenticationPage.clickSubmit();

        assertThat(formAuthenticationPage.isPasswordInvalidNotificationDisplayed(), is(true));
    }

    @Test
    public void should_change_location_of_blocks_after_drag_and_drop_A_to_B() {
        DragAndDropPage dragAndDropPage = new DragAndDropPage(driver);

        homePage.clickDragAndDropLink();
        dragAndDropPage.dragAonTopOfB();

        assertThat(dragAndDropPage.isTheFirstElementsHeaderText("B"), is(true));
    }

    @Test
    public void should_not_change_location_of_blocks_after_dragging_and_dropping_A_away_from_B() {
        DragAndDropPage dragAndDropPage = new DragAndDropPage(driver);

        homePage.clickDragAndDropLink();
        dragAndDropPage.dragAonTopOfPageFooter();

        assertThat(dragAndDropPage.isTheFirstElementsHeaderText("A"), is(true));
    }

    @Test
    public void should_see_New_Window_text_after_clicking_Click_Here() {

        MultipleWindowsPage multipleWindowsPage = new MultipleWindowsPage(driver);

        homePage.clickMultipleWindowsLink();
        multipleWindowsPage.clickOnClickHereLink();
        multipleWindowsPage.goToSecondWindowOpened();

        assertThat(multipleWindowsPage.isTextNewWindowDisplayed("New Window"), is(true));
    }

    @AfterClass
    public void close_driver() {
        if (driver != null) {
            driver.close();
        }
    }
}
