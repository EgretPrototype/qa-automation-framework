package utils;

import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Field;
import java.util.Optional;

public class AllureTestListener implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        System.out.println("❌ Test Failed: " + context.getDisplayName());
        captureScreenshotFromContext(context, "Screenshot on Failure");
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        System.out.println("⚠️ Test Skipped: " + context.getDisplayName());
        captureScreenshotFromContext(context, "Screenshot on Skipped");
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        System.out.println("✅ Test Passed: " + context.getDisplayName());

    }

    private void captureScreenshotFromContext(ExtensionContext context, String title) {
        Optional<Object> testInstance = context.getTestInstance();

        if (testInstance.isPresent()) {
            Object testObj = testInstance.get();
            System.out.println("🔍 Test class: " + testObj.getClass().getName());

            try {
                Field driverField = findDriverField(testObj.getClass());

                if (driverField != null) {
                    driverField.setAccessible(true);
                    Object driverObject = driverField.get(testObj);

                    if (driverObject != null && driverObject instanceof WebDriver) {
                        WebDriver driver = (WebDriver) driverObject;
                        System.out.println("📸 Capturing screenshot with driver: " + driver);
                        saveScreenshotPNG(driver, title);
                    } else {
                        System.err.println("⚠️ Found 'driver' field, but it was null or not a WebDriver.");
                    }
                } else {
                    System.err.println("⚠️ No 'driver' field found in test class or its superclasses.");
                }
            } catch (Exception e) {
                System.err.println("❗ Exception during screenshot capture:");
                e.printStackTrace();
            }
        } else {
            System.err.println("❗ No test instance found in ExtensionContext.");
        }
    }


    private Field findDriverField(Class<?> clazz) {
        while (clazz != null) {
            try {
                return clazz.getDeclaredField("driver");
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass(); // Check superclasses (e.g., BaseTest)
            }
        }
        return null;
    }

    @Attachment(value = "{title}", type = "image/png")
    public static byte[] saveScreenshotPNG(WebDriver driver, String title) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
