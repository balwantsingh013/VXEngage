package org.engage.TestBase;

import org.engage.Base.BaseUtilities;
import org.engage.POM.HomePagePOM;
import org.testng.annotations.*;

import static org.engage.POM.LoginPagePOM.loginApplication;

public class TestUtils {
   // static HomePagePOM homePagePOM;

    @BeforeClass()
    public void initialize()
    {
        BaseUtilities.initialization();
        new HomePagePOM();
    }
    @BeforeClass()
    @Parameters({ "username", "password" })
    public static void login(String username, String password)
    {
        loginApplication(username,password);

    }
    @AfterClass
    public void tearDown() {
        BaseUtilities.tearDown();
    }

}

