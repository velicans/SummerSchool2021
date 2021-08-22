package sesiunea14;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Slf4j
public class WineApiTest {
    public static final String WINE_URL = "https://endavawineapp.azurewebsites.net/wines";
    Response response;

    @Test
    void testWine() {

        String bottlingVolume = "liters";
        String composition = "merlot";
        String name = "mywine" + Instant.now();
        String type = "dry";
        int volume = 56;
        addWine(bottlingVolume, composition, name, type, volume);
        System.out.println("wine add complete");

        getWine();
        isWineAvailable(name);
        assertThat(isWineAvailable(name), is(true));

        String wineId = getWineId(name);
        System.out.println("our wine id is: " + wineId);

        deleteWine(wineId);
        assertThat(isWineAvailable(name), is(false));

        patchWine(wineId);

    }

    private void patchWine(String wineId) {

    }

    private void deleteWine(String wineId) {
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .delete(WINE_URL + wineId);
    }

    private String getWineId(String name) {
        String wines = response.prettyPrint();
        return JsonPath.with(wines).get("find { a -> a.name == '" + name + "' }.id").toString();
    }

    private boolean isWineAvailable(String name) {
        String wine = response.prettyPrint();
        return JsonPath.with(wine).get("name").toString().contains(name);
    }

    private void getWine() {
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get(WINE_URL);

        log.info(response.prettyPrint());
    }

    private void addWine(String bottlingVolume, String composition, String name, String type, int volume) {
        Map<String, Object> volumeMap = new HashMap<>();
        volumeMap.put("value", volume);
        volumeMap.put("unit", "liters");

        Map<String, Object> bodyMapWine = new HashMap<>();
        bodyMapWine.put("name", name);
        bodyMapWine.put("type", type);
        bodyMapWine.put("volume", volumeMap);
        bodyMapWine.put("bottlingVolume", bottlingVolume);
        bodyMapWine.put("composition", composition);

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(bottlingVolume)
                .when()
                .post(WINE_URL);

        log.info(response.prettyPrint());
    }


}
