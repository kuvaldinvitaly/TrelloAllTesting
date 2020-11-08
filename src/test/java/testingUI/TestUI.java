package testingUI;

import dataBaseManager.DBManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import webDriver.WebDriverManager;

import java.io.IOException;
import java.util.List;

public class TestUI {
    private WebDriver webDriver;

    @BeforeClass(groups = {"UITest"})
    public void setUp(){
        webDriver = WebDriverManager.getDriver();
        System.out.println("Начался выполняться UI тест");
    }


    @Test (priority = 1, groups = {"UITest"})
    public void authenticationInTrello() throws InterruptedException, IOException {
        webDriver.get("https://trello.com/login");
        WebElement webElement = webDriver.findElement(By.xpath("//*[@id='user']"));
        webElement.click();
        webElement.sendKeys(DBManager.getLogin());
        webDriver.findElement(By.xpath("//*[@id='login' and @value = 'Войти с помощью Atlassian']")).click();

        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id='password']")));


        WebElement webElement2 = webDriver.findElement(By.xpath("//*[@id='password']"));
        webElement2.click();
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("pass.properties"));
        webElement2.sendKeys(System.getProperty("ui.pass"));
        webDriver.findElement(By.xpath("//*[@id = 'login-submit']//span//span//span")).click();

    }

    @Test (priority = 2, groups = {"UITest"})
    public void checkCardInColumn() throws InterruptedException {
//        webDriver.findElement(By.xpath("//div[@class = 'content-all-boards']//div[@title = 'KanbanTool']")).click();

//        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
//        webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@title = 'KanbanTool']")));

        Thread.sleep(3000);
        webDriver.findElement(By.xpath("//div[@title = 'KanbanTool']")).click();
        WebElement webElement = webDriver.findElement(By.xpath("//div[@id= 'content']//textarea[text()='Done']/../..//span[text()='Карточка для изучения API']"));
        Assert.assertNotNull(webElement, "Карточка не находится в колонке Done");
    }

    @Test(priority = 3, groups = {"UITest"})
    public void checkCheckBoxes(){
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class= 'list-cards u-fancy-scrollbar u-clearfix js-list-cards js-sortable ui-sortable']/a")));
        webDriver.findElement(By.xpath("//div[@class= 'list-cards u-fancy-scrollbar u-clearfix js-list-cards js-sortable ui-sortable']/a")).click();
        List<WebElement> listCheckBoxes =  webDriver.findElements(By.xpath("//div[@class = 'window-main-col']//div[@class = 'checklist-items-list js-checklist-items-list js-no-higher-edits ui-sortable']/div[contains(@class, 'complete')]"));
        if (listCheckBoxes.size() != 2){
        Assert.fail("Не все чек-боксы находятся в состоянии \"Выбран\"");
        }
    }


    @Test(priority = 4, groups = "UITest")
    public void updateColorCoverToGreen(){
        webDriver.findElement(By.xpath("//div[@class = 'window-cover-menu']/a")).click();
        webDriver.findElement(By.xpath("//div[@class = 'js-react-root']//div//button[@class = '_9HlyjDStZT9Tkt _VjutK-Uakd6Op']")).click();
        webDriver.findElement(By.xpath("//div[@class = 'pop-over-header js-pop-over-header']//a")).click();

    }

    @Test(priority = 5, groups = "UITest")
    public void doneJobOnTime(){
        webDriver.findElement(By.xpath("//div[@class = 'card-detail-item js-card-detail-due-date']//div/a")).click();
        webDriver.findElement(By.xpath("//div[@class = 'window-overlay']//div//div//a")).click();
    }

    @Test(priority = 6, groups = "UITest")
    public void updateColorBoardToGreen(){
//        webDriver.findElement(By.xpath("//span[text() = 'Меню']")).click();
        webDriver.findElement(By.xpath("//div[@class = 'board-menu-content-frame']//li[@class = 'board-menu-navigation-item mod-background']//a")).click();
        webDriver.findElement(By.xpath("//div[@class = 'board-backgrounds-section-tile board-backgrounds-colors-tile js-bg-colors']//div[text() = 'Цвета']")).click();
        webDriver.findElement(By.xpath("//div[@class = 'board-menu-content-frame']//div[@style = 'background-color: rgb(81, 152, 57);']")).click();
        webDriver.findElement(By.xpath("//a[@title = 'Закрыть меню доски.']")).click();
    }

    @Test(priority = 7, groups = "UITest")
    public void updateBoardNameAndType() throws InterruptedException {
        webDriver.findElement(By.xpath("//span[@class= 'board-header-btn-icon icon-sm icon-private']")).click();
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[text()= 'Командная ']")));
        Thread.sleep(3000);
        webDriver.findElement(By.xpath("//a[text()= 'Командная ']")).click();
        webDriver.findElement(By.xpath("//a[text()= 'Создать команду']")).click();

        WebElement webElement = webDriver.findElement(By.xpath("//input[@id = 'org-display-name']"));
        webElement.click();
        webElement.sendKeys("Ученики");

        webDriver.findElement(By.xpath("//div[@class = 'js-team-type-select create-org-team-type-select']/div/div/div/div/div")).click();
        webDriver.findElement(By.xpath("//li[contains(text(), 'Образование')]")).click();
        webDriver.findElement(By.xpath("//input[@value = 'Создать']")).click();
    }

    @Test(priority = 8, groups = "UITest")
    public void changeBoardName(){
        webDriver.findElement(By.xpath("//div[@title = 'KanbanTool']")).click();
        webDriver.findElement(By.xpath("//div[@class = 'board-header-btn mod-board-name inline-rename-board js-rename-board']/h1")).click();

        WebElement webElement = webDriver.findElement(By.xpath("//div[@class = 'board-header-btn mod-board-name inline-rename-board js-rename-board is-editing']/input"));
        webElement.click();
        webElement.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        webElement.sendKeys("Только для образования");

        webDriver.findElement(By.xpath("//div[@id = 'board']")).click();

    }


    @AfterClass(groups = "UITest")
    public void close(){
//        webDriver.close();
        System.out.println("UI тест завершен");

    }


}

