package org.example;

import org.openqa.selenium.By;
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
        browser.get("http://www.programavimoabc.lt/filmai.php");
    }

    public static void filmoInput(String keyword) {
        WebElement pavadinimoLaukelis = browser.findElement(By.name("pavadinimas"));
        pavadinimoLaukelis.sendKeys(keyword);
    }

    public static void zanroInput(String keyword) {
        WebElement zanroLaukelis = browser.findElement(By.name("zanras"));
        zanroLaukelis.sendKeys(keyword);
    }

    public static void aktoriuInput(String keyword) {
        WebElement aktoriuLaukelis = browser.findElement(By.name("aktoriai"));
        aktoriuLaukelis.sendKeys(keyword);
    }

    public static void rezisieriausInput(String keyword) {
        WebElement rezisieriausLaukelis = browser.findElement(By.name("rezisierius"));
        rezisieriausLaukelis.sendKeys(keyword);
   }

    public static void trukmesInput(String keyword) {
        WebElement trukmesLaukelis = browser.findElement(By.name("trukme"));
        trukmesLaukelis.sendKeys(keyword);
    }

    public static void Wait(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void Click(){
        WebElement button = browser.findElement(By.name("insert"));
        button.click();
    }

    public static void Output() {
        WebElement messageBox = browser.findElement(By.ClassName("msg-good"));
        WebElement messageBoxNegative = browser.findElement(By.ClassName("msg-bad"));
        String fieldStatus = "Irasymas " + (messageBox.isDisplayed() && messageBox.isEnabled() ? "Pavyko" : "Nepavyko");
        String fieldStatusNegative = "Irasymas " + (messageBoxNegative.isDisplayed() && messageBoxNegative.isEnabled() ? "Pavyko" : "Nepavyko");
        System.out.println(fieldStatus);
    }

    public static boolean isEnabledDisplayed(WebElement element) {
        return element.isDisplayed() && element.isEnabled();
    }

    public static void closeBrowser() {
        browser.quit();
    }



    //public static Object isEnabledDisplayed(WebElement element) {
    //    String fieldStatus = "Irasymas " + (element.isDisplayed() && element.isEnabled() ? "Pavyko" : "Nepavyko");
    //    System.out.println(fieldStatus);
    //    return null;
    //}

    

    public static void main(String[] args) {
        setup();

        filmoInput("Wonka");
        Wait();

        zanroInput("Adventure, Comedy, Family, Fantasy, Musical");
        Wait();

        aktoriuInput("Timothee Chalamet, Olivia Colman, Charlotte Ritchie");
        Wait();

        rezisieriausInput("Paul King");
        Wait();

        trukmesInput("116");
        Wait();

        Click();
        Wait();

        Output();

        //closeBrowser();
    }

}