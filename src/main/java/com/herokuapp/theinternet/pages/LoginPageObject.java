package com.herokuapp.theinternet.pages;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPageObject extends BasePageObject{
    private String pageURL = "http://the-internet.herokuapp.com/login";
    private By invalidErrorMessage = By.id("flash");
    private By pageMainTitle = By.xpath("//div[@class='example']/h2");
    private By welcomeMessage = By.className("subheader");
    private By usernameLocator = By.id("username");
    private By passwordLocator = By.id("password");
    private By loginButtonLocator = By.className("radius");

    public LoginPageObject(WebDriver driver, Logger log){
        super(driver,log);
    }

    public void openPage(){
        log.info("Opening page");
        openPage(pageURL);
    }

    public SecureAreaPageObject logIn(String username, String password){
        log.info("Executing log in with username: " + username + " and password: " + password);
        type(username,usernameLocator);
        type(password,passwordLocator);
        click(loginButtonLocator);
        return new SecureAreaPageObject(driver,log);
    }

    public void negativeLogIn(String username, String password){
        log.info("Executing log in with invalid credentials: username -> " + username + " password -> " +password);
        type(username,usernameLocator);
        type(password,passwordLocator);
        click(loginButtonLocator);
    }

    public String getErrorMessage(){
        return getElementText(invalidErrorMessage);
    }

}
