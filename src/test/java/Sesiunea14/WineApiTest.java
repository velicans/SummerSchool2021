package Sesiunea14;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
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

    @Test
    void testWine() {

        String name = "DanielWine" + Instant.now();
        String bottlingVolume = "0.7";
        int volume = 300;
        String unit = "liters";
        String composition = "sauvignon";
        String type = "dry";
        addWine(name, bottlingVolume, volume, unit, composition, type);

        System.out.println("Wine add complete.");

        getWine();
        assertThat(isWineAvailable(name), is(true));

        String wineId = getWineId(name);
        System.out.println("Our wine id is: " + wineId);

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
                .when()
                .delete(WINE_URL + wineId);

    }

    private String getWineId(String name) {

        String wine = response.prettyPrint();
        return JsonPath.with(wine).get("find { a -> a.name == '" + name + "' }.id").toString();

    }

    public void getWine() {

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get(WINE_URL);

        log.info(response.prettyPrint());

    }

    public void addWine(String name, String bottlingVolume, int volume, String unit, String composition, String type) {

        Map<String, Object> volumeMap = new HashMap<>();
        volumeMap.put("value", volume);
        volumeMap.put("unit", unit);

        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("name", name);
        bodyMap.put("bottlingVolume", bottlingVolume);
        bodyMap.put("volume", volumeMap);
        bodyMap.put("composition", composition);
        bodyMap.put("type", type);

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(bodyMap)
                .when()
                .post(WINE_URL);

        log.info(response.prettyPrint());

    }

}
