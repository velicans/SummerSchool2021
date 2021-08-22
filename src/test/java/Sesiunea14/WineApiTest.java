package Sesiunea14;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class WineApiTest {

    public static final String WINE_URL = "https://endavawineapp.azurewebsites.net/wines/";
    Response response;

    @Test
    void testWine() {

        getWine();

        String bottlingVolume = "11";

        List<String> composition = new ArrayList<String>();
        composition.add("compositionValue");
        String name = "vinulMeu";
        String type = "vinBun";
        int value = 1;

        //assert is done in addWine method
        addWine(bottlingVolume,  composition,  name,  type, value);

        //assert is done in patchWine method
        patchWine(30, bottlingVolume,  composition,  "",  "", value);

        //assert is done in deleteWine method
        deleteWine(name);

    }

    private void patchWine(int wineID, String bottlingVolume, List<String> composition, String name, String type, int value) {

        Map<String, Object> bodyMap = new HashMap<>();

        if (composition.size() > 0)
            bodyMap.put("composition", composition);

        if (bottlingVolume.length() > 0)
            bodyMap.put("bottlingVolume", bottlingVolume);

        if (name.length()>0)
            bodyMap.put("name", name);

        if (type.length()>0)
            bodyMap.put("type", type);

        if (value >= 0) {
            Map<String, Object> volumeMap = new HashMap<>();
            volumeMap.put("unit", "L");
            volumeMap.put("value", value);
            bodyMap.put("volume", volumeMap);
        }
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(bodyMap)
                .when()
                .patch(WINE_URL + wineID);

        log.info(response.prettyPrint());

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    private String getWineId(String name) {

        String wine = response.prettyPrint();
        return JsonPath.with(wine).get("find { a -> a.name == '" + name + "' }.id").toString();
    }

    private void deleteWine(String name) {

        //int wineId = Integer.parseInt(getWineId(name));

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .queryParam("30")
                .when()
                .delete(WINE_URL);


        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);

    }


    private void addWine(String bottlingVolume, List<String> composition, String name, String type, int value) {

        Map<String, Object> volumeMap = new HashMap<>();
        volumeMap.put("unit", "L");
        volumeMap.put("value", value);

        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("id", 30);
        bodyMap.put("bottlingVolume", bottlingVolume);
        bodyMap.put("composition", composition);
        bodyMap.put("name", name);
        bodyMap.put("type", type);
        bodyMap.put("volume", volumeMap);

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(bodyMap)
                .when()
                .post(WINE_URL);

        log.info(response.prettyPrint());

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);

    }

    private void getWine() {
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get(WINE_URL);

        log.info(response.prettyPrint());
    }

}
