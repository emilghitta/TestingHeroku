package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckBoxesPageObject extends BasePageObject{
    private By heading = By.xpath("//div[@class='example']//h3");
    private By checkbox = By.xpath("//input[@type='checkbox']");


    public CheckBoxesPageObject(WebDriver driver, Logger log){super(driver,log);}

    public String headerText(){
        return getElementText(heading);
    }

    //Get List of all checkboxes and check if unchecked.

    public void selectAllCheckboxes(){
        log.info("Checking all unchecked checkboxes");
        List<WebElement> checkboxes = findAll(checkbox);
        for(WebElement checkbox : checkboxes){
            if(!checkbox.isSelected()){
                checkbox.click();
            }
        }
    }

    public boolean areAllCheckboxesChecked(){
        log.info("Verifying if all checkboxes are checked");
        List<WebElement> checkboxes = findAll(checkbox);
        for(WebElement checkbox : checkboxes){
            if(!checkbox.isSelected()){
                return false;
            }
        }
        return true;
    }

    public void unselectAllCheckboxes(){
        log.info("Unselecting all checkboxes");
        List<WebElement> checkboxes = findAll(checkbox);
        for(WebElement checkbox : checkboxes){
            if(checkbox.isSelected()){
                checkbox.click();
            }
        }
    }

    public boolean areAllCheckboxesUnchecked(){
        log.info("Verifying if all checkboxes are unchecked");
        List<WebElement> checkboxes = findAll(checkbox);
        for(WebElement checkbox : checkboxes){
            if(checkbox.isSelected()){
                return false;
            }
        }
        return true;
    }

}
