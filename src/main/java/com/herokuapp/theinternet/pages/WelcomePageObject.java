package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WelcomePageObject {
        private WebDriver driver;
        private Logger log;
        private String pageURL = "http://the-internet.herokuapp.com/";
        private By pageMainTitle = By.className("heading");
        private By subHeading = By.xpath("//div[@id='content']/h2");
        private By formAuthenticationLinkLocator = By.linkText("Form Authentication");


        public WelcomePageObject(WebDriver driver, Logger log){
            this.driver = driver;
            this.log = log;
        }


        public void openPage(){
            log.info("Opening the page");
            driver.get(pageURL);
        }

        public LoginPageObject clickFormAuthenticationLink(){
            log.info("Clicking Form Authentication link on Welcome page");
            driver.findElement(formAuthenticationLinkLocator).click();
            return new LoginPageObject(driver,log);
        }

        public String getElementText(By element){
            log.info("Getting Element text");
            return driver.findElement(element).getText();
        }


}
