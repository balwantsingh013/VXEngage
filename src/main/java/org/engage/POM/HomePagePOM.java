package org.engage.POM;
import org.engage.Base.BaseUtilities;
import org.engage.Constants.FilePaths;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Properties;

import static org.engage.Base.BaseUtilities.*;

public class HomePagePOM {
    static  Properties homePageProperties;
    public HomePagePOM()
    {
        homePageProperties = BaseUtilities.readPropertyfile(FilePaths.HOME_PAGE_PROPERTIES);
    }


    public static void clickOnPopUp() {

        try {
            WebElement remindMeLaterButton = wait.until(ExpectedConditions.elementToBeClickable(clickOnElement(homePageProperties.getProperty("alertRemindMeLater"))));
            WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(clickOnElement(homePageProperties.getProperty("alertOk"))));

            if (remindMeLaterButton.isDisplayed()) {
                remindMeLaterButton.click();
                okButton.click();
            } else {
                System.out.println("One or both buttons are not available, skipping the click.");
            }
        } catch (TimeoutException e) {
            System.out.println("One or both elements are not found, skipping the click.");
        }

    };

    public static void validateSideBarMenus ()
    {
       //Validate Account Summary
       assertElementText(homePageProperties.getProperty("menuAccountSummary"),homePageProperties.getProperty("accountSummaryText"));
       // Validate Billing Menu
       assertElementText(homePageProperties.getProperty("menuBilling"),homePageProperties.getProperty("billingText"));
       //Validate Usage Insight Menu
       assertElementText(homePageProperties.getProperty("menuUsageInsights"),homePageProperties.getProperty("usageInsightText"));
       // Validate Payment Menu
       assertElementText(homePageProperties.getProperty("menuPayments"),homePageProperties.getProperty("paymentText"));
    }


}
