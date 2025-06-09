package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * Page Object Model class for handling product cart operations
 */
public class AddToCart {
    private final Page page;
    
    // XPath selectors
    private static final String VIEW_PRODUCT_BUTTON = "(//a[contains(text(),'View Product')])[1]";
    private static final String PRODUCT_TITLE = "//h2[normalize-space()='Blue Top']";
    private static final String ADD_TO_CART_BUTTON = "//button[normalize-space()='Add to cart']";
    private static final String ADDED_MESSAGE = "//h4[normalize-space()='Added!']";
    private static final String CONTINUE_SHOPPING_BUTTON = "//button[normalize-space()='Continue Shopping']";
    private static final String WRITE_REVIEW_LINK = "//a[normalize-space()='Write Your Review']";

    /**
     * Constructor for AddToCart class
     * @param page Playwright Page instance
     */
    public AddToCart(Page page) {
        this.page = page;
    }

    /**
     * Opens the product page and verifies the product title
     */
    public void openProduct() {
        page.locator(VIEW_PRODUCT_BUTTON).click();
        Locator productTitle = page.locator(PRODUCT_TITLE);
        page.waitForSelector(PRODUCT_TITLE);
        takeScreenshot("OpenProduct");
        assertThat(productTitle).isVisible();
        System.out.println("Product page opened successfully");
    }

    /**
     * Adds the product to cart and verifies the success message
     */
    public void addProductToCart() {
        page.locator(ADD_TO_CART_BUTTON).click();
        Locator successMessage = page.locator(ADDED_MESSAGE);
        page.waitForSelector(ADDED_MESSAGE);
        takeScreenshot("ProductAddedSuccessfully");
        assertThat(successMessage).isVisible();
        System.out.println("Product added successfully");
    }

    /**
     * Continues shopping and verifies the write review link is visible
     */
    public void continueShopping() {
        page.locator(CONTINUE_SHOPPING_BUTTON).click();
        Locator writeReviewLink = page.locator(WRITE_REVIEW_LINK);
        page.waitForSelector(WRITE_REVIEW_LINK);
        takeScreenshot("ContinueShopping");
        assertThat(writeReviewLink).isVisible();
        System.out.println("Continue shopping cart");
    }

    /**
     * Helper method to take screenshots
     * @param screenshotName Name of the screenshot file
     */
    private void takeScreenshot(String screenshotName) {
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get(screenshotName + ".png"))
                .setFullPage(true));
    }
}
