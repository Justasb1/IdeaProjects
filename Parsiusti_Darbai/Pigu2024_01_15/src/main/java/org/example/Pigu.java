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
    public static WebDriver browser;  // Declare WebDriver as a class-level variable
    public static final String SEARCH_KEYWORD = "televizoriai";
    public static final int WAIT_DURATION_SECONDS = 2;
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
    public static boolean isEnabledAndDisplayed(By locator) {
        WebElement searchInput = browser.findElement(locator);
        if (searchInput.isDisplayed() && searchInput.isEnabled()) {
            return true;
        } else {
            return  false;
        }
    }
    public static void searchByKeyword(String keyword ) {
        WebElement searchInput = browser.findElement(By.className("sn-suggest-input"));
        searchInput.sendKeys(keyword);
        WebElement button = browser.findElement(By.className("c-icon--search"));
        button.click();
    }
    public static String findKeywordInUrl(String keyword) {
        String newURL = browser.getCurrentUrl();
        if (newURL.contains(keyword)) {
            return keyword + " paieskos kriterijus atvaizduojamas adrese.";
        }
        return keyword + "paieskos kriterijus neatvaizduojamas adrese.";
    }
    public static String findKeywordInTitle(String keyword){
        String title = browser.getTitle().toLowerCase();
        if (title.contains(keyword.substring(0,keyword.length()-2))) {
            return "Puslapio pavadinime yra žodis '" + keyword + "'.";
        }
        return "Puslapio pavadinime nėra žodis '" + keyword + "'.";
    }
    public static WebElement waitForElementVisibility(By locator) {
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(WAIT_DURATION_SECONDS));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public static void waitForElementClickability(By locator) {
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(WAIT_DURATION_SECONDS));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    public static void hoverElement(WebElement element){
        Actions act = new Actions(browser); //Imituojam pele
        act.moveToElement(element).build().perform();
    }
    public static void clickOnElement(By locator){
        browser.findElement(locator).click();
    }
    public static void checkHeader(WebElement element, String keyword){
        String h1 = element.getText().toLowerCase();
        if (!h1.contains(keyword.substring(0, keyword.length() - 2))) {
            System.out.println("Puslapis neprasideda zodziu " + keyword);
        }
    }
    public static boolean isModalDisplayed(By modalId, By modalTitleClass, String keyword){
        WebElement modal = browser.findElement(modalId);
        String modalTitle = browser.findElement(modalTitleClass).getText();
        if (modal.isDisplayed() || modalTitle.contains(keyword)) {
            return true;
        }
        System.out.println("Modalo klaida");
        return false;
    }
    public static void closeBrowser(){
        browser.quit();
    }
    public static void main(String[] args) {
        System.out.println("PIGU+Selenium+JUnit");
        setup();
        closeCookies();
        System.out.println(isEnabledAndDisplayed(By.className("sn-suggest-input")));
        searchByKeyword(SEARCH_KEYWORD);
        System.out.println(findKeywordInUrl(SEARCH_KEYWORD));
        waitForElementVisibility(By.cssSelector("div[id^='_0productBlock']"));
        hoverElement(browser.findElement(By.cssSelector("div[id^='_0productBlock']")));
        waitForElementVisibility(By.cssSelector(".product-name a"));
        clickOnElement(By.cssSelector(".product-name a"));
        System.out.println(findKeywordInTitle(SEARCH_KEYWORD));
        checkHeader(browser.findElement(By.tagName("h1")), SEARCH_KEYWORD);
        waitForElementClickability(By.cssSelector(".c-product__controls .c-btn--primary"));
        clickOnElement((By.cssSelector(".c-product__controls .c-btn--primary")));
        waitForElementVisibility(By.id("modal"));
        isModalDisplayed(By.id("modal"), By.className("add-to-cart-modal-title") , ("prekė įtraukta"));
        clickOnElement(By.id("close"));
        isEnabledAndDisplayed(By.id("modal"));
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
}
