package homeworkS14;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@Slf4j
public class MustApiTest {

    public static final String MUST_URL = "https://endavawineapp.azurewebsites.net/must/";
    Response response;

    @Test
    public void testMust() {
        String name = "My best must";
        int volumeValue = 1;
        String type = "Sauvignon Blanc";

        addMust(name, volumeValue, type);
        System.out.println("Must was added");

        getMust();
        assertThat(isMustAvailable(name), is(true));

        String mustId = getMustId(name);
        System.out.println("My must id is: " + mustId);

        deleteMust(mustId);

        getMust();
        assertThat(isMustAvailable(name), is(false));


    }

    private void getMust() {

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get(MUST_URL);

        log.info(response.prettyPrint());
    }

    private void addMust(String name, int volumeValue, String type) {

        Map<String, Object> volumeMap = new HashMap<>();
        volumeMap.put("value", volumeValue);
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

    private boolean isMustAvailable(String name) {
        String must = response.prettyPrint();
        return JsonPath.with(must).get("name").toString().contains(name);
    }
}
