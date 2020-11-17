package com.herokuapp.theinternet.pages;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPageObject {
    private WebDriver driver;
    private Logger log;
    private String pageURL = "http://the-internet.herokuapp.com/login";
    private By pageMainTitle = By.xpath("//div[@class='example']/h2");
    private By welcomeMessage = By.className("subheader");
    private By usernameLocator = By.id("username");
    private By passwordLocator = By.id("password");
    private By loginButtonLocator = By.className("radius");

    public LoginPageObject(WebDriver driver, Logger log){
            this.driver = driver;
            this.log = log;
    }

    public void openPage(){
        log.info("Opening page");
        driver.get(pageURL);
    }

    public SecureAreaPageObject logIn(String username, String password){
        log.info("Executing log in with username: " + username + " and password: " + password);
        driver.findElement(usernameLocator).sendKeys(username);
        driver.findElement(passwordLocator).sendKeys(password);
        driver.findElement(loginButtonLocator).click();
        return new SecureAreaPageObject(driver,log);
    }

    public String getElementText(By element){
        log.info("Getting Element text");
        return driver.findElement(element).getText();
    }


}
