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

public class Main {
    private static WebDriver browser;  // Declare WebDriver as a class-level variable
    public static final String SEARCH_KEYWORD = "televizoriai";

    public static final int WAIT_TIME_SECONDS = 2;
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
    public static String searchField() {
        WebElement searchInput = browser.findElement(By.className("sn-suggest-input"));
        if (searchInput.isDisplayed() && searchInput.isEnabled()) {
            return "Paieskos laukelis matomas ir aktyvus";
        } else {
            return  "Paieskos laukelis nematomas ir neaktyvus";
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
    public static WebElement waitForElementVisibility(By locator) {
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(WAIT_TIME_SECONDS));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return null;
    }
    public static void waitElement(By locator){
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(WAIT_TIME_SECONDS));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public static void hoverElement(WebElement element){
        Actions act = new Actions(browser);
        act.moveToElement(element).build().perform();
    }
    public static void clickOneElement(By locator){
        browser.findElement(locator).click();
    }
    public static void checkTitle(String keyword){
        String title = browser.getTitle().toLowerCase();
    }
    public static void checkItemName (WebElement element, String searchKeyword){
        String h1 = element.getText().toLowerCase();
        String keyword = null;
        if (!h1.contains(keyword.substring(0, keyword.length() - 2))) {
            System.out.println("Puslapis neprasideda zodziu " + keyword);
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        setup();
        closeCookies();
        System.out.println(searchField());
        searchByKeyword(SEARCH_KEYWORD);
        System.out.println(findKeywordUrl(SEARCH_KEYWORD));
        waitElement(By.cssSelector(("div[id^='_0productBlock']")));
        hoverElement(browser.findElement(new By.ByCssSelector("div[id^='_0productBlock']")));
        hoverElement(browser.findElement(new By.ByCssSelector("div[id^='_0productBlock']")));
        clickOneElement(By.cssSelector(".product-name a"));
        checkTitle(browser.getTitle());
        checkItemName(browser.findElement(By.tagName("h1")), SEARCH_KEYWORD);



        String title = browser.getTitle().toLowerCase();
        if (!title.contains(SEARCH_KEYWORD)) {
            System.out.println("Puslapio pavadinime nėra žodis '" + SEARCH_KEYWORD + "'.");
        }

        Actions act = new Actions(browser);
        // TODO: sukurti atskira metoda, laukimui
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(2));
        WebElement product = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id^='_0productBlock']")));
        act.moveToElement(product).build().perform();

        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product-name a")));
        JavascriptExecutor js = (JavascriptExecutor) browser;
        js.executeScript("arguments[0].click();", element);

        String h1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1"))).getText().toLowerCase();
        if (!h1.contains(SEARCH_KEYWORD.substring(0, SEARCH_KEYWORD.length() - 2))) {
            System.out.println("Puslapis neprasideda zodziu " + SEARCH_KEYWORD);
            System.exit(0);
        }

        WebElement toCart = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".c-product__controls .c-btn--primary")));
        toCart.click();

        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("modal")));
        String modalTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("add-to-cart-modal-title"))).getText();
        if (!modal.isDisplayed() || !modalTitle.contains("prekė įtraukta")) {
            System.out.println("Modalo klaida");
            System.exit(0);
        }

        browser.findElement(By.id("close")).click();

        if (modal.isDisplayed()) {
            System.out.println("Modalas atidarytas");
            System.exit(0);
        }

        browser.close();
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
