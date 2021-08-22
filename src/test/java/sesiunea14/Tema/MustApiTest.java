package sesiunea14.Tema;


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

    private static final String MUST_URL = "https://endavawineapp.azurewebsites.net/must/";
    Response response;



    @Test
    void testMust() {

        String name = "MustPI" + Instant.now();
        String type = "dry";
        int volume = 200;

        addMust(name, type, volume);
        log.info("Must add complete.");
        System.out.println("Must add complete.");

        getMust();
        assertThat(isMustAvailable(name), is(true));

        String mustId = getMustId(name);
        System.out.println("Our must id is: " + mustId);

        deleteMust(mustId);
        getMust();
        assertThat(isMustAvailable(name), is(false));
    }

    private void addMust(String name, String type, int volume) {

        Map<String, Object> volumeMap = new HashMap<>();
        volumeMap.put("value", volume);
        volumeMap.put("unit", "liters");

        Map<String, Object> mustBody = new HashMap<>();
        mustBody.put("name", name);
        mustBody.put("volume", volumeMap);
        mustBody.put("type", type);

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(mustBody)
                .when()
                .post(MUST_URL);
    }

    private boolean isMustAvailable(String name) {
        return JsonPath.with(response.prettyPrint())
                .get("name")
                .toString()
                .contains(name);
    }


    private void getMust() {
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get(MUST_URL);

        log.info(response.prettyPrint());
    }


    private String getMustId(String name) {
        String must = response.prettyPrint();
        return JsonPath.with(must)
                .get("find { must -> must.name == '" + name + "' }.id").toString()
                ;
    }

    private void deleteMust(String name) {
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(getMustId(name))
                .when()
                .delete(MUST_URL);
    }

}
