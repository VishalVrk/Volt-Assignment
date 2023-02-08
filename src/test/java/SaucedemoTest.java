import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SaucedemoTest {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
    }

    @Test
    public void testLogin() {
        // Step 3: Click on the "Login" button
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        // Step 4: Enter a valid username
        WebElement usernameField = driver.findElement(By.id("user-name"));
        usernameField.sendKeys("standard_user");

        // Step 5: Enter a valid password
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("secret_sauce");

        // Step 6: Click on the "Login" button
        loginButton.click();

        // Step 7: Verify that the user is logged in
        WebElement title = driver.findElement(By.className("title"));
        Assert.assertEquals(title.getText().toLowerCase(), "products");

        // Step 8: Verify that all products are displayed
        WebElement productList = driver.findElement(By.className("inventory_list"));
        Assert.assertTrue(productList.isDisplayed());



//        Step 9: fetch values from the product details page
        WebElement itemName = driver.findElement(By.xpath("(//*[@class='inventory_item_name'])[1]"));
        String name = itemName.getText();
        WebElement ProductPrice = driver.findElement(By.xpath("(//*[@class='inventory_item_price'])[1]"));
        String price = ProductPrice.getText();

        // Step 10: View a product details
        WebElement firstProduct = driver.findElement(By.cssSelector("div:nth-child(1) > div.inventory_item_img"));
        firstProduct.click();

        // Step 11: Verify that the product details page loads
        WebElement productTitle = driver.findElement(By.cssSelector("[class='inventory_details_name large_size']"));
        WebElement productPrice = driver.findElement(By.cssSelector("[class='inventory_details_price']"));
        Assert.assertTrue(productTitle.isDisplayed());
        Assert.assertEquals(productTitle.getText(), name);
        Assert.assertEquals(productPrice.getText(), price);

        // Step 12: Add the product to the cart
        WebElement addToCartButton = driver.findElement(By.cssSelector("[class='btn btn_primary btn_small btn_inventory']"));
        addToCartButton.click();

        // Step 13: Verify that the product is added to the cart
        WebElement cartIcon = driver.findElement(By.cssSelector("[class='shopping_cart_badge']"));
        Assert.assertTrue(cartIcon.isDisplayed());

        // Step 14: View the cart
        WebElement cartButton = driver.findElement(By.cssSelector("[class='shopping_cart_badge']"));
        cartButton.click();

        // Step 15: Verify that the product is in the cart
        WebElement cartProduct = driver.findElement(By.cssSelector("[class='cart_item']"));
        Assert.assertTrue(cartProduct.isDisplayed());

        // Step 16: Checkout
        WebElement checkoutButton = driver.findElement(By.cssSelector("[name='checkout']"));
        checkoutButton.click();

        // Step 17: Enter the first name
        WebElement firstNameField = driver.findElement(By.id("first-name"));
        firstNameField.sendKeys("Volodymyr");

        // Step 18: Enter the last name
        WebElement lastNameField = driver.findElement(By.id("last-name"));
        lastNameField.sendKeys("Kovalenko");

        // Step 19: Enter the postal code
        WebElement postalCodeField = driver.findElement(By.id("postal-code"));
        postalCodeField.sendKeys("12345");

        // Step 20: Click the continue button
        WebElement continueButton = driver.findElement(By.cssSelector("[data-test='continue']"));
        continueButton.click();

        // Step 21: Verify that the "Finish" button is displayed
        WebElement finishButton = driver.findElement(By.cssSelector("[data-test='finish']"));
        Assert.assertTrue(finishButton.isDisplayed());
        finishButton.click();

//        Step 22: Verify that the "Finish" button is displayed
        WebElement thankYouMessage = driver.findElement(By.cssSelector("[class='complete-header']"));
        Assert.assertTrue(thankYouMessage.isDisplayed());
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
