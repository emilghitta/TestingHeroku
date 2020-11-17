package com.herokuapp.theinternet.base;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class BrowserDriverFactory {
    private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    private String browser;
    private Logger log;

    public BrowserDriverFactory(String browser, Logger log){
        this.browser = browser;
        this.log = log;
    }

    public WebDriver createDriver(){

        log.info("Create driver with " + browser);

            //1. Create driver.
            switch (browser){
                case "Chrome":
                    System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                    driver.set(new ChromeDriver());
                    break;
                case "Firefox":
                    System.setProperty("webdriver.gecko.driver","src/main/resources/geckodriver.exe");
                    driver.set(new FirefoxDriver());
                    break;

                default:
                    log.info("Not sure how to start " + browser + " starting Chrome as the default browser instead");
                    System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                    driver.set(new ChromeDriver());
            }
            return driver.get();

        }

}
