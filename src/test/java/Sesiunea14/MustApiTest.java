package Sesiunea14;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@Slf4j
public class MustApiTest {

    public static final String MUST_URL = "https://endavawineapp.azurewebsites.net/must/";
    Response response;

    @Test
    void testmusts() {

    	 String name = "EmilIuga";
    	 List<String> volume = List.of("50", "liters");
         String type = "dry";
         int volumeValue = 80;
         
         addmust(name,type,volumeValue, volume);
        System.out.println("must add complete.");

        getmusts();
        assertThat(ismustAvailable(name), is(true));

        String mustId = getmustId(name);
        System.out.println("our must id is: " + mustId);

        deletemust(mustId);
        getmusts();
        assertThat(ismustAvailable(name), is(false));

    }

    private boolean ismustAvailable(String name) {
        String musts = response.prettyPrint();
        return JsonPath.with(musts).get("name").toString().contains(name);
    }

    private void deletemust(String mustId) {

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .delete(MUST_URL + mustId);
    }

    private String getmustId(String name) {

        String musts = response.prettyPrint();
        return JsonPath.with(musts).get("find { a -> a.name == '" + name + "' }.id").toString();
    }

    private void getmusts() {

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get(MUST_URL);

        System.out.println(response);
    }

    private void addmust(String name, String type, int volumeValue, List<String>volume) {

    	 Map<String, Object> volumeMap = new HashMap<>();
         volumeMap.put("value", volumeValue);
         volumeMap.put("unit", "liters");

     

         Map<String, Object> bodyMap = new HashMap<>();
         bodyMap.put("name", name);
         bodyMap.put("type", type);
         bodyMap.put("volume", volumeMap);
        

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(bodyMap)
                .when()
                .post(MUST_URL);

  //      log.info(response.prettyPrint());
        System.out.println(response);

    }
}
