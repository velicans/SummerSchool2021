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
public class MustApiTest {

    public static final String MUST_URL = "https://endavawineapp.azurewebsites.net/must";
    Response response;

    @Test
    void testMust() {

        String name = "Must" + Instant.now();
        String type = "dry";
        int volume = 56;
        addMust(name, type, volume);
        System.out.println("must add complete");

        getMust();
        isMustAvailable(name);
        assertThat(isMustAvailable(name), is(true));

        String mustId = getMustId(name);
        System.out.println("our must id is: " + mustId);

        deleteMust(mustId);
        assertThat(isMustAvailable(name), is(false));

    }

    private void deleteMust(String mustId) {
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .delete(MUST_URL + mustId);
    }

    private String getMustId(String name) {
        String musts = response.prettyPrint();
        return JsonPath.with(musts).get("find { a -> a.name == '" + name + "' }.id").toString();
    }

    private boolean isMustAvailable(String name) {
        String must = response.prettyPrint();
        return JsonPath.with(must).get("name").toString().contains(name);
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

        Map<String, Object> bodyMapMust = new HashMap<>();
        bodyMapMust.put("name", name);
        bodyMapMust.put("type", type);
        bodyMapMust.put("volume", volumeMap);

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(bodyMapMust)
                .when()
                .post(MUST_URL);

        log.info(response.prettyPrint());
    }
}
