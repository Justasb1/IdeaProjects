import org.example.Atsiskaitymas;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.example.Atsiskaitymas.Output;
import static org.example.Atsiskaitymas.browser;

public class AtsiskaitymasTest {

    @BeforeMethod
    public void setup() {

        Atsiskaitymas.setup();
    }

    @Test (priority = 1)
    public void PositiveTest(){
        Atsiskaitymas.filmoInput("Wonka");
        Atsiskaitymas.Wait();
        Atsiskaitymas.zanroInput("Adventure, Comedy, Family, Fantasy, Musical");
        Atsiskaitymas.Wait();
        Atsiskaitymas.aktoriuInput("Timothee Chalamet, Olivia Colman, Charlotte Ritchie");
        Atsiskaitymas.Wait();
        Atsiskaitymas.rezisieriausInput("Paul King");
        Atsiskaitymas.Wait();
        Atsiskaitymas.trukmesInput("116");
        Atsiskaitymas.Wait();
        Atsiskaitymas.Click();
        Atsiskaitymas.Output();
        //boolean resultActual =! Atsiskaitymas.isEnabledDisplayed(browser.findElement(By.className("msg-good")));
        //Assert.assertTrue(resultActual);
        Atsiskaitymas.closeBrowser();
    }

    //@Test (priority = 2)
    //public void isEnabledDisplayed() {
    //    boolean fieldStatus = (boolean) Atsiskaitymas.isEnabledDisplayed(browser.findElement(By.className("msg-good")));
    //    Assert.assertTrue(fieldStatus);
    //}

    @Test (priority = 2)
    public void NegativeTest(){
        Atsiskaitymas.filmoInput("Wonka");
        Atsiskaitymas.Wait();
        Atsiskaitymas.zanroInput("Adventure, Comedy, Family, Fantasy, Musical");
        Atsiskaitymas.Wait();
        Atsiskaitymas.aktoriuInput("Timothee Chalamet, Olivia Colman, Charlotte Ritchie");
        Atsiskaitymas.Wait();
        Atsiskaitymas.rezisieriausInput("Paul King");
        Atsiskaitymas.Wait();
        //Atsiskaitymas.trukmesInput("116");
        //Atsiskaitymas.Wait();
        Atsiskaitymas.Click();
        Atsiskaitymas.Output();
        //boolean resultActual =! Atsiskaitymas.isEnabledDisplayed(browser.findElement(By.className("msg-bad")));
        //Assert.assertFalse(resultActual);
    }

    //@Test (priority = 4)
    //public void isEnabledDisplayedNegative() {
    //    boolean fieldStatus = (boolean) Atsiskaitymas.isEnabledDisplayed(browser.findElement(By.className("msg-bad")));
    //    Assert.assertTrue(fieldStatus);
    //}

}
