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
public class MustApiTest {

    public static final String MUST_URL = "https://endavawineapp.azurewebsites.net/must/";
    Response response;

    @Test
    void testMust() {

        String name = "Must" + Instant.now();
        int age = 2;
        int quantity = 30;
        String type = "sweet";
        addMust(name, age, quantity, type);
        log.info("must add complete.");
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
                .body("[" + mustId + "]")
                .when()
                .delete(MUST_URL);
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

    private void addMust(String name, int age, int quantity, String type) {

        Map<String, Object> quantityMap = new HashMap<>();
        quantityMap.put("value", quantity);
        quantityMap.put("unit", "liters");

        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("name", name);
        bodyMap.put("quantity", quantityMap);
        bodyMap.put("age", age);
        bodyMap.put("type", type);

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(bodyMap)
                .when()
                .post(MUST_URL);

        log.info(response.prettyPrint());

    }
}

