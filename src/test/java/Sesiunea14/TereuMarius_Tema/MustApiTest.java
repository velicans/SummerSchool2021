package Sesiunea14.TereuMarius_Tema;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class MustApiTest {

    private static final String MUST_URL = "https://endavawineapp.azurewebsites.net/must/";
    Response response;

    private String name;
    private String type;
    private int volume;

    @BeforeAll
    void setUp() {

        name = "MUST" + Instant.now();
        type =  "sweet";
        volume = 100;
    }


    @Test
    void testAddMust() {

        addMust(name, type, volume);
        System.out.println("Must add is done!");
        getMustCollection();
        assertThat(isMustAvailable(name), is(true));
    }

    @Test
    void testDeleteMust() {
        System.out.println("Must ID is : " + getMustId(name)[0]);
        deleteMust(name);
        System.out.println("Must is removed!");
        getMustCollection();
        assertThat(isMustAvailable(name), is(false));
    }


    private void addMust(String name, String type, int volume) {

        Map<String, Object> volumeMap = new HashMap<>();
        volumeMap.put("value", volume);
        volumeMap.put("unit", "liters");

        Map<String, Object> mustJson = new HashMap<>();
        mustJson.put("name", name);
        mustJson.put("volume", volumeMap);
        mustJson.put("type", type);


        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(mustJson).when()
                .post(MUST_URL);
    }

    private boolean isMustAvailable(String name) {
        return JsonPath.with(response.prettyPrint()).get("name").toString().contains(name);
    }

    private void getMustCollection() {
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get(MUST_URL);
        System.out.println(response.prettyPrint());
    }

    private int[] getMustId(String name) {
        String mustList = response.prettyPrint();

        int[] mustIds= {JsonPath.with(mustList).get("find { mustItem -> mustItem.name == '" + name + "' }.id")};
        return mustIds;
    }

    private void deleteMust(String name) {
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(getMustId(name))
                .when()
                .delete(MUST_URL);
    }

}


