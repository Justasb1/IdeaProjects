package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class Pigu {
    public static WebDriver browser;
    public static final String SEARCH_KEYWORD = "televizoriai";
    public static final int SECONDS_WAIT_TIME_FOR_ELEMENT = 2;

    public static void main(String[] args) {
        setup();
        closeCookies();
        System.out.println(isEnabledDisplayed(browser.findElement(By.className("sn-suggest-input"))));
        searchByKeyword(SEARCH_KEYWORD);
        System.out.println(findKeywordUrl(SEARCH_KEYWORD));
        waitElement(By.cssSelector("div[id^='_0productBlock']"));
        hoverElement(browser.findElement(By.cssSelector("div[id^='_0productBlock']")));
        clickOnElement(By.cssSelector(".product-name a"));
        System.out.println(checkTitle(SEARCH_KEYWORD));
        checkItemName(browser.findElement(By.tagName("h1")), SEARCH_KEYWORD);
        clickOnElement(By.cssSelector(".c-product__controls .c-btn--primary"));
        waitElement(By.id("modal"));
        isModalContains(browser.findElement(By.id("modal")),browser.findElement(By.className("add-to-cart-modal-title")),"prekė įtraukta");
        clickOnElement(By.id("close"));
        isEnabledDisplayed(browser.findElement(By.id("modal")));
        closeBrowser();
    }
    public static void ScreenShots() {
        String name = new SimpleDateFormat("yyyy_MM_dd_HH-mm-ss").format(new Date());
        File imgFile = ((TakesScreenshot) browser).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(imgFile, new File("src/test/screenshots/" + name + "_screenshot.png"));
        } catch (IOException error) {
            System.out.println("Nepavyko padaryti screenshot. Placiau:" + error.getMessage());
        }
    }
    public static void setup() {
        // Narsykles nustatymai
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--ignore-certificate-errors");

        browser = new ChromeDriver(chromeOptions);
        browser.get("https://pigu.lt");
    }
    public static void closeCookies() {
        WebElement notAgree = browser.findElement(By.className("c-link"));
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) browser;
        javascriptExecutor.executeScript("arguments[0].click()", notAgree);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean isEnabledDisplayed(WebElement element) {
        if (element.isDisplayed() && element.isEnabled()) {
            return true;
        } else {
            return false;
        }
    }
    public static void searchByKeyword(String keyword ) {
        WebElement searchInput = browser.findElement(By.className("sn-suggest-input"));
        searchInput.sendKeys(keyword);
        WebElement button = browser.findElement(By.className("c-icon--search"));
        button.click();
    }
    public static String findKeywordUrl(String keyword) {
        String newURL = browser.getCurrentUrl();
        if (newURL.contains(keyword)) {
            return keyword + " paieskos kriterijus atvaizduojamas adrese.";
        }
        return keyword + "paieskos kriterijus neatvaizduojamas adrese.";
    }
    public static void waitElement(By locator){
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(SECONDS_WAIT_TIME_FOR_ELEMENT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public static void hoverElement(WebElement element){
        Actions act = new Actions(browser);
        act.moveToElement(element).build().perform();
    }
    public static void clickOnElement(By locator){
        browser.findElement(locator).click();
    }
    public static String checkTitle(String keyword){
        String title = browser.getTitle().toLowerCase();
        if (title.contains(keyword.substring(0, keyword.length()-2))) {
            return "Puslapio pavadinime yra zodis " + keyword + ".";
        }else {
            return "Puslapio pavadinime nėra žodis '" + keyword + "'.";
        }
    }
    public static void checkItemName(WebElement element, String keyword){
        String h1 = element.getText().toLowerCase();
        if (!h1.contains(keyword.substring(0, keyword.length() - 2))) {
            System.out.println("Puslapis neprasideda zodziu " + keyword);
        }
    }
    public static boolean isModalContains(WebElement modal, WebElement modalTitle, String keyword){
        String title = modalTitle.getText();
        if (isEnabledDisplayed(modal) || title.contains(keyword)) {
            return true;
        }else{
            System.out.println("Modalo klaida");
            return false;
        }
    }
    public static void closeBrowser(){
        browser.quit();
    }
}
