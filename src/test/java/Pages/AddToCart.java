package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class AddToCart {
    private final Page page;

    public AddToCart(Page page) {
        this.page = page;
    }

    public void OpenProduct(){
        page.locator("(//a[contains(text(),'View Product')])[1]").click();
        Locator Check = page.locator("//h2[normalize-space()='Blue Top']");
        page.waitForSelector("//h2[normalize-space()='Blue Top']");
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("OpenProduct.png"))
                .setFullPage(true));
        assertThat(Check).isVisible();
        System.out.println("Product page opened Successfully");
    }

    public void AddProductToCart(){
        page.locator("//button[normalize-space()='Add to cart']").click();
        Locator success = page.locator("//h4[normalize-space()='Added!']");
        page.waitForSelector("//h4[normalize-space()='Added!']");
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("ProductAddedSuccessfully.png"))
                .setFullPage(true));
        assertThat(success).isVisible();
        System.out.println("Product added successfully");
    }

    public void ContinueShoppingCart(){
        page.locator("//button[normalize-space()='Continue Shopping']").click();
        Locator check1 = page.locator("//a[normalize-space()='Write Your Review']");
        page.waitForSelector("//a[normalize-space()='Write Your Review']");
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("ContinueShopping.png"))
                .setFullPage(true));
        assertThat(check1).isVisible();
        System.out.println("Continue shopping cart");

    }
}
