package Base;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.AllureTestListener;
import utils.ConfigReader;
import utils.WebDriverManager;

@ExtendWith(AllureTestListener.class)
public class BaseTest {
    protected WebDriver driver;
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeforeEach
    public void setUp() {
        logger.info("Starting setup");
        driver = WebDriverManager.getDriver();
        driver.get(ConfigReader.get("url"));
    }

    @AfterEach
    public void tearDown(TestInfo testInfo) {
        logger.info("Tearing down WebDriver");

        if (driver != null) {
            // Attach screenshot for passed and failed tests
            AllureTestListener.saveScreenshotPNG(driver, "Final Screenshot (After Test)");
        }

        if (WebDriverManager.isDriverActive()) {
            AllureTestListener.saveScreenshotPNG(driver, testInfo.getDisplayName());
        } else {
            logger.warn("Driver is already null or session is closed. Skipping screenshot.");
        }

        WebDriverManager.quitDriver();
    }
}