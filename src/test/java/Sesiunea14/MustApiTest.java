package Sesiunea14;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import org.json.*;

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


        String name = "MustulMeu2";
        String type = "tipDeMust";
        int volume = 2;

        //assert is done in addMust method
        addMust(name, type, volume);

        getMust();

        //assert is done in deleteMust method
        deleteMust(name);

        getMust();

    }

    private String getMustId(String name) {

        String grapes = response.prettyPrint();
        return JsonPath.with(grapes).get("find { a -> a.name == '" + name + "' }.id").toString();
    }

    private void deleteMust(String name) {

        int mustID = Integer.parseInt(getMustId(name));

        String body = "[" + mustID + "]";

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .delete(MUST_URL);


        int statusCode = response.getStatusCode();
        System.out.println(response.asString());
        Assert.assertEquals(statusCode, 200);

    }

    private void addMust(String name, String type, int volume) {

        Map<String, Object> volumeMap = new HashMap<>();
        volumeMap.put("value", volume);
        volumeMap.put("unit", "L");

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

        int statusCode = response.getStatusCode();
        System.out.println(response.asString());
        Assert.assertEquals(statusCode, 200);

    }

    private void getMust() {
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get(MUST_URL);

        log.info(response.prettyPrint());
    }

}
