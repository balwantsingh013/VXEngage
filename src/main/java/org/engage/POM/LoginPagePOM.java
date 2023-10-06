package org.engage.POM;
import org.engage.Constants.FilePaths;
import org.engage.Base.BaseUtilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Properties;

import static org.engage.Base.BaseUtilities.*;

public class LoginPagePOM
{
      //  static Properties loginPageProperties;
    //    public LoginPagePOM()
  //      {
          //  loginPageProperties = BaseUtilities.readPropertyfile(FilePaths.LOGIN_PAGE_PROPERTIES);
     //   }
    public static void loginApplication(String username, String password)
    {
        Properties  loginPageProperties = BaseUtilities.readPropertyfile(FilePaths.LOGIN_PAGE_PROPERTIES);
        sendKeysToElement(loginPageProperties.getProperty("username"),username);
        String decryptedPassword=  decryptPasswordNormal(password);
        sendKeysToElement(loginPageProperties.getProperty("password"),decryptedPassword);
        clickOnElement(loginPageProperties.getProperty("loginButton"));

    }


}
