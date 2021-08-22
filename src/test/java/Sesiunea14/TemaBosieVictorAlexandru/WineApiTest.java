package Sesiunea14.TemaBosieVictorAlexandru;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@Slf4j
public class WineApiTest {

    public static final String WINE_URL = "https://endavawineapp.azurewebsites.net/wines/";
    Response response;

    //someone deleted all wines and they can't be added anymore; got to test everything but the patchWine method.

    @Test
    void testWine() {

        String name = "Wine" + Instant.now();
        int quantity = 30;
        String type = "sweet";
        addWine(name, quantity, type);
        log.info("wine add complete.");
        System.out.println("wine add complete.");

        getWine();
        assertThat(isWineAvailable(name), is(true));

        String wineId = getWineId(name);
        System.out.println("our grape id is: " + wineId);

        //patchWine(wineId);

        deleteWine(wineId);
        getWine();
        assertThat(isWineAvailable(name), is(false));

    }

    private boolean isWineAvailable(String name) {
        String wine = response.prettyPrint();
        return JsonPath.with(wine).get("name").toString().contains(name);
    }

    private void deleteWine(String wineId) {

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(wineId)
                .when()
                .delete(WINE_URL);
    }

    private void patchWine(String wineId) {

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{}")
                .when()
                .delete(WINE_URL + wineId);
    }

    private String getWineId(String name) {

        String wine = response.prettyPrint();
        return JsonPath.with(wine).get("find { a -> a.name == '" + name + "' }.id").toString();
    }

    private void getWine() {

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get(WINE_URL);

        log.info(response.prettyPrint());
    }

    private void addWine(String name, int quantity, String type) {

        Map<String, Object> quantityMap = new HashMap<>();
        quantityMap.put("value", quantity);
        quantityMap.put("unit", "liters");

        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("name", name);
        bodyMap.put("volume", quantityMap);
        bodyMap.put("type", type);

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(bodyMap)
                .when()
                .post(WINE_URL);

        log.info(response.prettyPrint());

    }
}
