package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Atsiskaitymas {
    public static WebDriver browser;  // Declare WebDriver as a class-level variable
    public static final int SECONDS_WAIT_TIME_FOR_ELEMENT = 2;
    public static final int WAIT_DURATION_SECONDS = 2;

    public static void setup() {
        // Narsykles nustatymai
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--ignore-certificate-errors");

        browser = new ChromeDriver(chromeOptions);
        browser.get("https://programavimoabc.lt/pica.php");
    }

    public static void picosName(String keyword) {
        WebElement picosLaukelis = browser.findElement(By.name("pavadinimas"));
        picosLaukelis.sendKeys(keyword);
    }

    public static void ingridientaiInput(String keyword) {
        WebElement ingridientuLaukelis = browser.findElement(By.name("ingridientai"));
        ingridientuLaukelis.sendKeys(keyword);
    }

    public static void padazoInput(String keyword) {
        WebElement padazoLaukelis = browser.findElement(By.name("padazas"));
        padazoLaukelis.sendKeys(keyword);
    }

    public static void papildaiInput(String keyword) {
        WebElement papilduLaukelis = browser.findElement(By.name("papildai"));
        papilduLaukelis.sendKeys(keyword);
    }

    public static void kainosInput(String keyword) {
        WebElement kainosLaukelis = browser.findElement(By.name("kaina"));
        kainosLaukelis.sendKeys(keyword);
    }

    public static void Click() {
        WebElement button = browser.findElement(By.name("insert"));
        button.click();
    }

    public static void Wait() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public static void closeBrowser() {
        browser.quit();
    }

    public static void idinput(String keyword) {
        WebElement idLaukelis = browser.findElement(By.name("id"));
        idLaukelis.sendKeys(keyword);
    }

    public static void ClickIstrinti() {
        WebElement button = browser.findElement(By.name("delete"));
        button.click();
    }

    public static void IdOutput() {
        WebElement messageBox = browser.findElement(By.className("msg-good"));
        String fieldStatus = "Istrinimas " + (messageBox.isDisplayed() && messageBox.isEnabled() ? "Pavyko" : "Nepavyko");
        System.out.println(fieldStatus);
    }

    public static void Clickredaguoti() {
        WebElement button = browser.findElement(By.name("update"));
        button.click();
    }


    public static void Output() {
        try {
            WebElement messageBox = browser.findElement(By.className("msg-good"));

            if (messageBox.isDisplayed() && messageBox.isEnabled()) {
                System.out.println("Irasymas Pavyko");
            } else {
                System.out.println("Positive message box found but not enabled or displayed");
            }
        } catch (NoSuchElementException e) {
            System.out.println("Positive message box not found");
        }

        try {
            WebElement messageBoxNegative = browser.findElement(By.className("msg-bad"));

            if (messageBoxNegative.isDisplayed() && messageBoxNegative.isEnabled()) {
                System.out.println("Irasymas Nepavyko");
            } else {
                System.out.println("Negative message box found but not enabled or displayed");
            }
        } catch (NoSuchElementException e) {
            System.out.println("Negative message box not found");
        }
    }

    public static void main(String[] args) {
        setup();

        picosName("Margarita");
        Wait();

        ingridientaiInput("Picos padas, suris, pomidoru padazas");
        Wait();

        padazoInput("Cesnakinis");
        Wait();

        papildaiInput("Daugiau surio");
        Wait();

        kainosInput("");
        Wait();

        Click();
        Wait();

        Output();
    }
}
