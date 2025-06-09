package Tests;

import BaseTest.LaunchBrowser;
import Pages.AddToCart;
import Pages.LoginPage;
import org.testng.annotations.Test;

public class AddToCartTest extends LaunchBrowser {

    @Test(priority = 1)
    public void LoginTest(){
        LoginPage loginPage = new LoginPage(page);
        loginPage.openURL();
        loginPage.openLoginPage();
        loginPage.loginWithValidCredentials();
    }

    @Test(priority = 2)
    public void AddToCart1(){
        AddToCart addToCartTest = new AddToCart(page);
        addToCartTest.openProduct();
        addToCartTest.addProductToCart();
        addToCartTest.continueShopping();
    }
}
