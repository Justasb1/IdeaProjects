import org.example.Pigu;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.example.Pigu.SEARCH_KEYWORD;
import static org.example.Pigu.browser;

public class PiguTest {
    @BeforeClass
    public static void setUp(){
        Pigu.setup();
        Pigu.closeCookies();
    }

    @Test
    public void inputEnabledDisplayed(){
        boolean resultActual = Pigu.isEnabledAndDisplayed(By.className("sn-suggest-input"));
        Assert.assertTrue(resultActual);
    }

    @Test
    public void inputEnabledDisplayedNegative(){
        boolean resultActual = Pigu.isEnabledAndDisplayed(By.className("sn-suggest-input"));
        Assert.assertNotEquals(false, resultActual);
    }

    @Test
    public void findKeywordInURL(){
        Pigu.searchByKeyword(Pigu.SEARCH_KEYWORD);
        String resultActual = Pigu.findKeywordInUrl(Pigu.SEARCH_KEYWORD);
        Assert.assertEquals(Pigu.SEARCH_KEYWORD + " paieskos kriterijus atvaizduojamas adrese.", resultActual);
    }

    @Test
    public void findKeywordInTitle(){
        Pigu.waitForElementVisibility(By.cssSelector("div[id^='_0productBlock']"));
        Pigu.hoverElement(Pigu.browser.findElement(By.cssSelector("div[id^='_0productBlock']")));
        Pigu.waitForElementVisibility(By.cssSelector(".product-name a"));
        Pigu.clickOnElement(By.cssSelector(".product-name a"));
        String resultActual = Pigu.findKeywordInTitle(Pigu.SEARCH_KEYWORD);
        Assert.assertEquals("Puslapio pavadinime yra žodis '" + Pigu.SEARCH_KEYWORD + "'.", resultActual);
    }

    @Test
    public void isModalDisplayed(){
        Pigu.checkHeader(Pigu.browser.findElement(By.tagName("h1")), Pigu.SEARCH_KEYWORD);
        Pigu.waitForElementClickability(By.cssSelector(".c-product__controls .c-btn--primary"));
        Pigu.clickOnElement((By.cssSelector(".c-product__controls .c-btn--primary")));
        Pigu.waitForElementVisibility(By.id("modal"));
        boolean resultActual = Pigu.isModalDisplayed(By.id("modal"), By.className("add-to-cart-modal-title") , ("prekė įtraukta"));
        Assert.assertTrue(resultActual);
        Pigu.clickOnElement(By.id("close"));
    }

    @AfterMethod
    public static void close(){
        Pigu.closeBrowser();
    }
}