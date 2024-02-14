package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Selenium {
    public static void main(String[] args) {
        System.out.println("Selenium + Maven");

        System.setProperty( // Instrukcija sistemai susieti driverius ir daugiau...
                // Nurodomas driver tipas narsykleje.
                "webdriver.chrome.driver",
                // Kelias iki driver
                "drivers/chromedriver.exe"
        );

        // Sukuriamas chrome driver obejktas
        WebDriver browser = new ChromeDriver();
        // Nurodomo kuri atidaryti tinklapi
        browser.get("https://www.bing.com/");

        // WebElement button = browser.findElement(By.xpath("//*[@id=\"cookie_block\"]/div/div/div[2]/div[2]/button[3]").click();

        // surandamas paieskos laukas kurio yra id = sb_form_q
        WebElement searchfield = browser.findElement(By.id("sb_form_q"));
        // Paieskos lauke ivedame zodi.
        searchfield.sendKeys("Baranauskas");
        // Paspaudziamas ENTER mygtukas.
        searchfield.sendKeys(Keys.ENTER);

        WebElement result = browser.findElement(By.xpath("//*[@id=\"b_tween_searchResults\"]"));
        System.out.println(result.toString());



    }

}





// 1. Sukurtas Maven projektas
// 2. Bibliotekos Maven atsisiustos (https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java)
// 3. Driveriai parsisiusti (chrome driveriai turi atitikti chrome versija!!!) ((https://chromedriver.chromium.org/downloads))
// 4. Rasyti koda

