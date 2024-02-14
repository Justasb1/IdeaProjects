package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Main {
    public static final String SEARCH_KEYWORD = "televizoriai"; // didžiosiom raidėmis apsirašo globalus kintamasis

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver160.exe");

        ChromeOptions chromeOptions = new ChromeOptions(); // Naršyklės nustatymai
        chromeOptions.addArguments("--start-maximized"); // max naršyklės langas
        chromeOptions.addArguments("--ignore-certificate-errors"); // ignoruoja sertifikato klaidas, t.y. kai prisijungimas nėra saugus

        WebDriver browser = new ChromeDriver(chromeOptions);
//        browser.manage().window().maximize(); // max naršyklės langas
        browser.get("https://pigu.lt/lt");

        WebElement reject = browser.findElement(By.className("c-link"));
        reject.click();
        Thread.sleep(2000);
//        browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

        WebElement searchField = browser.findElement(By.id("searchInput"));
        // Naudojamas Explicit Wait (aiškus laukimas), nurodant iki kiek laiko bus laukiama užkraunamų elementų
        WebDriverWait webDriverWait = new WebDriverWait(browser,Duration.ofSeconds(1));
        // Lauksime paieškos laukelio pasirodymo
        webDriverWait.until(ExpectedConditions.visibilityOf(searchField));

        if (searchField.isDisplayed() && searchField.isEnabled()){
            System.out.println("Paieškos langelis matomas ir priima tekstą.");
            searchField.sendKeys(SEARCH_KEYWORD);
        }
        else {
            System.out.println("Paieškos langelio NĖRA.");
            System.exit(1);
        }

        WebElement searchIcon = browser.findElement(By.className("c-icon--search"));
        searchIcon.click();

        String searchUrl = browser.getCurrentUrl();
        System.out.println(searchUrl);
        if (searchUrl.contains(SEARCH_KEYWORD)){
            System.out.println("Paieškos kriterijus '" + SEARCH_KEYWORD + "' atvaizduojamas url adrese");
        }
        else {
            System.out.println("Paieškos kriterijus '\" + SEARCH_KEYWORD + \"' NĖRA atvaizduojamas url adrese.");
            System.exit(1);
        }

        String searchTitle = browser.getTitle().toLowerCase();
//        System.out.println(searchTitle);
        if (!searchTitle.contains(SEARCH_KEYWORD)){
            System.out.println("Paieškos kriterijus '" + SEARCH_KEYWORD + "' NĖRA atvaizduojamas pavadinime.");
        }

//        WebElement firstItem = browser.findElement(By.className("image-list-container"));
//        firstItem.click();
//        WebElement firstItem = browser.findElement(By.className("image-list-container"));

        // Action klasė simuliuoja vartotojo veiksmus (klaviatūrą arba pelę)
        // moveToElement() naudojamas nuėjimui iki elemento ir užvedimui pelės
        // jeigu neužvedama pelė ant elemento, neina paspausti nuorodos
        // Šiam metodui paduodamas WebElement. Būtini build() - sukuria veiksmą ir perform() - atlieka veiksmą
        // Css selektoriuje pasirenkamas div elementas, kuris turi žodį 'productBlock'
        // ^ reiškia, kad ieškosime id elemento, kuris prasideda iš nurodytų simbolių (productBlock)
        // * reiškia, kad ieškosime id elemento, kuriame yra nurodyti simboliai (productBlock)
        Actions action = new Actions(browser);
        //WebElement firstItem = browser.findElement(By.cssSelector("div[id^='_0productBlock']"));
        WebElement firstItem = browser.findElement(By.cssSelector("div[id*='productBlock']"));
        action.moveToElement(firstItem).build().perform();
        Thread.sleep(1000);

        browser.findElement(By.cssSelector(".product-name a")).click();
        Thread.sleep(1000);
//        // Jei neveikia, tuomet su JavascriptExecutor:
//        WebElement element = browser.findElement(By.cssSelector(".product-name a"));
//        JavascriptExecutor js = (JavascriptExecutor)browser;
//        js.executeScript("arguments[0].click();", element);

        String h1 = browser.findElement(By.tagName("h1")).getText().toLowerCase();
        if (!h1.contains(SEARCH_KEYWORD.substring(0,SEARCH_KEYWORD.length()-2))){ // numetam galūnę (ai) iš žodžio televizoriai
            System.out.println("Puslapis neprasideda žodžiu " + SEARCH_KEYWORD);
            System.exit(1);
        }

        WebElement cart = browser.findElement(By.cssSelector(".c-product__controls .c-btn--primary"));
        cart.click();
        Thread.sleep(1000);

        WebElement modal = browser.findElement(By.cssSelector("#modal"));
        String modalTitle = browser.findElement(By.className("add-to-cart-modal-title")).getText();
        if (!modal.isDisplayed() || !modalTitle.contains("prekė įtraukta")){
            System.out.println("Modalas NĖRA rodomas arba prekė į krepšelį NEĮTRAUKTA.");
            System.exit(1);
        }

//        browser.findElement(By.id("buy")).click();

        browser.findElement(By.id("close")).click();

        if (modal.isDisplayed()){
            System.out.println("Modalas NEUŽDARYTAS.");
            System.exit(1);
        }

        browser.close();

        /*WebElement firstItem = browser.findElement(By.className("image-list-container"));
        firstItem.click();

        WebElement cart = browser.findElement(By.className("h-btn-intent--atc"));
//        WebElement cart = browser.findElement(By.xpath("//*[@id=\"product-sidebar\"]/div[1]/div[4]/div/div[1]"));
        cart.click();
        Thread.sleep(1000);

        WebElement modalTitle = browser.findElement(By.className("add-to-cart-modal-title"));
        String modalText = modalTitle.getText();
        System.out.println(modalText);
        if (modalText.contains("prekė įtraukta į krepšelį")){
            System.out.println("Prekė '" + SEARCH_KEYWORD + "' įtraukta į krepšelį.");
        }
        else {
            System.out.println("Prekė į krepšelį neįtraukta.");
        }

        WebElement close = browser.findElement(By.id("close"));
        close.click();

//        WebElement buy = browser.findElement(By.id("buy"));
//        buy.click();

//        browser.close();
*/

    }
}