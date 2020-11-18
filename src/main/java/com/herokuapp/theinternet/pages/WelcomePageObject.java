package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WelcomePageObject extends BasePageObject{
        private String pageURL = "http://the-internet.herokuapp.com/";
        private By pageMainTitle = By.className("heading");
        private By subHeading = By.xpath("//div[@id='content']/h2");
        private By formAuthenticationLinkLocator = By.linkText("Form Authentication");
        private By dynamicLoadingLinkLocator = By.linkText("Dynamic Loading");


        public WelcomePageObject(WebDriver driver, Logger log){
            super(driver,log);
        }

        public void openPage(){
            log.info("Opening the Welcome page");
            openPage(pageURL);
        }

        public LoginPageObject clickFormAuthenticationLink(){
            log.info("Clicking Form Authentication link on Welcome page");
            click(formAuthenticationLinkLocator);
            return new LoginPageObject(driver,log);
        }

        public DynamicLoadingPageObject clickDynamicLoadingLink(){
            log.info("Clicking Dynamic Loading link on Welcome page");
            click(dynamicLoadingLinkLocator);
            return new DynamicLoadingPageObject(driver,log);
        }

}
