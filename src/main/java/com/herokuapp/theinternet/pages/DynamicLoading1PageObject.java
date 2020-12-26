package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DynamicLoading1PageObject extends BasePageObject{
    private By startButton = By.xpath("//div[@id='start']/button");
    private By finishMessage = By.xpath("//div[@id='finish']/h4");


    public DynamicLoading1PageObject(WebDriver driver, Logger log){
        super(driver, log);
    }

    public void clickStartButton(){
        log.info("Clicking the start button");
        click(startButton);
    }

    public String getFinishMessageText(){
        log.info("Getting finishMessage text");
        return getElementText(finishMessage);
    }

    public boolean isFinishMessageVisible(){
        log.info("Checking for the visibility of the finishMessage");
        return isElementVisible(finishMessage);
    }





}
