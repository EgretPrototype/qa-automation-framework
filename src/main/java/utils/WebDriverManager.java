package utils;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.URL;
import java.time.Duration;

public class WebDriverManager {
    private static WebDriver driver;

    private WebDriverManager() {}

    public static WebDriver getDriver() {
        if (driver == null) {
            String browser = ConfigReader.get("browser");
            boolean isRemote = Boolean.parseBoolean(ConfigReader.get("remote"));
            try {
                if (isRemote) {
                    URL gridURL = new URL(ConfigReader.get("remoteUrl"));
                    switch (browser) {
                        case "chrome":
                            ChromeOptions chromeOptions = new ChromeOptions();
                            driver = new RemoteWebDriver(gridURL, chromeOptions);
                            break;
                        case "firefox":
                            FirefoxOptions firefoxOptions = new FirefoxOptions();
                            driver = new RemoteWebDriver(gridURL, firefoxOptions);
                            break;
                        default:
                            throw new RuntimeException("Remote browser not supported: " + browser);
                    }
                } else {
                    switch (browser) {
                        case "chrome":
                            driver = new ChromeDriver();
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
                }
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            } catch (Exception e) {
                throw new RuntimeException("Failed to initialize WebDeriver", e);
            }
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