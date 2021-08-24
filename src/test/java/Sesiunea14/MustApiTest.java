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
public class MustApiTest {

    public static final String MUST_URL = "https://endavawineapp.azurewebsites.net/must/";
    Response response;

    @Test
    void testMust() {

        String name = "DanielMust" + Instant.now();
        int volume = 300;
        String unit = "liters";
        String type = "dry";
        addMust(name, volume, unit, type);

        System.out.println("Must add complete.");

        getMust();
        assertThat(isMustAvailable(name), is(true));

        String mustId = getMustId(name);
        System.out.println("Our must id is: " + mustId);

        deleteMust(mustId);
        getMust();
        assertThat(isMustAvailable(name), is(false));

    }

    private boolean isMustAvailable(String name) {

        String must = response.prettyPrint();
        return JsonPath.with(must).get("name").toString().contains(name);

    }

    private void deleteMust(String mustId) {

        String mustBody;
        mustBody = "[" + mustId + "]";

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(mustBody)
                .when()
                .delete(MUST_URL);

    }

    private String getMustId(String name) {

        String must = response.prettyPrint();
        return JsonPath.with(must).get("find { a -> a.name == '" + name + "' }.id").toString();

    }

    public void getMust() {

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get(MUST_URL);

        log.info(response.prettyPrint());

    }

    public void addMust(String name, int volume, String unit, String type) {

        Map<String, Object> volumeMap = new HashMap<>();
        volumeMap.put("value", volume);
        volumeMap.put("unit", unit);

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
