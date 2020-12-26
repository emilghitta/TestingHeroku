package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DynamicControlsPageObject extends BasePageObject{
    private By removeButton = By.xpath("//form[@id='checkbox-example']/button");
    private By checkBox = By.xpath("//div[@id='checkbox']/input");
    private By message = By.id("message");
    private By backCheckbox = By.id("checkbox");

    private By inputText = By.xpath("//form[@id='input-example']/input");
    private By enableDisableInputText = By.xpath("//form[@id='input-example']/button");




    public DynamicControlsPageObject(WebDriver driver, Logger log){ super(driver,log);}

    public void clickRemoveButton(){
        log.info("Clicking the remove button");
        click(removeButton);
    }

    public void clickEnableDisableButton(){
        log.info("Clicking the enable button");
        click(enableDisableInputText);
    }

    public void clickCheckBox(){
        log.info("Clicking the checkBox");
        click(checkBox);
    }

    public void clickBackCheckbox(){
        log.info("Clicking the second checkbox");
        click(backCheckbox);
    }

    public void sendKeysToInput(String input){
        log.info("Sending text to the input field");
        type(input,inputText);
    }

    public String getMessageText(){
        log.info("Getting message text");
        return getElementText(message);
    }

    public String getInputValue(){
        log.info("Extracting element's value");
        return getElementValue(inputText);
    }

    public boolean checkForCheckBoxStaleness(){
        log.info("Checking checkbox staleness");
        WebElement checkbox = find(checkBox);
        return isElementStale(checkbox);
    }

    public boolean isCheckboxDisplayed(){
        log.info("Checking if the checkbox is displayed");
        return isElementVisible(backCheckbox);
    }

    public boolean isCheckboxEnabled(){
        log.info("Verifying if the checkbox is enabled or not");
        return isElementEnabled(inputText);
    }

    public String getButtonText(){
        return getElementText(removeButton);
    }

    public boolean isCheckboxChecked(){
        log.info("Verifying if the checkbox is checked or not");
        return isElementSelected(checkBox);
    }

    public boolean isReturnedCheckboxChecked(){
        log.info("Verifying if the returned checkbox is checked or not");
        return isElementSelected(backCheckbox);
    }





}
