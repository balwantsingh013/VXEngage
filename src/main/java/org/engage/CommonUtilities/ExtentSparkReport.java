package org.engage.CommonUtilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.engage.Base.BaseUtilities;
import org.engage.Constants.FilePaths;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentSparkReport {


    public static ExtentReports extent;
    public static ExtentSparkReporter spark;

    public static ExtentReports initialise() {
        extent = new ExtentReports();
        spark = new ExtentSparkReporter(FilePaths.EXTENT_HTML_REPORT_PATH + generateDynamicName() + ".html");
        setConfig();
        return extent;
    }

    public static String generateDynamicName() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy h-m-s-ms");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static void setConfig() {
        spark.config().setTheme(Theme.STANDARD);
        spark.config().setDocumentTitle("VXengage Automation");
        spark.config().setReportName("AutomationReport_" +
                BaseUtilities.readPropertyfile(FilePaths.EXTENT_REPORT_PROPERTIES).getProperty("projectName") );
        spark.config().setOfflineMode(true);
        extent.attachReporter(spark);
        setSystemInfo();
    }

    public static void setSystemInfo() {
        try {
            extent.setSystemInfo("SYSTEM NAME", InetAddress.getLocalHost().getHostName());
            extent.setSystemInfo("BROWSER NAME", BaseUtilities.readPropertyfile(FilePaths.GLOBAL_CONFIG_PROPERTIES).getProperty("browser"));
            extent.setSystemInfo("OS Name", BaseUtilities.readPropertyfile(FilePaths.EXTENT_REPORT_PROPERTIES).getProperty("OSName"));
            extent.setSystemInfo("Developed & Tested By", BaseUtilities.readPropertyfile(FilePaths.EXTENT_REPORT_PROPERTIES).getProperty("developedBy"));
            extent.setSystemInfo("APPLICATION URL", BaseUtilities.readPropertyfile(FilePaths.GLOBAL_CONFIG_PROPERTIES).getProperty("applicationURL"));
            extent.setSystemInfo("ENVIRONMENT", BaseUtilities.readPropertyfile(FilePaths.EXTENT_REPORT_PROPERTIES).getProperty("environment"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

}
