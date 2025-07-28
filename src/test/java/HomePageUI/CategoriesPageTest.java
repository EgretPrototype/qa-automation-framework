package HomePageUI;

import Base.BaseTest;
import io.qameta.allure.*;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import pageObjects.CategoriesPage;
import utils.AllureTestListener;

@Feature("Category Menu")
public class CategoriesPageTest extends BaseTest {

    @ExtendWith(AllureTestListener.class)
    @Test
    @Description("Verifying the categories direct the user to the correct page when selected")
    public void verifyCategoriesMenu(){
        CategoriesPage categoriesPage = new CategoriesPage();
        SoftAssertions sa = new SoftAssertions();
        categoriesPage.selectCategories();
        String[] productGroups = {"Hand Tools", "Power Tools", "Other", "Special Tools", "Rentals"};
        for (String category : productGroups){
            logger.debug("Checking title for each category: {}", category);
            categoriesPage.selectCategoryByGroupName(category);
            String actualTitle = categoriesPage.getPageTitleText();
            String expectedTitle = category.equals("Rentals")
                    ? "Rentals"
                    : "Category: " + category;
            sa.assertThat(actualTitle)
                    .as("Title mismatch for: " + category)
                    .isEqualTo(expectedTitle);
            categoriesPage.selectCategories();
        }
        sa.assertAll();
    }
}