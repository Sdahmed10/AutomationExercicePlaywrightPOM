package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginPage {
    private final Page page;

    public LoginPage(Page page) {
        this.page = page;
    }

    public void OpenURL(){
        page.navigate("https://automationexercise.com/");
        assertThat(page).hasTitle("Automation Exercise");
        System.out.println("Page opened");
    }

    public void OpenLoginPage(){
        page.locator("//a[normalize-space()='Signup / Login']").click();
        Locator text = page.locator("//h2[normalize-space()='Login to your account']");
        page.waitForSelector("//h2[normalize-space()='Login to your account']");
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("OpenLoginPage.png"))
                .setFullPage(true));
        assertThat(text).isVisible();
        System.out.println("Login page opened successfully");
    }

    public void Login(){
        page.locator("//input[@data-qa='login-email']").fill("Ahmaddd+1111@gmail.com");
        page.locator("//input[@placeholder='Password']").fill("123456Aa@");
        page.locator("//button[normalize-space()='Login']").click();
        Locator SuccessLogin = page.locator("//a[normalize-space()='Logout']");
        page.waitForSelector("//a[normalize-space()='Logout']");
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("LoginSuccess.png"))
                .setFullPage(true));
        assertThat(SuccessLogin).isVisible();
        System.out.println("Success login");
    }
}
