package Sesiunea14_tema;

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
    void testMusts() {
        /*[
        {
            "id": 0,
                "name": "string",
                "type": "string",
                "volume": {
            "unit": "string",
                    "value": 0
        }
        }
        ]*/
        String name = "Must" + Instant.now();
        String type = "RosuDeTuta";
        int volume = 77;
        addMust(name, type, volume);
        log.info("Must add complete.");
        System.out.println("Must add complete.");

        getMusts();
        assertThat(isMustAvailable(name), is(true));

        String MustId = getMustId(name);
        System.out.println("our Must id is: " + MustId);

        log.info("Delete must now");
        deleteMust(MustId);
        log.info("Check if deleted");
        getMusts();
        assertThat(isMustAvailable(name), is(false));

    }

    private boolean isMustAvailable(String name) {
        String Musts = response.prettyPrint();
        return JsonPath.with(Musts).get("name").toString().contains(name);
    }

    private void deleteMust(String MustId) {

        String mustBody;
        mustBody = "[" + MustId + "]";

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(mustBody)
                .when()
                .delete(MUST_URL);
    }

    private String getMustId(String name) {

        String Musts = response.prettyPrint();
        return JsonPath.with(Musts).get("find { a -> a.name == '" + name + "' }.id").toString();
    }

    private void getMusts() {

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get(MUST_URL);

        log.info(response.prettyPrint());
    }
    /*[
            {
                "id": 0,
                    "name": "string",
                    "type": "string",
                    "volume": {
                "unit": "string",
                        "value": 0
            }
            }
            ]*/
    private void addMust(String name, String type, int volume) {

        Map<String, Object> volumeMap = new HashMap<>();
        volumeMap.put("value", volume);
        volumeMap.put("unit", "rows");

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
