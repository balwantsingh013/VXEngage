package org.engage.Base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.engage.Constants.FilePaths;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class BaseUtilities {
    public static WebDriverWait wait;
    public static WebDriver driver;
    public static Properties userConfig;
    static Logger log = Logger.getLogger(BaseUtilities.class);
    private static String browserName()
    {
        userConfig = readPropertyfile(FilePaths.GLOBAL_CONFIGS_PATH);
        return userConfig.getProperty("browser");
    }
    public static void initialization() {
        String browser = browserName();
        if(browser.equalsIgnoreCase("chrome") || browser.isEmpty() || browser.equalsIgnoreCase("Google chrome"))
        {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        else if
        (browser.equalsIgnoreCase("Internet Explorer") || browser.equalsIgnoreCase("IE")
                || browser.equalsIgnoreCase("InternetExplorer")) {
            WebDriverManager.iedriver().setup();
            driver = new InternetExplorerDriver();
        }
        else if (browser.equalsIgnoreCase("FireFox") || browser.equalsIgnoreCase("FF")) {
            WebDriverManager.firefoxdriver().setup();
           // System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
            //System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
            driver = new FirefoxDriver();

        } else if (browser.equalsIgnoreCase("Edge") || browser.equalsIgnoreCase("Microsoft Edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("headless chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("headless");
            options.addArguments("window-size=1200x600");
            driver = new ChromeDriver();

        }
        try {
            driver.get(userConfig.getProperty("applicationURL"));
        }
        catch (Exception e)
        {
            log.info("Please enter the URL in GlobalConfigs.properties file - parameter name is applicationURL ");
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        wait = new WebDriverWait(driver, 20 );

    }
    public static Properties readPropertyfile(String propertyFilePath) {
        Properties p = null;
        try
        {
            FileReader reader = new FileReader(propertyFilePath);
            p = new Properties();
            p.load(reader);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return p;
    }

    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static void sendKeysToElement(String locator, String text) {
        getElement(locator).sendKeys(text);
    }

    private static String getLocatorType(String locator) {
        String type = splitString(locator, "]: ", 0);
        return type.substring(1).toUpperCase();
    }

    private static String getLocatorValue(String locator) {
        return splitString(locator, "]: ", 1);
    }

    private static String splitString(String locator, String regEx, int index) {
        String[] inputSpilt = locator.split(regEx);
        return inputSpilt[index];
    }

    public static WebElement getElement(String locator) {
        String locatorType = getLocatorType(locator);
        String locatorValue = getLocatorValue(locator);
        WebElement element = null;
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        switch (locatorType) {
            case "XPATH":
                element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
                break;
            case "CSS":
            case "CSSSELECTOR":
                element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
                break;
            case "NAME":
                element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
                break;
            case "CLASSNAME":
                element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locatorValue)));
                break;
            case "LINKTEXT":
                element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
                break;
            case "PARTIALLINKTEXT":
                element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(locatorValue)));
                break;
            case "TAGNAME":
                element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locatorValue)));
                break;
            case "ID":
                element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
                break;
        }
        jse.executeScript("arguments[0].style.border='cyan solid 3px' ", element);
        return element;

    }

    public static WebElement clickOnElement(String locator) {
        WebElement ele = getElement(locator);
        if (verifyElementIsClickable(ele))
            ele.click();
        return ele;
    }

    public static boolean verifyElementIsClickable(WebElement element) {
        try {
            ExpectedConditions.elementToBeClickable(element);
            log.info("Element is clickable");
        } catch (Exception e) {
            log.info("Element is not clickable");
            return false;
        }
        return true;
    }

    public static void assertElementText(String element, String expectedText) {
        WebElement ele= getElement(element);
        String actualText = ele.getText();
        Assert.assertEquals(actualText, expectedText, "Expected text: '" + expectedText + "', but got: '" + actualText + "'");
    }

        public static String decryptPasswordNormal(String encryptedPassword) {
        byte[] decodedBytes = Base64.decodeBase64(encryptedPassword.getBytes());
        return new String(decodedBytes);
    }


    public String getscreenshots(String testCaseName, WebDriver driver) throws IOException {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("DD-MM-YYYY-H-M-S");
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(FilePaths.SCREENSHOTS_PATH + testCaseName + dateFormat.format(date) + ".png");
        FileUtils.copyFile(sourceFile,destinationFile);
        System.out.println("Screenshot taken and saved to: " + destinationFile.getAbsolutePath());

        return FilePaths.SCREENSHOTS_PATH + testCaseName + dateFormat.format(date) + ".png";
    }


}
