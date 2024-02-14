import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import javax.net.ssl.HttpsURLConnection;
import java.net.HttpURLConnection;

import static io.restassured.path.json.JsonPath.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.IsEqual.equalTo;

public class MateoTest {
    @BeforeEach

    public void setUp() {
        RestAssured.baseURI = "https://api.meteo.lt/v1/";

    }
    @Test
    public void GautiInfoApieKaunoOrus() {
        RestAssured.baseURI = "https://api.meteo.lt/v1/";

        Response response = RestAssured
                .when().get("places/kaunas")
                .then()
                .assertThat()
                .statusCode(HttpsURLConnection.HTTP_OK) // 200
                .body("code", equalTo("kaunas"))
                .body("name", equalTo("Kaunas"))
                .body("administrativeDivision", equalTo("Kauno miesto savivaldybė"))
                .body("countryCode", equalTo("LT"))
                .extract().response();


        Assertions.assertEquals(HttpsURLConnection.HTTP_OK, response.statusCode(), 200);

    }
@Test
    public void InformacijosGavimoLaikas(){
    Response response = RestAssured
            .when().get("https://api.meteo.lt/v1/places/kaunas/")
            .then().assertThat().log().all()
            .time(lessThan(500L))
            .extract().response();

    Assertions.assertTrue(response.getTime()<500,"Response took longer then expected");
    }

    private RestAssured given() {
            return null;
    }

}
