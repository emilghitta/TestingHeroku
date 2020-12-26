package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DynamicLoading2PageObject extends BasePageObject{
    private By startButton = By.xpath("//div[@id='start']/button");
    private By finishMessage = By.xpath("//div[@id='finish']/h4");

    public DynamicLoading2PageObject(WebDriver driver, Logger log){ super(driver,log);}

    public void clickStartButton(){
        log.info("Clicking the start button");
        click(startButton);
    }

    public boolean finishMessagePresent(String expectedMessage){
        log.info("Checking to see if the finishMessage is visible");
        return isTextPresentInElement(finishMessage,expectedMessage);
    }

    public String getFinishMessageText(){
        log.info("Getting the finishMessage Text");
        return getElementText(finishMessage);
    }



}
