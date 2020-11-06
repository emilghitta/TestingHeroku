package com.herokuapp.theinternet.loginpagetests;
import com.herokuapp.theinternet.base.TestUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTests extends TestUtilities {


    @Parameters({"username","password","expectedSuccessMessage"})
    @Test(priority = 0 , groups = {"positiveTests", "smokeTests"})
    public void positiveLoginTest(String username, String password, String expectedSuccessMessage){


        //   1. Open Test page.
        log.info("Open the test page");
        String url = "http://the-internet.herokuapp.com/login";
        driver.get(url);

        // 2. Enter username.
        log.info("Enter the username: " + username );
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys(username);

        // 3. Enter password.
        log.info("Enter the password " + password);
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(password);

        // 4. Click login button.
        log.info("Click the login button");
        WebElement loginButton = driver.findElement(By.className("radius"));
        loginButton.click();


        //Verifications

        // 1. Verify that the page URL is the correct one.
        log.info("Assert that the page URL is the correct one");
        String expectedURL = "http://the-internet.herokuapp.com/secure";
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(expectedURL, actualURL,"Actual page is not the same as expected");

        // 2. Verify that the Logout button is visible.s
        log.info("Assert that the logout button is visible");
        WebElement logoutButton = driver.findElement(By.xpath("//a[@class='button secondary radius']"));
        Assert.assertTrue(logoutButton.isDisplayed(),"LogOut button is not available.");


        //  3. Verify that the login message is displayed.
        log.info("Assert that the login message is successfully displayed");
        WebElement successLoginMessage = driver.findElement(By.cssSelector("#flash"));
        String actualMessage = successLoginMessage.getText();

        Assert.assertTrue(actualMessage.contains(expectedSuccessMessage),"Actual Message does not include the expected message!");


    }

    @Parameters({"username","password","expectedErrorMessage"})
    @Test(priority = 1, groups = {"negativeTests", "smokeTests"})
    public void negativeLoginTest(String username, String password, String expectedErrorMessage){

        // Steps

        // 1. Access the Login Page.
        log.info("Open the test url");
        driver.get("http://the-internet.herokuapp.com/login");

        // 2. Input Invalid username.
        log.info("Input the invalid username " + username);
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys(username);

        // 3. Input Valid password.
        log.info("Input the valid password" + password);
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(password);

        // 4. Click Login button.
        log.info("Click the login button");
        WebElement loginButton = driver.findElement(By.className("radius"));
        loginButton.click();

        // Verification

        // 1. Verify that we are on the correct URL
        log.info("Assert that the correct URL is displayed");
        String currentURLAddress = driver.getCurrentUrl();
        String expectedURLAddress = "http://the-internet.herokuapp.com/login";

        Assert.assertEquals(expectedURLAddress, currentURLAddress);

        // 2. Verify that the correct error message is displayed.
        log.info("Assert that the correct error message is displayed");
        WebElement loginErrorMessage = driver.findElement(By.id("flash"));
        String actualErrorMessage = loginErrorMessage.getText();

        Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),"Expected error message is different from the actual error message");


    }


}
