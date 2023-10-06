package org.engage.Constants;

import java.io.File;

public class FilePaths {



    public static final String RESOURCE_PATH = "src" + File.separator + "main" + File.separator + "resources" + File.separator;

    public static final String GLOBAL_CONFIGS_PATH = RESOURCE_PATH + "CommonConfigProperties" + File.separator + "GlobalConfigs.properties";

    public static final String LOGIN_PAGE_PROPERTIES = RESOURCE_PATH + "UILocators" + File.separator + "LoginPage.properties";
    public static final String HOME_PAGE_PROPERTIES = RESOURCE_PATH + "UILocators" + File.separator + "HomePage.properties";

    public static final String EXTENT_HTML_REPORT_PATH = System.getProperty("user.dir") + File.separator + "extentReportsOutput" + File.separator + "htmlReports" + File.separator;

    public static final String EXTENT_REPORT_PROPERTIES = RESOURCE_PATH + "CommonConfigProperties" + File.separator + "ExtentReport.properties";
    public static final String GLOBAL_CONFIG_PROPERTIES = RESOURCE_PATH + "CommonConfigProperties" + File.separator + "GlobalConfigs.properties";

    public static final String SCREENSHOTS_PATH = System.getProperty("user.dir") + File.separator + "extentReportsOutput" + File.separator + "screenshot" + File.separator;
}
