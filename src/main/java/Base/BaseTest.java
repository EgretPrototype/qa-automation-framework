package Base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;
import utils.WebDriverManager;

public class BaseTest {
    protected WebDriver driver;
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeforeMethod
    public void setUp() {
        logger.info("Starting setup");
        WebDriver driver = WebDriverManager.getDriver();
        driver.get(ConfigReader.get("url"));
    }

    @AfterMethod
    public void tearDown(){
        logger.info("Tearing down WebDriver");
        WebDriverManager.quitDriver();
    }

    public WebDriver getDriver(){
        return driver;
    }
}
