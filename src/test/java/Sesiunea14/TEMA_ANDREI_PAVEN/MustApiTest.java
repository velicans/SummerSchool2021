package Sesiunea14.TEMA_ANDREI_PAVEN;

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

    // storing the needed URL in a final variable
    public static final String MUST_URL = "https://endavawineapp.azurewebsites.net/must/";
    Response response;

    @Test
    void testMust() {

        String name = "Must_Southern" + Instant.now();
        String type = "Dry";

        int value = 500;

        addMust(name, type, value);
        log.info("must add complete.");
        System.out.println("must add complete.");

        getMust();
        assertThat(isMustAvailable(name), is(true));

        String mustId = getMustId(name);
        System.out.println("our must id is: " + mustId);

        deleteMust(Integer.parseInt(mustId));
        getMust();
        assertThat(isMustAvailable(name), is(false));

    }

    private boolean isMustAvailable(String name) {
        String must = response.prettyPrint();
        return JsonPath.with(must).get("name").toString().contains(name);
    }

    private void deleteMust(int mustId) {

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(new int[]{mustId})
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

    private void addMust(String name, String type, int value) {

        Map<String, Object> volumeMap = new HashMap<>();
        volumeMap.put("value", value);
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