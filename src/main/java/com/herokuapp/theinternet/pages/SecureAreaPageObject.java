package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SecureAreaPageObject extends BasePageObject{
    private String pageURL = "http://the-internet.herokuapp.com/login/secure";
    private By loggedInMessage = By.id("flash");
    private By closeLoggedInMessage = By.className("close");
    private By pageMainTitle = By.xpath("//div[@class='example']/h2");
    private By welcomeMessage = By.xpath("//div[@class='example']/h4");
    private By logoutButton = By.xpath("//a[@class='button secondary radius']");

    public SecureAreaPageObject(WebDriver driver, Logger log){
        super(driver,log);
    }

    public boolean isLogoutButtonVisible(){
        return find(logoutButton).isDisplayed();
    }

    public LoginPageObject logout(){
        log.info("Clicking the Logout button");
        click(logoutButton);
        return new LoginPageObject(driver,log);
    }

    public void closeMessage(){
        log.info("Clicking the close button for the loggedInMessage");
        click(closeLoggedInMessage);
    }

    public String getLoggedInMessage(){
        return getElementText(loggedInMessage);
    }

    public String getPageMainTitle(){
        return getElementText(pageMainTitle);
    }

    public void CloseLoggedInMess(){
        click(closeLoggedInMessage);
    }


}
