import org.testng.annotations.BeforeMethod;
import org.example.Atsiskaitymas;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AtsiskaitymasTest {
    @BeforeMethod
    public void setup(){
        Atsiskaitymas.setup();
    }

    @Test (priority = 1)
    public void PositiveTest(){
        Atsiskaitymas.picosName("Margarita");
        Atsiskaitymas.Wait();
        Atsiskaitymas.ingridientaiInput("Picos padas, suris, pomidoru padazas");
        Atsiskaitymas.Wait();
        Atsiskaitymas.padazoInput("Cesnakinis");
        Atsiskaitymas.Wait();
        Atsiskaitymas.papildaiInput("Daugiau surio");
        Atsiskaitymas.Wait();
        Atsiskaitymas.kainosInput("12");
        Atsiskaitymas.Wait();
        Atsiskaitymas.Click();
        Atsiskaitymas.Output();
        Atsiskaitymas.closeBrowser();
    }

    @Test (priority = 2)
    public void NegativeTest(){
        Atsiskaitymas.picosName("Margarita");
        Atsiskaitymas.Wait();
        Atsiskaitymas.ingridientaiInput("Picos padas, suris, pomidoru padazas");
        Atsiskaitymas.Wait();
        Atsiskaitymas.padazoInput("Cesnakinis");
        Atsiskaitymas.Wait();
        Atsiskaitymas.papildaiInput("Daugiau surio");
        Atsiskaitymas.Wait();
        //Atsiskaitymas.kainosInput("12");
        //Atsiskaitymas.Wait();
        Atsiskaitymas.Click();
        Atsiskaitymas.Output();

        Atsiskaitymas.closeBrowser();
    }

    @Test (priority = 3)
    public void positiveIdTest(){
        Atsiskaitymas.idinput("1");
        Atsiskaitymas.Wait();
        Atsiskaitymas.ClickIstrinti();
        Atsiskaitymas.IdOutput();
        Atsiskaitymas.closeBrowser();

    }

    @Test (priority = 4)
    public void positiveEditTest(){
        Atsiskaitymas.idinput("1");
        Atsiskaitymas.Wait();
        Atsiskaitymas.picosName("Margarita");
        Atsiskaitymas.Wait();
        Atsiskaitymas.ingridientaiInput("Picos padas, suris, pomidoru padazas");
        Atsiskaitymas.Wait();
        Atsiskaitymas.padazoInput("Cesnakinis");
        Atsiskaitymas.Wait();
        Atsiskaitymas.papildaiInput("Daugiau surio");
        Atsiskaitymas.Wait();
        Atsiskaitymas.kainosInput("12");
        Atsiskaitymas.Wait();
        Atsiskaitymas.Clickredaguoti();
        Atsiskaitymas.Output();
        Atsiskaitymas.closeBrowser();
    }

    @Test (priority = 5)
    public void negativeEditTest(){
        Atsiskaitymas.idinput("1");
        Atsiskaitymas.Wait();
        Atsiskaitymas.picosName("Margarita");
        Atsiskaitymas.Wait();
        Atsiskaitymas.ingridientaiInput("Picos padas, suris, pomidoru padazas");
        Atsiskaitymas.Wait();
        Atsiskaitymas.padazoInput("Cesnakinis");
        Atsiskaitymas.Wait();
        Atsiskaitymas.papildaiInput("Daugiau surio");
        Atsiskaitymas.Wait();
        //Atsiskaitymas.kainosInput("12");
        //Atsiskaitymas.Wait();
        Atsiskaitymas.Clickredaguoti();
        Atsiskaitymas.Output();
        Atsiskaitymas.closeBrowser();
    }

}
