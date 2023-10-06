package org.engage.POM;

import org.apache.log4j.Logger;
import org.engage.Base.BaseUtilities;
import org.engage.Constants.FilePaths;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

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

    public static void validateAccountSummaryMenu (String MenuName)
    {
        verifyMenuIsPresent(MenuName,homePageProperties.getProperty("menuAccountSummary"));
    }

    public static void validateBillingMenu (String MenuName)
    {
        verifyMenuIsPresent(MenuName,homePageProperties.getProperty("menuBilling"));
    }

    public static void validateUsageInsightsMenu (String MenuName)
    {
        verifyMenuIsPresent(MenuName,homePageProperties.getProperty("menuUsageInsights"));
    }

    public static void validatePaymentsMenu (String MenuName)
    {
        verifyMenuIsPresent(MenuName,homePageProperties.getProperty("menuPayments"));
    }

    public static void doLogout () throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(clickOnElement(homePageProperties.getProperty("iconAccount"))));
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(clickOnElement(homePageProperties.getProperty("btnLogout"))));
        Thread.sleep(2000);
    }
}
