import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.HTTP;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.net.ssl.HttpsURLConnection;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class MeteoTest {
@Test
    public void GautiInfoApieKaunaPozityvus(){
    RestAssured.baseURI = "https://api.meteo.lt/v1/";

    Response response = given()
            .when().get("places/kaunas")
            .then()
            .assertThat()
            .statusCode(HttpsURLConnection.HTTP_OK) // 200
            .extract().response();


    Assertions.assertEquals(HttpsURLConnection.HTTP_OK, response.statusCode(), "Statuso kodas skiriasi nuo to, kurio tikėjomės.");

    }

@Test
    public void InformacijosGavimoLaikasPozityvus(){

    RestAssured.baseURI = "https://api.meteo.lt/v1/";
    Response response = given()
            .when().get("places/kaunas/")
            .then().assertThat()
            .time(lessThan(500L))
            .extract().response();

    Assertions.assertTrue(response.getTime()<500, "Laikas ilgesnis nei tikėtasi");

    }
    @Test
    public void ArTeisingosKoordinatesPozityvus(){
        RestAssured.baseURI = "https://api.meteo.lt/v1/";
        Response response = given()
                .when().get("places/kaunas/")
                .then().assertThat().log().all()
                .body("coordinates.latitude", equalTo(54.898214F))
                .body("coordinates.longitude", equalTo(23.904482F))
                .extract().response();

    }
    @Test
    public void ArKorektiskasJsonFailasPozityvus(){

        RestAssured.baseURI = "https://api.meteo.lt/v1/";
        Response response = given()
                .when().get("places/kaunas/")
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

    Assertions.assertNotEquals(response.statusCode(), HttpsURLConnection.HTTP_CLIENT_TIMEOUT, "Statuso kodas skiriasi nuo to, kurio tikėjomės.");

    }

    @Test
    public void ArKorektiskasJsonFailasNegatyvus(){

        RestAssured.baseURI = "https://api.meteo.lt/v1/";
        Response response = given()
                .when().get("places/kaunas/")
                .then().assertThat()
                .extract().response();

        float latitude = response.jsonPath().get("coordinates.latitude");
        float longitude = response.jsonPath().get("coordinates.longitude");

        Assertions.assertNotEquals(51.898214F, latitude, "Neteisinga latitude koordinatė");
        Assertions.assertNotEquals(22.904482F, longitude, "Neteisinga longitude koordinatė");

    }
    @Test
    public void GavimoLaikasNegatyvus(){

        RestAssured.baseURI = "https://api.meteo.lt/v1/";
        Response response = given()
                .when().get("places/kaunas/")
                .then().assertThat()
                .time(lessThan(900L))
                .extract().response();

        Assertions.assertFalse(response.getTime() > 900, "Laikas ilgesnis nei tikėtasi" + response.getTime());


    }


}
