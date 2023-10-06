package org.engage.TestCases;

import org.apache.log4j.Priority;
import org.engage.Base.BaseUtilities;
import org.engage.POM.HomePagePOM;
import org.engage.TestBase.TestUtils;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class HomePageTest extends TestUtils {

    @Test
    public void clickOnPopUp()
    {
        HomePagePOM.clickOnPopUp();
    }
    @Test
    public void validateSideBarMenus()
    {
        HomePagePOM.clickOnPopUp();
        HomePagePOM.validateSideBarMenus();
    }



}
