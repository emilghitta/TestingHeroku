package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DynamicLoadingPageObject extends BasePageObject{
    private By example1Link = By.xpath("//div[@class='example']/a[1]");
    private By example2Link = By.linkText("Example 2: Element rendered after the fact");

    public DynamicLoadingPageObject(WebDriver driver, Logger log){
        super(driver,log);
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
