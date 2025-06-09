package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * Page Object Model for the Login functionality of Automation Exercise website
 */
public class LoginPage {
    private final Page page;
    
    // XPath Selectors
    private static final String SIGNUP_LOGIN_LINK = "//a[normalize-space()='Signup / Login']";
    private static final String LOGIN_TITLE = "//h2[normalize-space()='Login to your account']";
    private static final String EMAIL_INPUT = "//input[@data-qa='login-email']";
    private static final String PASSWORD_INPUT = "//input[@placeholder='Password']";
    private static final String LOGIN_BUTTON = "//button[normalize-space()='Login']";
    private static final String LOGOUT_LINK = "//a[normalize-space()='Logout']";
    private static final String ERROR_MESSAGE = "//p[normalize-space()='Your email or password is incorrect!']";

    // Test Data
    private static final String VALID_EMAIL = "Ahmaddd+1111@gmail.com";
    private static final String VALID_PASSWORD = "123456Aa@";
    private static final String INVALID_EMAIL = "Ahmaddd+12111@gmail.com";
    private static final String INVALID_PASSWORD = "123456456Aa@";

    public LoginPage(Page page) {
        this.page = page;
    }

    /**
     * Opens the Automation Exercise website
     */
    public void openURL() {
        page.navigate("https://automationexercise.com/");
        assertThat(page).hasTitle("Automation Exercise");
        System.out.println("Page opened");
    }

    /**
     * Navigates to the login page
     */
    public void openLoginPage() {
        page.locator(SIGNUP_LOGIN_LINK).click();
        Locator loginTitle = page.locator(LOGIN_TITLE);
        page.waitForSelector(LOGIN_TITLE);
        assertThat(loginTitle).isVisible();
        System.out.println("Login page opened successfully");
    }

    /**
     * Performs login with valid credentials and logs out
     */
    public void loginWithValidCredentials() {
        login(VALID_EMAIL, VALID_PASSWORD);
        verifySuccessfulLogin();
        logout();
    }

    /**
     * Attempts login with invalid email
     */
    public void loginWithInvalidEmail() {
        login(INVALID_EMAIL, VALID_PASSWORD);
        verifyFailedLogin("LoginFailedWithInvalidEmail.png");
    }

    /**
     * Attempts login with invalid password
     */
    public void loginWithInvalidPassword() {
        login(VALID_EMAIL, INVALID_PASSWORD);
        verifyFailedLogin("LoginFailedWithInvalidPassword.png");
    }

    /**
     * Helper method to perform login
     */
    private void login(String email, String password) {
        page.locator(EMAIL_INPUT).fill(email);
        page.locator(PASSWORD_INPUT).fill(password);
        page.locator(LOGIN_BUTTON).click();
    }

    /**
     * Verifies successful login and performs logout
     */
    private void verifySuccessfulLogin() {
        Locator logoutLink = page.locator(LOGOUT_LINK);
        page.waitForSelector(LOGOUT_LINK);
        assertThat(logoutLink).isVisible();
        System.out.println("Success login");
        
        logout();
    }

    /**
     * Performs logout action
     */
    private void logout() {
        page.locator(LOGOUT_LINK).click();
        verifyLogout();
    }

    /**
     * Verifies successful logout
     */
    private void verifyLogout() {
        Locator loginTitle = page.locator(LOGIN_TITLE);
        page.waitForSelector(LOGIN_TITLE);
        takeScreenshot("LogoutSuccess.png");
        assertThat(loginTitle).isVisible();
        System.out.println("Success logout");
    }

    /**
     * Verifies failed login attempt
     */
    private void verifyFailedLogin(String screenshotName) {
        Locator errorMessage = page.locator(ERROR_MESSAGE);
        page.waitForSelector(ERROR_MESSAGE);
        takeScreenshot(screenshotName);
        assertThat(errorMessage).isVisible();
        System.out.println("Failed login with invalid credentials");
    }

    /**
     * Takes a screenshot of the current page
     */
    private void takeScreenshot(String fileName) {
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get(fileName))
                .setFullPage(true));
    }
}
