package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DynamicLoadingPageObject extends BasePageObject{
    private String pageUrl = "http://the-internet.herokuapp.com/dynamic_loading";
    private By example1Link = By.linkText("Example 1: Element on page that is hidden");
    private By example2Link = By.linkText("Example 2: Element rendered after the fact");

    public DynamicLoadingPageObject(WebDriver driver, Logger log){
        super(driver,log);
    }

    public void openUrl(){
        openPage(pageUrl);
    }

    public DynamicLoading1PageObject clickExample1Link(){
        log.info("Clicking the Example 1 link");
        click(example1Link);
        return new DynamicLoading1PageObject(driver,log);
    }

    public DynamicLoading2PageObject clickExample2Link(){
        log.info("Clicking the Example 2 link");
        click(example2Link);
        return new DynamicLoading2PageObject(driver,log);
    }

}
