package webDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.UnreachableBrowserException;

import java.util.concurrent.TimeUnit;

public class WebDriverManager {


    public static WebDriver driver;

    private WebDriverManager() {

    }

    public static WebDriver getDriver() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
            try {
                ChromeOptions option = new ChromeOptions();
                option.addArguments("--window-size=1920,1080");
                driver = new ChromeDriver(option);
            } catch (UnreachableBrowserException e) {
                System.err.println("Невозможно инциализировать драйвер!" + e);
            }
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        return driver;
    }

    public static void quit() {
        try {
            driver.quit();
            driver = null;
        } catch (UnreachableBrowserException e) {
            System.err.println("Невозможно закрыть браузер!");
        }
    }
}

