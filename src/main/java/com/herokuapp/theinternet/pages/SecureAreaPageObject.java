package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SecureAreaPageObject {
    private WebDriver driver;
    private Logger log;
    private By loggedInMessage = By.id("flash");
    private By closeLoggedInMessage = By.className("close");
    private By pageMainTitle = By.xpath("//div[@class='example']/h2");
    private By welcomeMessage = By.xpath("//div[@class='example']/h4");
    private By logoutButton = By.xpath("//a[@class='button secondary radius']");

    public SecureAreaPageObject(WebDriver driver, Logger log){
        this.driver = driver;
        this.log = log;
    }

    public LoginPageObject logout(){
        log.info("Clicking the Logout button");
        driver.findElement(logoutButton).click();
        return new LoginPageObject(driver,log);
    }

    public String getElementText(By element){
        log.info("Getting Element text");
        return driver.findElement(element).getText();
    }

    public void closeMessage(){
        log.info("Clicking the close button for the loggedInMessage");
        driver.findElement(closeLoggedInMessage).click();
    }


}
