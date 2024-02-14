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
    public void InformacijosGavimoLaikasPozytyvus(){
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
    @Test
    public void ArTeisingosKordinatesPozytyvus(){
        RestAssured.baseURI = "https://api.meteo.lt/v1/";
        Response response = given()
                .when().get("places/kaunas")
                .then().assertThat().log().all()
                .body("coordinates.latitude", equalTo(54.898212F))
                .body("coordinates.longitude", equalTo(23.904482F))
                .extract().response();


    }
    @Test
    public void ArKorektiskasJsonFailasPozytyvus(){

        RestAssured.baseURI = "https://api.meteo.lt/v1/";
        Response response = given()
                .when().get("places/kaunas")
                .then().assertThat().log().all()
                .body("code", equalTo("kaunas"))
                .body("name", equalTo("Kaunas"))
                .body("administrativeDivision", equalTo("Kauno miesto savivaldybė"))
                .body("countryCode", equalTo("LT"))
                .extract().response();
    }

@Test
    public void InformacijosGavimoLaikasNegatyvus(){
        RestAssured.baseURI = "https://api.meteo.lt/v1/";
        Response response = given()
            .when().get("places/kaunas")
            .then()
            .assertThat()
            .statusCode(HttpsURLConnection.HTTP_OK) // 200
            .extract().response();


    Assertions.assertNotEquals(response.statusCode(), HttpsURLConnection.HTTP_CLIENT_TIMEOUT, "Statuso kodas skiriasi nuo to, kurio tikejomes");;
    }

    @Test
    public void ArKorektiskasJsonFailasNegatyvus{
        R
        Response response = given()

                .when().get("https://api.meteo.lt/v1/")
                .then().assertThat()
                .extract().response();

        float latitude = response.jsonPath().get("coordinates.latitude");
        float longtitude = response.jsonPath().get("coordinates.longitude");

    }


}
