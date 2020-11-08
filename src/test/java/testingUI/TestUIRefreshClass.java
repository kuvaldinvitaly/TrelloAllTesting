package testingUI;

import dataBaseManager.DBManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import webDriver.WebDriverManager;

import java.io.IOException;

public class TestUIRefreshClass {
    private WebDriver webDriver;

    @BeforeClass(groups = {"refresh"})
    public void setUp(){
        webDriver = WebDriverManager.getDriver();
        System.out.println("Удаление начато");
    }


    @Test(priority = 1, groups = {"refresh"})
    public void authenticationInTrello() throws IOException {
        webDriver.get("https://trello.com/login");
        WebElement webElement = webDriver.findElement(By.xpath("//*[@id='user']"));
        webElement.click();
        webElement.sendKeys(DBManager.getLogin());
        webDriver.findElement(By.xpath("//*[@id='login' and @value = 'Войти с помощью Atlassian']")).click();
//        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
//        webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id='password']")));

//        WebElement webElement2 = webDriver.findElement(By.xpath("//*[@id='password']"));
//        webElement2.click();
//        System.getProperties().load(ClassLoader.getSystemResourceAsStream("pass.properties"));
//        webElement2.sendKeys(System.getProperty("ui.pass"));
//        webDriver.findElement(By.xpath("//*[@id = 'login-submit']//span//span//span")).click();

    }

    @Test(priority = 2, groups = {"refresh"})
    public void deleteBoardAndCommand(){
        webDriver.findElement(By.xpath("//span[text()='Настройки']")).click();
        webDriver.findElement(By.xpath("//a[@class='quiet-button']/span")).click();
        webDriver.findElement(By.xpath("//input[@value='Удалить навсегда']")).click();


        webDriver.findElement(By.xpath("//div[@class = 'content-all-boards']//div[@title = 'Только для образования']")).click();
        webDriver.findElement(By.xpath("//span[text() = 'Меню']")).click();
        webDriver.findElement(By.xpath("//div[@class = 'board-menu-content-frame']//li[@class = 'board-menu-navigation-item']//a[@class = 'board-menu-navigation-item-link js-open-more']")).click();
        webDriver.findElement(By.xpath("//ul[@class = 'board-menu-navigation']//a[@class = 'board-menu-navigation-item-link js-close-board']")).click();
        webDriver.findElement(By.xpath("//input[@value = 'Закрыть']")).click();
        webDriver.findElement(By.xpath("//a[text() = 'Безвозвратное удаление доски…']")).click();
        webDriver.findElement(By.xpath("//input[@value = 'Удалить']")).click();


    }

    @AfterClass(groups = {"refresh"})
    public void close(){
        System.out.println("Удаление завершено");
        webDriver.quit();
    }


}
