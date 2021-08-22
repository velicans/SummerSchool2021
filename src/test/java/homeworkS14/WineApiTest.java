package homeworkS14;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@Slf4j
public class WineApiTest {

    public static final String WINE_URL = "https://endavawineapp.azurewebsites.net/wines/";
    Response response;

    @Test
    public void testWines() {
        String name = "My first wine";
        int bottlingVolume = 1;
        int volumeValue = 80;
        List<String> composition = List.of("Feteasca alba", "Grasa de Cotnari");
        String type = "Sauvignon";

        String patchedName = "My second wine";

        addWine(name, bottlingVolume, volumeValue, composition, type);
        System.out.println("My wine was added");

        getWines();
        assertThat(isWineAvailable(name), is(true));

        String id = getWineId(name);
        System.out.println("My wine id is " + id);

        patchWine(id, patchedName);
        assertThat(isWineAvailable(patchedName), is(true));

        getWines();

        deleteWine(id);
        assertThat(isWineAvailable(patchedName), is(false));

    }

    private void getWines() {

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get(WINE_URL);

        log.info(response.prettyPrint());
    }

    private void addWine(String name, int bottlingVolume, int volumeValue, List<String> composition, String type) {

        Map<String, Object> volumeMap = new HashMap<>();
        volumeMap.put("value", volumeValue);
        volumeMap.put("unit", "liters");

        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("name", name);
        bodyMap.put("bottlingVolume", bottlingVolume);
        bodyMap.put("volume", volumeMap);
        bodyMap.put("composition", composition);
        bodyMap.put("type", type);


        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(bodyMap)
                .when()
                .post(WINE_URL);

        log.info(response.prettyPrint());

    }

    private void deleteWine(String wineId) {

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body("[" + wineId + "]")
                .when()
                .delete(WINE_URL);
    }

    private String getWineId(String name) {

        String wine = response.prettyPrint();
        return JsonPath.with(wine).get("find { a -> a.name == '" + name + "' }.id").toString();
    }

    private boolean isWineAvailable(String name) {
        String wine = response.prettyPrint();
        return JsonPath.with(wine).get("name").toString().contains(name);
    }

    private void patchWine(String wineId, String newName) {

        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("name", newName);
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .param(WINE_URL, wineId)
                .body(bodyMap)
                .when()
                .patch(WINE_URL);
    }
}
