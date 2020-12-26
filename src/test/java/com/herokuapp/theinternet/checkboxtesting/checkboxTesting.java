package com.herokuapp.theinternet.checkboxtesting;

import com.herokuapp.theinternet.base.BaseTest;
import com.herokuapp.theinternet.pages.CheckBoxesPageObject;
import com.herokuapp.theinternet.pages.WelcomePageObject;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class checkboxTesting extends BaseTest {

    @Parameters({"testPage","expectedHeader"})
    @Test(priority = 1)
        public void CheckboxTesting(String TestPage, String expectedHeader){
            //Open the Webpage

        WelcomePageObject welcome = new WelcomePageObject(driver,log);
        welcome.openPage(TestPage);

        CheckBoxesPageObject check = welcome.clickCheckBoxesLink();

        //Verify that the header is the correct one
        Assert.assertTrue(check.headerText().contains("Checkboxes"),"The header is not the correct one!");

        //Checking all checkboxes
        check.selectAllCheckboxes();

        //Verifying that all checkboxes are checked
        Assert.assertTrue(check.areAllCheckboxesChecked(),"Not all checkboxes are checked");

        //Unchecking all checkboxes
        check.unselectAllCheckboxes();

        //Verifying that all checkboxes are unchecked
        Assert.assertTrue(check.areAllCheckboxesUnchecked(),"Not all checkboxes are unchecked");

    }

}
