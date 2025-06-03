package BaseTest;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class LaunchBrowser {
    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeTest
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterTest
    public void tearDown() {
        context.close();
        browser.close();
        playwright.close();
    }
}
