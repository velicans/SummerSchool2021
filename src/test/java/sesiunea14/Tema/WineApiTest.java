package sesiunea14.Tema;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


@Slf4j
public class WineApiTest {

    private static final String WINE_URL = "https://endavawineapp.azurewebsites.net/wines";
    Response response;

    private String name, type, bottlingVolume, composition, nameToPatch, bottlingVolumeToPatch;
    private int volume;

    @BeforeAll
    void setUp() {
        composition = "Merlot";
        name = "MyWine" + Instant.now();
        type = "dry";
        volume = 300;
        bottlingVolume = "750ml ";
        nameToPatch = "MyWinePatch" + Instant.now();
        bottlingVolumeToPatch = "1000 ml";
    }

    @Test
    void testWine() {

        addWine(name, type, volume, composition, bottlingVolume);
        System.out.println("Wine add complete");

        getWineList();
        System.out.println("Wine list: ");
        assertThat(isWineAvailable(name), is(true));

        deleteWine();
        System.out.println("Wine has been deleted");

        getWineList();
        System.out.println("List after the wine was deleted");
        assertThat(isWineAvailable(name), is(false));

        patchWine();
        assertThat(checkIfPatchSuccessful(), is(true));

    }

    private void addWine(String name, String type, int volume, String composition, String bottlingVolume) {

        Map<String, Object> volumeMap = new HashMap<>();
        volumeMap.put("value", volume);
        volumeMap.put("unit", "liters");

        Map<String, Object> wineBody = new HashMap<>();
        wineBody.put("id", new Random().nextInt(99) + 1);
        wineBody.put("name", name);
        wineBody.put("volume", volumeMap);
        wineBody.put("type", type);
        wineBody.put("composition", composition);
        wineBody.put("bottlingVolume", bottlingVolume);

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(wineBody)
                .when()
                .post(WINE_URL);
    }

    private boolean checkIfPatchSuccessful() {

        String patchBottling = JsonPath.with(response.prettyPrint())
                .get("find { wine -> wine.name == '" + name + "' }.bottlingVolume");
        String patchName = JsonPath.with(response.prettyPrint())
                .get("find { wine -> wine.name == '" + name + "' }.name");

        if (patchBottling.equals(bottlingVolume) || patchName.equals(name))
            return false;
        return true;
    }

    private void patchWine() {

        Map<String, Object> patchObj = new HashMap<>();
        patchObj.put("name", nameToPatch);
        patchObj.put("bottlingVolume", bottlingVolumeToPatch);

        response = RestAssured.given().contentType(ContentType.JSON)
                .body(patchObj)
                .when()
                .patch(WINE_URL + "/" + getWineId(name));
    }

    private boolean isWineAvailable(String name) {
        return JsonPath.with(response.prettyPrint())
                .get("name")
                .toString()
                .contains(name);
    }

    private void getWineList() {
        response = RestAssured.given().contentType(ContentType.JSON)
                .when()
                .get(WINE_URL);
        System.out.println(response.prettyPrint());
    }

    private int getWineId(String name) {
        return JsonPath.with(response.prettyPrint())
                .get("find { wine -> wine.name == '" + name + "' }.id");
    }

    private void deleteWine() {
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .delete(WINE_URL + "?wineid=" + getWineId(name));
    }

}