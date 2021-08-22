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

public class Tema14 {

    public static class MustApiTest {

        public static final String MUST_URL = "https://endavawineapp.azurewebsites.net/must/";
        Response response;

        @Test
        void testMust() {

            String name = "Must" + Instant.now();
            String type = "sweet";
            int volume = 30;
            addMust(name, type, volume);
           // log.info("grape add complete.");
            System.out.println("must add complete.");

            getMust();
            assertThat(isMustAvailable(name), is(true));

            String mustId = getMustId(name);
            System.out.println("our must id is: " + mustId);

            deleteMust(mustId);
            getMust();
            assertThat(isMustAvailable(name), is(false));

        }

        private boolean isMustAvailable(String name) {
            String must = response.prettyPrint();
            return JsonPath.with(must).get("name").toString().contains(name);
        }

        private void deleteMust(String mustId) {

            response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .when()
                    .delete(MUST_URL + mustId);
        }

        private String getMustId(String name) {

            String must = response.prettyPrint();
            return JsonPath.with(must).get("find { a -> a.name == '" + name + "' }.id").toString();
        }

        private void getMust() {

            response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .when()
                    .get(MUST_URL);

            log.info(response.prettyPrint());
        }

        private void addMust(String name, String type, int volume) {

            Map<String, Object> volumeMap = new HashMap<>();
            volumeMap.put("value", volume);
            volumeMap.put("unit", "liters");

            Map<String, Object> bodyMap = new HashMap<>();
            bodyMap.put("name", name);
            bodyMap.put("volume", volumeMap);
            bodyMap.put("type", type);

            response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(bodyMap)
                    .when()
                    .post(MUST_URL);

            log.info(response.prettyPrint());

        }
    }

    public class WinesApiTest {

        public static final String WINES_URL = "https://endavawineapp.azurewebsites.net/wines/";
        Response response;

        @Test
        void testWines() {

            String bottlingVolume = "1";
            String[] composition =new String[1];
            String name = "Wine" + Instant.now();
            String type = "sweet";
            int wineVolume = 30;

            addWine(bottlingVolume,composition,name,type, wineVolume);
            // log.info("grape add complete.");
            System.out.println("wine add complete.");

            getWine();
            assertThat(isWineAvailable(name), is(true));

            String WineId = getWineId(name);
            System.out.println("our wine id is: " + WineId);

            deleteWine(WineId);
            getWine();
            assertThat(isWineAvailable(name), is(false));

        }

        }

        private boolean isWineAvailable(String name) {
            String wine = response.prettyPrint();
            return JsonPath.with(wine).get("name").toString().contains(name);
        }

        private void deleteWine(String WineId) {

            response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .when()
                    .delete(WINES_URL + WineId);

        }

        private String getWineId(String name) {

            String wine = response.prettyPrint();
            return JsonPath.with(wine).get("find { a -> a.name == '" + name + "' }.id").toString();
        }

        private void getWine() {

            response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .when()
                    .get(WINES_URL);

            log.info(response.prettyPrint());
        }

        private void addWine(String bottlingVolume, String[] composition,String name, String type, int wineVolume) {

            Map<String, Object> wineVolumeMap = new HashMap<>();
            wineVolumeMap.put("value", wineVolume);
            wineVolumeMap.put("unit", "liters");

            Map<String, Object> bodyMap = new HashMap<>();
            bodyMap.put("name", name);
            bodyMap.put("volume", wineVolumeMap);
            bodyMap.put("composition", composition);
            bodyMap.put("type", type);
            bodyMap.put("bottlingVolume", bottlingVolume);

            response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(bodyMap)
                    .when()
                    .post(WINES_URL);

            log.info(response.prettyPrint());

        }

           }

