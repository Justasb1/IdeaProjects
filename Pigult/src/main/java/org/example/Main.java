package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Main {
    public static final String  Search_Keyword = "Televizoriai";
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Selenium+Maven");

        System.setProperty( // Instrukcija sistemai susieti driverius ir daugiau...
                // Nurodomas driver tipas narsykleje.
                "webdriver.chrome.driver",
                // Kelias iki driver
                "drivers/chromedriver.exe"
        );

        // Pradedam chrome fulscreen
        ChromeOptions chromeOptions = new ChromeOptions(); // Narsykles nystatymai
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--ignore-certificate-errors");

        // Sukuriamas chrome driver obejktas
        WebDriver browser = new ChromeDriver(chromeOptions);
        // Nurodomo kuri atidaryti tinklapi
        browser.get("https://pigu.lt/lt/");




        // WebElement button = browser.findElement(By.xpath("//*[@id=\"cookie_block\"]/div/div/div[2]/div[2]/button[3]").click();

        // String fieldStatus = "Langelis " + (searchfield.isDisplayed() && searchfield.isEnabled() ? "Yra" : "Nera");
        // System.out.println(fieldStatus);

        // Paspaudziam reject cookies
        WebElement reject = browser.findElement(By.className("c-link"));
        reject.click();
        Thread.sleep(1000);
        //browser.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        WebElement searchField = browser.findElement(By.id("searchInput"));
        WebDriverWait webDriverWait =  new WebDriverWait(browser, Duration.ofSeconds(1));
        webDriverWait.until(ExpectedConditions.visibilityOf(searchField));

        WebElement searchfield = browser.findElement(By.id("searchInput"));
        if(searchfield.isDisplayed() && searchfield.isEnabled()) {
            System.out.println("Paieskos kriterijus '" + Search_Keyword + "' atvaizduojamas url adrese");
            searchfield.sendKeys(Search_Keyword);
        }
        else {
            System.out.println("Paieskos kriterijus '\" + Search_Keyword + \"'NERA atvaizduojamas url adrese.");
            System.exit(1); // programa baigia darba
        }
        WebElement searchIcon = browser.findElement(By.className("c-icon--search"));
        searchIcon.click();

        String searchTitle = browser.getTitle().toLowerCase();
        // System.out.println(searchTitle);
        if (!searchTitle.contains(Search_Keyword)) {
            System.out.println("Paieskos kriterijus '" + Search_Keyword + "' NERA atvaizduojmas pavadinime.");
        }

        // Paspaudziam ant pirmo ismesto rezultato
        // WebElement firstItem = browser.findElement(By.className("image-list-container"));
        // firstItem.click();
        // WebElement firstItem = browser.findElement((By.className("image-list-container")));
        // Action klase simuliuoja vartotojo veiksmus (klaviatura arba pele)
        // moveToElement() naudojamas  nuejimui iki elemento ir uzvedimui peles
        // jeigu neuzvedama pele ant elemento neina paspausti nuorodos
        // siam metodui paduodamas WebElement
        // .build skirtas peles uzvedimo simulaicijai sukurti, .perfrom skirtas peles veiksmo simuliacijai atlitki.
        // css selectoriuje pasirenkamas div elemntas, kuris turi zodi 'prodiuctBlock'
        // ^ reiskia kad ieskosime id elemnto kuris prasides po = nurodytais zodziais (productBlock)
        Actions action = new Actions(browser);
        WebElement firstItem = browser.findElement(By.cssSelector("div[id^='_0productBlock']"));
        action.moveToElement(firstItem).build().perform();
        Thread.sleep(1000);


        browser.findElement(By.cssSelector(".product-name a")).click();
        Thread.sleep(1000);
        // Jei neveikia tai.
        // WebElement element = narsykle.findElement(By.cssSelector(".product-name a"));
        // JavascriptExecutor js = (JavascriptExecutor)narsykle;
        // js.executeScript("arguments[0].click();", element);

        String h1 = browser.findElement(By.tagName("h1")).getText().toLowerCase();
        if (!h1.contains(Search_Keyword.substring(0, Search_Keyword.length()-2))){ // numetam galune (ai) is zodzio televizoriai
            System.out.println("Puslapis neprasideda zodziu " + Search_Keyword);
            // System .exit(1);
        }
        Thread.sleep(1000);

        WebElement cart = browser.findElement(By.cssSelector(".c-product__controls .c-btn--primary"));
        cart.click();
        Thread.sleep(1000);

        WebElement modal = browser.findElement(By.cssSelector("#modal"));
        String modalTitle = browser.findElement(By.className("add-to-cart-modal-title")).getText();
        if (!modal.isDisplayed() || !modalTitle.contains(" prekė įtraukta")){
            System.out.println("Modalas NERA rodomas arba preke i krepseli neitraukta");
            System.exit(1);
        }

        browser.findElement(By.id("close")).click();

        if (!modal.isDisplayed()){
            System.out.println("Modalas NEUZDARYTAS.");
            System.exit(1);
        }
        //String naujasURL = browser.getCurrentUrl();
        //if(naujasURL.contains(Search_Keyword)){
            //System.out.println(Search_Keyword + );

        browser.close();

        }

        // System.out
    }
