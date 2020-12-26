package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WelcomePageObject extends BasePageObject{
        private By pageMainTitle = By.className("heading");
        private By subHeading = By.xpath("//div[@id='content']/h2");
        private By formAuthenticationLinkLocator = By.linkText("Form Authentication");
        private By dynamicLoadingLinkLocator = By.linkText("Dynamic Loading");
        private By dynamicControls = By.linkText("Dynamic Controls");
        private By checkBox =  By.linkText("Checkboxes");


        public WelcomePageObject(WebDriver driver, Logger log){
            super(driver,log);
        }

        public void openPage(String pageURL){
            log.info("Opening the Welcome page");
            openGivenPage(pageURL);
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

        public DynamicControlsPageObject clickDynamicControlsLink(){
            log.info("Clicking Dynamic Controls link on Welcome page");
            click(dynamicControls);
            return new DynamicControlsPageObject(driver ,log);
        }

        public CheckBoxesPageObject clickCheckBoxesLink(){
            log.info("Clicking the Checkboxes link on Welcome page");
            click(checkBox);
            return new CheckBoxesPageObject(driver ,log);
        }


}
