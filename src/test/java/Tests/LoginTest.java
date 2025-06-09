package Tests;

import BaseTest.LaunchBrowser;
import Pages.LoginPage;
import org.testng.annotations.Test;

public class LoginTest extends LaunchBrowser {
    @Test
    public void LoginTest1() {
        LoginPage loginpage = new LoginPage(page);
        loginpage.openURL();
        loginpage.openLoginPage();
        loginpage.loginWithValidCredentials();
        loginpage.loginWithInvalidEmail();
        loginpage.loginWithInvalidPassword();
    }
}
