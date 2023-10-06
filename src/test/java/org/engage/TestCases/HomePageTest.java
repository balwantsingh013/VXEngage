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

    @Test(priority=2)
    //@Parameters({"Account Summary"})
   public void validateAccountSummaryMenu()
    {
        HomePagePOM.validateAccountSummaryMenu("Account Summary");
    }
    @Test(priority=3)
    public void validateUsageInsightMenu()
    {
        HomePagePOM.validateUsageInsightsMenu("Usage Insight");
    }
    @Test(priority=4)
    public void validateBillingMenu()
    {
        HomePagePOM.validateBillingMenu("Billings");
    }
    @Test(priority=5)
    public void validatePaymentsMenu()
    {
        HomePagePOM.validatePaymentsMenu("Payments");
    }
    @Test(priority=6)
    public void doLogout() throws InterruptedException {
        HomePagePOM.doLogout();
    }


}
