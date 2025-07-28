package utils;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class WebDriverManager {
    private static WebDriver driver;

    private WebDriverManager() {}

    public static WebDriver getDriver() {
        if (driver == null) {
            String browser = ConfigReader.get("browser");
            switch (browser.toLowerCase()) {
                case "chrome":
                    ChromeOptions options = new ChromeOptions();
                    String githubActions = System.getenv("GITHUB_ACTIONS");
                    boolean headless = githubActions != null && githubActions.equalsIgnoreCase("true");
                    if (headless) {
                        options.addArguments(
                                "--headless=new",
                                "--no-sandbox",
                                "--disable-dev-shm-usage",
                                "--disable-gpu",
                                "--remote-allow-origins=*",
                                "--window-size=1920,1080",
                                "user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/138.0.0.0 Safari/537.36"
                        );
                    }
                    driver = new ChromeDriver(options);

                    if (headless) {
                        driver.manage().window().setSize(new Dimension(1920, 1080));
                    } else {
                        driver.manage().window().maximize();
                    }
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

    public static void quitDriver() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.err.println("Error quitting driver: " + e.getMessage());
            } finally {
                driver = null;
            }
        }
    }

    public static boolean isDriverActive() {
        return driver instanceof RemoteWebDriver && ((RemoteWebDriver) driver).getSessionId() != null;
    }
}
