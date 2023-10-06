package org.engage.TestBase;

import org.engage.Base.BaseUtilities;
import org.engage.POM.HomePagePOM;
import org.engage.POM.LoginPagePOM;
import org.testng.annotations.*;

public class TestUtils {

    LoginPagePOM loginPagePOM;
    @BeforeMethod
    public void initialize() {
        BaseUtilities.initialization();
        loginPagePOM = new LoginPagePOM();
        new HomePagePOM();
    }
    @BeforeMethod
    @Parameters({ "username", "password" })
    public void login(String username, String password) {
        loginPagePOM.loginApplication(username, password);
    }

   @AfterMethod()
    public void logoutApplication() throws InterruptedException {
        loginPagePOM.doLogout();
        BaseUtilities.tearDown();
    }

}
