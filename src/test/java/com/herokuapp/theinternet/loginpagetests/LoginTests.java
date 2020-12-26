package com.herokuapp.theinternet.loginpagetests;
import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.LoginPageObject;
import com.herokuapp.theinternet.pages.SecureAreaPageObject;
import com.herokuapp.theinternet.pages.WelcomePageObject;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTests extends TestUtilities {

    @Parameters({"testPage","username","password","expectedSuccessMessage","expectedPageURL"})
    @Test(priority = 0 , groups = {"positiveTests", "smokeTests"})
    public void positiveLoginTest(String testPage ,String username, String password, String expectedSuccessMessage, String expectedPageURL){


        //   1. Open Test page.
        WelcomePageObject welcomepage = new WelcomePageObject(driver,log);

        welcomepage.openPage(testPage);
        LoginPageObject loginpage = welcomepage.clickFormAuthenticationLink();


        // 2. Execute login.
        SecureAreaPageObject secureArea = loginpage.logIn(username, password);

        //Verifications

        // 1. Verify that the page URL is the correct one.
        log.info("Assert that the page URL is the correct one");
        Assert.assertEquals(secureArea.getCurrentUrl(),expectedPageURL,"The actual url doesn't match with the expected one");

        // 2. Verify that the Logout button is visible
        log.info("Assert that the logout button is visible");
        Assert.assertTrue(secureArea.isLogoutButtonVisible(),"Logout button is not visible");


        //  3. Verify that the login message is displayed.
        log.info("Assert that the login message is successfully displayed");
        Assert.assertTrue(secureArea.getLoggedInMessage().contains(expectedSuccessMessage),"The Welcome message is not the correct one");


    }

    @Parameters({"testPage","username","password","expectedErrorMessage", "expectedPageURL"})
    @Test(priority = 1, groups = {"negativeTests", "smokeTests"})
    public void negativeLoginTest(String testPage,String username, String password, String expectedErrorMessage, String expectedPageURL){


        // 1. Access the Login Page.
        log.info("Open the test url");
        WelcomePageObject welcome = new WelcomePageObject(driver, log);
        welcome.openPage(testPage);
        LoginPageObject loginPage = welcome.clickFormAuthenticationLink();

        // 2. Input username, password and click log in.
        log.info("Input username " + username);
        loginPage.negativeLogIn(username,password);

        // Verification

        // 1. Verify that we are on the correct URL
        log.info("Assert that the correct URL is displayed");
        Assert.assertTrue(loginPage.getCurrentUrl().equals(expectedPageURL));

        // 2. Verify that the correct error message is displayed.
        log.info("Assert that the correct error message is displayed");
        Assert.assertTrue(loginPage.getErrorMessage().contains(expectedErrorMessage),"Wrong or no error message is displayed.");


    }


}
