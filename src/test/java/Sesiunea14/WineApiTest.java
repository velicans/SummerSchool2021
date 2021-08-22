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
public class WineApiTest {

    public static final String Wine_URL = "https://endavawineapp.azurewebsites.net/Wines/";
    Response response;

    @Test
    void testWines() {
        /*[
  {
    "bottlingVolume": "string",
    "composition": [
      "string"
    ],
    "id": 0,
    "name": "string",
    "type": "string",
    "volume": {
      "unit": "string",
      "value": 0
    }
  }
    ]*/
        String bottlingVolume = "77";
        String composition = "88";
        String name = "Wine" + Instant.now();
        String type = "RosuDeTuta";
        int volume = 77;

        addWine(bottlingVolume, composition, name, type, volume);

        log.info("Wine add complete.");
        System.out.println("Wine add complete.");

        getWines();
        assertThat(isWineAvailable(name), is(true));

        String WineId = getWineId(name);
        System.out.println("our Wine id is: " + WineId);

        deleteWine(WineId);
        getWines();
        assertThat(isWineAvailable(name), is(false));

    }

    private boolean isWineAvailable(String name) {
        String Wines = response.prettyPrint();
        return JsonPath.with(Wines).get("name").toString().contains(name);
    }

    private void deleteWine(String WineId) {

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .delete(Wine_URL + WineId);
    }

    private String getWineId(String name) {

        String Wines = response.prettyPrint();
        return JsonPath.with(Wines).get("find { a -> a.name == '" + name + "' }.id").toString();
    }

    private void getWines() {

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get(Wine_URL);

        log.info(response.prettyPrint());
    }

    private void addWine(String bottlingVolume, String composition, String name, String type, int volume) {
        /*[
  {
    "bottlingVolume": "string",
    "composition": [
      "string"
    ],
    "id": 0,
    "name": "string",
    "type": "string",
    "volume": {
      "unit": "string",
      "value": 0
    }
  }
    ]*/
        Map<String, Object> volumeMap = new HashMap<>();
        volumeMap.put("value", volume);
        volumeMap.put("unit", "rows");

        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("bottlingVolume", bottlingVolume);
        bodyMap.put("composition", composition);
        bodyMap.put("name", name);
        bodyMap.put("type", type);
        bodyMap.put("volume", volumeMap);


        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(bodyMap)
                .when()
                .post(Wine_URL);

        log.info(response.prettyPrint());

    }
}
