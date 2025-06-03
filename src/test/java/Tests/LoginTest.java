package Tests;

import BaseTest.LaunchBrowser;
import Pages.LoginPage;
import org.testng.annotations.Test;

public class LoginTest extends LaunchBrowser {
    @Test
    public void LoginTest1() {
        LoginPage loginpage = new LoginPage(page);
        loginpage.OpenURL();
        loginpage.OpenLoginPage();
        loginpage.Login();
    }
}
