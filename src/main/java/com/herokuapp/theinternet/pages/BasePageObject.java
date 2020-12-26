package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePageObject {
    protected WebDriver driver;
    protected Logger log;

    public BasePageObject(WebDriver driver, Logger log){
        this.driver = driver;
        this.log = log;
    }

    protected void openGivenPage(String url){
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

    protected String getElementValue(By locator){
        waitForVisibility(locator,5);
        return driver.findElement(locator).getAttribute("value");
    }

    protected List<WebElement> findAll(By locator){
        return driver.findElements(locator);
    }

    protected void click(By locator){
        waitForVisibility(locator,5);
        driver.findElement(locator).click();
    }

    protected void type(String text, By locator){
        waitForVisibility(locator,5);
        driver.findElement(locator).sendKeys(text);
    }

    protected boolean isElementVisible(By locator){
        waitForVisibility(locator, 5);
        return driver.findElement(locator).isDisplayed();
    }

    protected boolean isTextPresentInElement(By locator, String expectedTextToBePresent){
       return waitForTextToBePresentInElement(locator,expectedTextToBePresent,5);
    }

    protected boolean isElementSelected(By locator){
        waitForVisibility(locator,10);
        return driver.findElement(locator).isSelected();
    }

    protected boolean isElementEnabled(By locator){
        waitForElementToBeClickable(locator,5);
        return driver.findElement(locator).isEnabled();
    }

    protected boolean isElementStale(WebElement locator){
        return waitForElementStaleness(locator,10);
    }

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }


    private void waitFor(ExpectedCondition<WebElement> condition, Integer timeout){
        timeout = timeout != null ? timeout : 30;
        WebDriverWait wait = new WebDriverWait(driver,timeout);
        wait.until(condition);
    }


    //Checks for ElementNotVisible Exception
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

    //Checks for NoSuchElementException
    protected boolean waitForTextToBePresentInElement(By locator,String text, Integer timeout){
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator,text));
    }

    //Checks for Staleness of element

    protected boolean waitForElementStaleness(WebElement locator,Integer timeout){
        WebDriverWait wait = new WebDriverWait(driver,timeout);
        return wait.until(ExpectedConditions.stalenessOf(locator));
    }

    //Catches if timeout and element is not clickable but the trick is that this acts as a timeout so the isElementEnabled method will return false for the assert.
    protected void waitForElementToBeClickable(By locator, Integer timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        }catch(TimeoutException e){
            log.info("Timing out, element is not clickable");
        }
    }
    


}
