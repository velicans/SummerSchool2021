package Sesiunea14;

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
public class WinesApiTest {

    public static final String WINE_URL = "https://endavawineapp.azurewebsites.net/wines/";
    Response response;

    @Test
    void testWines() {

        String name = "WineMonica" + Instant.now();
        String bottlingVolume = "0.75l";
        String composition="grapes";
        String type = "merlot";
        String unit = "rows";
        int value = 2;
        addWine(name, bottlingVolume,composition,type, unit, value);
        log.info("wine add complete.");
        System.out.println("wine add complete.");

        getWines();
        assertThat(isWineAvailable(name), is(true));

        String wineId = getWineId(name);
        System.out.println("our wine id is: " + wineId);

        deleteWine(wineId);
        getWines();
        assertThat(isWineAvailable(name), is(false));

    }

    private boolean isWineAvailable(String name) {
        String wine = response.prettyPrint();
        return JsonPath.with(wine).get("name").toString().contains(name);
    }

    private void deleteWine(String wineId) {

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .delete(WINE_URL + wineId);
    }

    private String getWineId(String name) {

        String wine = response.prettyPrint();
        return JsonPath.with(wine).get("find { a -> a.name == '" + name + "' }.id").toString();
    }

    private void getWines() {

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get(WINE_URL);

        log.info(response.prettyPrint());
    }

    private void addWine(String name, String bottlingVolume, String composition, String type, String unit, int value) {

        Map<String, Object> volumeMap = new HashMap<>();
        volumeMap.put("value", value);
        volumeMap.put("unit", unit);

        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("bottlingVolume", bottlingVolume);
        bodyMap.put("composition", new String[]{composition});
        bodyMap.put("name", name);
        bodyMap.put("volume", volumeMap);
        bodyMap.put("type", type);


        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(bodyMap)
                .when()
                .post(WINE_URL);

        log.info(response.prettyPrint());

    }
}
