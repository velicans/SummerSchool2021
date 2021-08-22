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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


@Slf4j
public class GarpeApiTest {

    public static final String GRAPE_URL = "https://endavawineapp.azurewebsites.net/grapes/";
    Response response;

    @Test
    void testGrapes() {

        String name = "Grape" + Instant.now();
        int age = 2;
        int quantity = 30;
        int ripeness = 97;
        addGrape(name, age, quantity, ripeness);
        log.info("grape add complete.");
        System.out.println("grape add complete.");

        getGrapes();
        assertThat(isGrapeAvailable(name), is(true));

        String grapeId = getGrapeId(name);
        System.out.println("our grape id is: " + grapeId);

        deleteGrape(grapeId);
        getGrapes();
        assertThat(isGrapeAvailable(name), is(false));

    }

    private boolean isGrapeAvailable(String name) {
        String grapes = response.prettyPrint();
        return JsonPath.with(grapes).get("name").toString().contains(name);
    }

    private void deleteGrape(String grapeId) {

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .delete(GRAPE_URL + grapeId);
    }

    private String getGrapeId(String name) {

        String grapes = response.prettyPrint();
        return JsonPath.with(grapes).get("find { a -> a.name == '" + name + "' }.id").toString();
    }

    private void getGrapes() {

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get(GRAPE_URL);

        log.info(response.prettyPrint());
    }

    private void addGrape(String name, int age, int quantity, int ripeness) {

        Map<String, Object> quantityMap = new HashMap<>();
        quantityMap.put("value", quantity);
        quantityMap.put("unit", "rows");

        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("name", name);
        bodyMap.put("quantity", quantityMap);
        bodyMap.put("age", age);
        bodyMap.put("ripeness", ripeness);

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(bodyMap)
                .when()
                .post(GRAPE_URL);

        log.info(response.prettyPrint());

    }
}