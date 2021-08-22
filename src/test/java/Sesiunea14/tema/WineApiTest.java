package Sesiunea14.tema;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
public class WineApiTest {

    public static final String WINES_URL = "https://endavawineapp.azurewebsites.net/wines/";

    private Response response;

    @Test
    void testWIne() {
        final String name = "wine" + Instant.now();
        final int wineId = 1;
        addWine(name, "wine", new Volume("bottle", 10), "12", Arrays.asList("grapes"), wineId);

        final String newName = "new_wine" + Instant.now();
        patchWine(newName, wineId);
        getWine();
        Assertions.assertTrue(isWineAvailable(name));
        deleteWine(wineId);
        Assertions.assertFalse(isWineAvailable(name));

    }

    private boolean isWineAvailable(String name) {
        return JsonPath.with(response.body().asString()).get("name").toString().contains(name);
    }

    private void patchWine(String name, int id) {
        System.out.println("Setting new name for wine: " + name);
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(Map.of("name", name))
                .when()
                .patch(WINES_URL + id);

        log.info(response.prettyPrint());
    }

    private void addWine(String name, String type, Volume volume, String bottlingVolume, List<String> composition, int id) {
        System.out.println("Adding wine: " + name);
        final Map<String, Object> body = Map.of(
                "id", id,
                "name", name,
                "type", type,
                "volume", volume,
                "composition", composition,
                "bottlingVolume", bottlingVolume
        );

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(WINES_URL);

        log.info(response.prettyPrint());
    }

    private void deleteWine(int wineId) {
        System.out.println("Deleting wine with id: " + wineId);
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .delete(WINES_URL + wineId);

        log.info(response.prettyPrint());
    }

    private void getWine() {
        System.out.println("Get:");
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get(WINES_URL);

        log.info(response.prettyPrint());
    }

}
