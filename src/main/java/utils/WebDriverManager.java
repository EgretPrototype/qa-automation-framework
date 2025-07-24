package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class WebDriverManager {
    private static WebDriver driver;

    private WebDriverManager(){}

    public static WebDriver getDriver(){
        if(driver == null) {
            String browser = ConfigReader.get("browser");

            switch (browser.toLowerCase()){
                case "chrome":
                    ChromeOptions options = new ChromeOptions();
                    String githubActions = System.getenv("GITHUB_ACTIONS");
                    boolean headless = githubActions != null && githubActions.equalsIgnoreCase("true");
                    if (headless) {
                        options.addArguments("--headless=new");
                        options.addArguments("--no-sandbox");
                        options.addArguments("--disable-dev-shm-usage");
                        options.addArguments("--window-size=1920,1080");
                        options.addArguments("--disable-gpu");
                        options.addArguments("--remote-allow-origins=*");
                    }
                    driver = new ChromeDriver(options);
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                case "safari":
                    driver = new SafariDriver();
                    break;
                default:
                    throw new RuntimeException("Unsupported browser: " + browser);
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
        return driver;
    }

    public static void quitDriver(){
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
