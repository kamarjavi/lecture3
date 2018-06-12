import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public class lecture3 {
    public static void main(String[] args) {
        WebDriver driver = initFirefoxDriver();
        driver.navigate().to("http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/");
        WebElement email = driver.findElement(By.id("email"));
        String login = "webinar.test@gmail.com";
        email.sendKeys(login);
        WebElement passwd = driver.findElement(By.id("passwd"));
        passwd.sendKeys("Xcg7299bnSmMuRLp9ITw");
        passwd.submit();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Actions action = new Actions(driver);
        WebElement catalog = driver.findElement(By.id("subtab-AdminCatalog"));
        action.moveToElement(catalog).perform();
        WebElement category = driver.findElement(By.id("subtab-AdminCategories"));
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(category));
        category.click();

        WebElement create_category = driver.findElement(By.id("page-header-desc-category-new_category"));
        create_category.click();
        WebElement name_category = driver.findElement(By.id("name_1"));
        String category_name = "new category";
        name_category.sendKeys(category_name);
        driver.findElement(By.id("category_form_submit_btn")).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector("tr.nodrag:nth-child(1) > th:nth-child(3) > span:nth-child(1) > a:nth-child(2) > i:nth-child(1)")).click();
        WebElement check = driver.findElement(By.xpath("//*[contains(text(), category_name)]"));
        if (check.isDisplayed()) {
            System.out.println("Категория создана");
        } else {
            System.out.println("Категория не создана");
        }
    }

    public static WebDriver initFirefoxDriver() {
        System.setProperty("webdriver.gecko.driver", lecture3.class.getResource("geckodriver.exe").getPath());
        return new FirefoxDriver();
    }
}


