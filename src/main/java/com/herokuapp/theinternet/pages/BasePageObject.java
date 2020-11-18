package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageObject {
    protected WebDriver driver;
    protected Logger log;

    public BasePageObject(WebDriver driver, Logger log){
        this.driver = driver;
        this.log = log;
    }

    protected void openPage(String url){
        log.info("Opening the: " + url + " page");
        driver.get(url);
    }

    protected WebElement find(By locator){
        waitForVisibility(locator,5);
        return driver.findElement(locator);
    }

    protected String getElementText(By locator){
        log.info("Extracting text from " + locator);
        waitForVisibility(locator,5);
        return driver.findElement(locator).getText();
    }

    protected void click(By locator){
        waitForVisibility(locator,5);
        driver.findElement(locator).click();
    }

    protected void type(String text, By locator){
        waitForVisibility(locator,5);
        driver.findElement(locator).sendKeys(text);
    }

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }


    private void waitFor(ExpectedCondition<WebElement> condition, Integer timeout){
        timeout = timeout != null ? timeout : 30;
        WebDriverWait wait = new WebDriverWait(driver,timeout);
        wait.until(condition);
    }

    protected void waitForVisibility(By locator, Integer time){
        int attempts = 0;
        while(attempts < 2 ){
           try{
               waitFor(ExpectedConditions.visibilityOfElementLocated(locator),time);
               break;
           } catch(StaleElementReferenceException e){
               log.info(e);
           }
           attempts++;
        }
    }



}
