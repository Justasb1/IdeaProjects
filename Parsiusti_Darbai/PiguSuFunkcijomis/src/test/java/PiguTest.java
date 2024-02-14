import org.example.Pigu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PiguTest {
   @BeforeAll
   public static void setUP(){
       Pigu.setup();
       Pigu.closeCookies();
   }

   @Test
    public void isEnabledDisplayed(){
       boolean resultActual = Pigu.isEnabledDisplayed((WebElement) By.className("sn-suggest-input"));
       Assertions.assertTrue(resultActual);
   }

   @Test
    public void isEnabledDisplayedNegative(){
       boolean resultActual = Pigu.isEnabledDisplayed((WebElement) By.className("sn-suggest-input"));
       Assertions.assertNotEquals(false, resultActual);
   }

   @Test
   public void findKeywordUrl() {
       Pigu.searchByKeyword(Pigu.SEARCH_KEYWORD);
       String resultActual = Pigu.findKeywordUrl(Pigu.SEARCH_KEYWORD);
       Assertions.assertEquals(Pigu.SEARCH_KEYWORD + "paieskos kriterijus atvaizduojamas adrese.", resultActual);
   }

   @Test
    public void findKeywordInTitle(){
       Pigu.waitElement(By.cssSelector("div[id^='_0productBlock']"));
       Pigu.hoverElement(Pigu.browser.findElement(By.cssSelector("div[id^='_0productBlock']")));
       Pigu.waitElement(By.cssSelector(".product-name a"));
       Pigu.clickOnElement(By.cssSelector(".product-name a"));
       String resultActual = Pigu.checkTitle(Pigu.SEARCH_KEYWORD);
   }

   @Test
    public void isModalDisplayed(){
       Pigu.checkItemName(Pigu.browser.findElement(By.tagName("h1")), Pigu.SEARCH_KEYWORD);
       Pigu.waitElement(By.cssSelector(".c-product__controls .c-btn--primary"));
       Pigu.clickOnElement(By.cssSelector(".c-product__controls .c-btn--primary"));;
       Pigu.waitElement(By.id("modal"));
       boolean resultActual = isModalDisplayed(By.id("modal"), By.className("add-to-cart-modal-title"));
       Assertions.assertTrue(resultActual);
       Pigu.clickOnElement(By.id("close"));
   }

    private boolean isModalDisplayed(By modal, By by) {
        return false;
    }

   @Test
   public void isModalEnabledAndDisplayed(){
   }

}
