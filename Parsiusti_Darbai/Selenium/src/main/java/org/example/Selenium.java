package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class Selenium {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Selenium+Maven");
        // Instrukcija sistemai, ka ji turi padaryti
        System.setProperty(
                "webdriver.chrome.driver", // Nurodomas driver tipas narsyklei
                "drivers/chromedriver120.exe" // Kelias iki driver
        );

        WebDriver narsykle = new ChromeDriver(); // Sukuriamas Chromedriver klases objektas
        narsykle.get("https://www.bing.com"); // Nurodom puslapi kuri atidarom

        // Surandamas elementas, kurio id yra sb_form_q
        WebElement paieskosLaukelis = narsykle.findElement(By.id("sb_form_q"));
        // Ivesim ieskoma zodi
        paieskosLaukelis.sendKeys("Baranauskas");
        // Simuliuojam enter paspaudima
        //paieskosLaukelis.sendKeys(Keys.ENTER);
        WebElement searchIcon = narsykle.findElement(By.id("search_icon"));
        //searchIcon.click();
        // Jeigu nepavyksta paspausti mygtuko
        // Siuolaikiniai puslapiai prikrauti javascript ir nauju technologiju
        // Todel kartais negalima paspausti mygtuku click kaip noretusi
        // Todel kartais mygtuko paspaudimui reikes naudoti ->
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) narsykle;
        // Mygtukas paspaudziamas vygdant script'a
        javascriptExecutor.executeScript("arguments[0].click()", searchIcon);


        Thread.sleep(1000);

        WebElement result = narsykle.findElement(By.className("sb_count"));
        System.out.println(result.getText());

        String paieskosResultatai = result.getText()
                        .replaceAll("[a-zA-Z]", "")
                        .replaceAll("[ąčęėįšųūž]", "")
                        .replaceAll("[ ,]","");

        int resultInt = Integer.parseInt(paieskosResultatai);
        System.out.println(resultInt);
        if (resultInt >= 50000) {
            System.out.println("Jaunimas dar neužmiršo Anykščių šilelio.");
        } else {
            System.out.println("Rašytojas nelabai populiarus internetinėse platybėse.");
        }
        narsykle.close();
    }
}