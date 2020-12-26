package com.herokuapp.theinternet.dynamicloadingtests;
import com.herokuapp.theinternet.base.BaseTest;
import com.herokuapp.theinternet.pages.*;
import org.testng.Assert;
import org.testng.annotations.*;


public class DynamicLoadingTest extends BaseTest {

    @Parameters({"testPage","expectedMessage"})
    @Test(priority = 1)
        public void visibilityTest(String testPage,String expectedMessage){

            //Open the Webpage
            WelcomePageObject welcomePage = new WelcomePageObject(driver, log);
            welcomePage.openPage(testPage);

            DynamicLoadingPageObject dynamicTest = welcomePage.clickDynamicLoadingLink();

            DynamicLoading1PageObject dynamicLoading1 = dynamicTest.clickExample1Link();

            //Click the start Button
            dynamicLoading1.clickStartButton();

            //Assert that "Hello World" message is displayed.
            Assert.assertTrue(dynamicLoading1.isFinishMessageVisible(),"The finish message is not visible");


            //Assert that the correct message is displayed.
            Assert.assertTrue(dynamicLoading1.getFinishMessageText().contains(expectedMessage),"Expected: " + expectedMessage + " but found: " + dynamicLoading1.getFinishMessageText());

    }

    @Parameters({"testPage","expectedMessage"})
    @Test(priority = 2)
        public void renderedAfterTest(String testPage,String expectedMessage){

        //Open Webpage
        WelcomePageObject welcomePage = new WelcomePageObject(driver,log);
        welcomePage.openPage(testPage);
        DynamicLoadingPageObject dynamicTest = welcomePage.clickDynamicLoadingLink();
        DynamicLoading2PageObject dynamicLoading2 = dynamicTest.clickExample2Link();

        //Click the start button
        dynamicLoading2.clickStartButton();

        //Wait and Assert that the correct finish message is displayed
        Assert.assertTrue(dynamicLoading2.finishMessagePresent(expectedMessage),"The expectedMessage is not visible");

        Assert.assertTrue(dynamicLoading2.getFinishMessageText().contains(expectedMessage),"Expected: " + expectedMessage + " but found " + dynamicLoading2.getFinishMessageText());


    }

    @Parameters({"testPage","expectedGoneMessage","expectedBackMessage"})
    @Test(priority = 2)
    public void elementDomRemoval(String testPage, String expectedGoneMessage,String expectedBackMessage){

            //Open the webpage
        WelcomePageObject welcomePage = new WelcomePageObject(driver,log);
        welcomePage.openPage(testPage);
        DynamicControlsPageObject dynamicControls = welcomePage.clickDynamicControlsLink();

        // Assert that the button text is "Remove"
        Assert.assertTrue(dynamicControls.getButtonText().contains("Remove")); //Hardcoded !

        //Check de checkbox & Verify that the checkbox is checked.
        dynamicControls.clickCheckBox();

        Assert.assertTrue(dynamicControls.isCheckboxChecked(),"The checkbox is not checked. It should be!");


        //Click the remove button
        dynamicControls.clickRemoveButton();

        //Assert that the checkbox has been removed successfully from the DOM
        Assert.assertTrue(dynamicControls.checkForCheckBoxStaleness());

        //Assert that the It's gone message is displayed.
        Assert.assertTrue(dynamicControls.getMessageText().contains(expectedGoneMessage),"Expected: " + expectedGoneMessage + " but found: " +dynamicControls.getMessageText());

        //Assert that the the button text is "Add"
        Assert.assertTrue(dynamicControls.getButtonText().contains("Add")); //Hardcoded!

        //Click on the Add button
        dynamicControls.clickRemoveButton();

        //Assert that the It's back message is displayed. We are re-assignin the message WebElement because it has become stale after clicking the add button
        Assert.assertTrue(dynamicControls.getMessageText().contains(expectedBackMessage));
        //message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));

        //Click the second checkbox and assert that the second checkbox is checked
        dynamicControls.clickBackCheckbox();
        Assert.assertTrue(dynamicControls.isReturnedCheckboxChecked(),"The checkbox is not checked. It should be!");

        //Assert that the checkbox is back again
        Assert.assertTrue(dynamicControls.isCheckboxDisplayed(),"Checkbox is not displayed but it should be");
    }

    @Parameters({"testPages","textIntoField","expectedEnabledMessage","expectedDisabledMessage", "inputCheckbox"})
    @Test(priority = 0)
    public void disableElementTest(String testPages, String textIntoField, String expectedEnabledMessage, String expectedDisabledMessage, String inputCheckbox){

        WelcomePageObject welcomePage = new WelcomePageObject(driver,log);
        welcomePage.openPage(testPages);

        DynamicControlsPageObject dynamicControls = welcomePage.clickDynamicControlsLink();

        //Verify if the input field is disabled by default
        Assert.assertFalse(dynamicControls.isCheckboxEnabled(),"The checkbox should be disabled by default");

        //Click on the Enable button
        dynamicControls.clickEnableDisableButton();

        //Verify if the input field is enabled after click
        //Type something inside the field
        Assert.assertTrue(dynamicControls.isCheckboxEnabled(),"The checkbox should be enabled");

        //Verify that the Message is displayed
        Assert.assertTrue(dynamicControls.getMessageText().contains(expectedEnabledMessage));

        //Verify if the text is the same that you wrote inside the field
        //For inputs we need to get the value attribute not the text
        dynamicControls.sendKeysToInput(inputCheckbox);
        Assert.assertTrue(dynamicControls.getInputValue().contains(inputCheckbox));

        //Click the disable button
        dynamicControls.clickEnableDisableButton();

        //Verify that the It's disabled! message is displayed
        Assert.assertTrue(dynamicControls.getMessageText().contains(expectedDisabledMessage));

        //Verify if the input field is disabled We could use an Implicit time!
        Assert.assertFalse(dynamicControls.isCheckboxEnabled(),"The input field should be disabled");
        


    }

}
