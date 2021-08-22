package Sesiunea14.tema;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Map;

@Slf4j
public class MustApiTest {

    public static final String MUST_URL = "https://endavawineapp.azurewebsites.net/must/";

    private Response response;

    @Test
    void testMust() {
        final String name = "must" + Instant.now();
        addMust(name, "must", new Volume("bottle", 10));
        getMust();
        Assertions.assertTrue(isMustAvailable(name));
        final int mustId = getMustId(name);
        deleteMust(mustId);
        Assertions.assertFalse(isMustAvailable(name));

    }

    private boolean isMustAvailable(String name) {
        return JsonPath.with(response.body().asString()).get("name").toString().contains(name);
    }

    private void addMust(String name, String type, Volume volume) {
        System.out.println("Adding must: " + name);
        final Map<String, Object> body = Map.of(
                "name", name,
                "type", type,
                "volume", volume
        );

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(MUST_URL);

        log.info(response.prettyPrint());
    }

    private void deleteMust(int mustId) {
        System.out.println("Deleting must with id: " + mustId);
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(new int[]{mustId})
                .when()
                .delete(MUST_URL);

        log.info(response.prettyPrint());
    }

    private int getMustId(String name) {
        return Integer.parseInt(JsonPath.with(response.body().asString()).get("find { must -> must.name == '" + name + "' }.id").toString());
    }

    private void getMust() {
        System.out.println("Get:");
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get(MUST_URL);

        log.info(response.prettyPrint());
    }
}

@Data
final class Volume {
    private final String unit;
    private final int value;

    Volume(String unit, int value) {
        this.unit = unit;
        this.value = value;
    }
}
