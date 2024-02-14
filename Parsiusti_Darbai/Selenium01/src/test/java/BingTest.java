import dev.failsafe.internal.util.Assert;
import org.example.Bing;
import org.junit.jupiter.api.*;


public class BingTest {
    @BeforeEach
    public void setup() {

        Bing.setup();
    }
    @Disabled
    @Test
    public void searchByKeyword() {
        Bing.searchByKeyword("Baranauskas");
    }

    @Test
    @Order(2)
    public void compareResultsPositiveTest(){
        Bing.searchByKeyword("Baranauskas");
        int resultInt = Bing.getResultInt();
        String resultActual = Bing.compareResult(resultInt);
        Assertions.assertEquals("Jaunimas dar neužmiršo Anykščių šilelio.", resultActual);
    }
    @Test
    @Order(1)
    public void compareResultsNegativeTest(){
        Bing.searchByKeyword("Baranauskas");
        int resultInt = Bing.getResultInt();
        String resultActual = Bing.compareResult(resultInt);
        Assertions.assertNotEquals("Rašytojas nelabai populiarus internetinėse platybėse.", resultActual);
    }

    @AfterEach
    public void close() {
        Bing.close();
    }

}
