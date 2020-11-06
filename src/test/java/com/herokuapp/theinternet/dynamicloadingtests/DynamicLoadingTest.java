package com.herokuapp.theinternet.dynamicloadingtests;
import com.herokuapp.theinternet.base.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;


public class DynamicLoadingTest extends BaseTest {

    @Parameters({"testPage","expectedMessage"})
    @Test(priority = 1)
        public void visibilityTest(String testPage,String expectedMessage){

            //Open the Webpage
            driver.get(testPage);

            //Click the start Button
            WebElement startButton = driver.findElement(By.xpath("//div[@id='start']/button"));
            startButton.click();

            //Assert that "Hello World" message is displayed.
            WebElement finishMessage = driver.findElement(By.id("finish"));

              WebDriverWait wait = new WebDriverWait(driver, 6);
                  wait.until(ExpectedConditions.visibilityOf(finishMessage));


            //Assert that the correct message is displayed.
            String actualMessage = finishMessage.getText();
            Assert.assertTrue(actualMessage.contains(expectedMessage),"Incorrect message is displayed. Expected to recevie Hello World");

    }

    @Parameters({"testPage","expectedMessage"})
    @Test(priority = 2)
        public void renderedAfterTest(String testPage,String expectedMessage){
        //Open Webpage
        driver.get(testPage);

        //Click the start button
        WebElement startButton = driver.findElement(By.xpath("//div[@id='start']/button"));
        startButton.click();

        //Wait and Assert that the correct finish message is displayed

        WebDriverWait wait = new WebDriverWait(driver,10);
        Assert.assertTrue(
                wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("finish"),"Hello World")), "Couldn't find the expected message"
        );
    }

    @Parameters({"testPage","expectedGoneMessage","expectedBackMessage"})
    @Test(priority = 2)
    public void elementDomRemoval(String testPage, String expectedGoneMessage,String expectedBackMessage){
        WebDriverWait wait = new WebDriverWait(driver,10);
            //Open the webpage
        driver.get(testPage);

        //Create checkbox element
        WebElement checkbox = driver.findElement(By.id("checkbox"));

        //Click the remove button
        WebElement removeButton = driver.findElement(By.xpath("//form[@id='checkbox-example']/button"));
        removeButton.click();

        //Assert that the checkbox has been removed successfully from the DOM
        //Assert.assertTrue( wait.until(ExpectedConditions.invisibilityOf(checkbox)),"Checkbox is still visible, but should not be");
        Assert.assertTrue(wait.until(ExpectedConditions.stalenessOf(checkbox)),"Checkbox is still present");

        //Assert that the It's gone message is displayed.
        WebElement message = driver.findElement(By.id("message"));
        String messageText = message.getText();

        Assert.assertTrue(messageText.contains(expectedGoneMessage));

        //Click on the Add button
        WebElement addButton = driver.findElement(By.xpath("//form[@id='checkbox-example']/button"));
        addButton.click();

        //Assert that the It's back message is displayed. We are re-assignin the message WebElement because it has become stale after clicking the add button
        message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        messageText = message.getText();

        Assert.assertTrue(messageText.contains(expectedBackMessage));

        //Assert that the checkbox is back again
        checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkbox")));

        Assert.assertTrue(checkbox.isDisplayed(),"Checkbox is not displayed but it should be");
    }

    @Parameters({"testPages","textIntoField"})
    @Test(priority = 0)
    public void disableElementTest(String testPages, String textIntoField){

        WebDriverWait wait = new WebDriverWait(driver,10);
        driver.get(testPages);

        WebElement enable = driver.findElement(By.xpath("//form[@id='input-example']/button"));
        WebElement inputField = driver.findElement(By.xpath("//form[@id='input-example']/input"));

        //Verify if the input field is disabled by default
        Assert.assertFalse(inputField.isEnabled(),"Text field is not disabled by default and it should be");

        //Click on the Enable button
        enable.click();

        //Verify if the input field i enabled after click
        //Type something inside the field
        wait.until(ExpectedConditions.elementToBeClickable(inputField)).sendKeys(textIntoField);

        //Verify that the Message is displayed
        WebElement enabledMessage = driver.findElement(By.id("message"));
        Assert.assertTrue(enabledMessage.isDisplayed());

        //Verify if the text is the same that you wrote inside the field
        //For inputs we need to get the value attribute not the text
        String inputFormText = inputField.getAttribute("value");
        Assert.assertTrue(inputFormText.contains(textIntoField));

        //Click the disable button
        enable.click();

        enabledMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));


        //Verify if the input field is disabled We could use an Implicit time!
        Assert.assertFalse(inputField.isEnabled());
        


    }

}
