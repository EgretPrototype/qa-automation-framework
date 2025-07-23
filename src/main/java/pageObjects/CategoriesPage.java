package pageObjects;

import Base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Listeners;
import utils.WebDriverManager;
@Listeners(utils.AllureTestListener.class)

public class CategoriesPage extends BasePage {



    public CategoriesPage() {
        super();
        this.driver = WebDriverManager.getDriver();
    }

    private static final By CATEGORIES = By.linkText("Categories");
    private static final String GROUP_NAME= "//a[text()= \"%s\"]";
    private static final By PAGE_TITLE = By.xpath("//*[@data-test='page-title']");

    @Step("Selecting the Categories menu from the homepage")
    public void selectCategories(){
        wait.until(ExpectedConditions.elementToBeClickable(CATEGORIES)).click();
    }

    @Step("Getting the page title text")
    public String getPageTitleText(){
        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_TITLE));
        logger.debug("Obtained page title {}", PAGE_TITLE);
      return titleElement.getText();
    }

    @Step("Selecting category by it's group name ")
    public void selectCategoryByGroupName(String category) {
        logger.info("Selecting category: " + category);
       driver.findElement(By.xpath(String.format(GROUP_NAME, category))).click();
    }
}
