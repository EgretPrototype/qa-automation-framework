import Base.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.CategoriesPage;
@Listeners(utils.AllureTestListener.class)

@Feature("Category Menu")
public class CategoriesPageTest extends BaseTest {

    @Test(description = "Verify categories")
    @Description("Verifying the categories direct the user to the correct page when selected")
    public void verifyCategoriesMenu(){
        CategoriesPage categoriesPage = new CategoriesPage();
        SoftAssert sa = new SoftAssert();
        categoriesPage.selectCategories();
        String[] productGroups = {"Hand Tools", "Power Tools", "Other", "Special Tools", "Rentals"};
        for (String category : productGroups){
            logger.debug("Checking title for each category: {}", category);
            categoriesPage.selectCategoryByGroupName(category);
            String actualTitle = categoriesPage.getPageTitleText();
            String expectedTitle = category.equals("Rentals")
                    ? "Rentals"
                    : "Category: " + category;
            sa.assertEquals(actualTitle, expectedTitle, "Title mismatch for: " + category);
            categoriesPage.selectCategories();
        }
        sa.assertAll();
    }
}
