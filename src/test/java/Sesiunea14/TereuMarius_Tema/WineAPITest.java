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
import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class WineAPITest {
    private static final String WINE_URL = "https://endavawineapp.azurewebsites.net/wines";
    Response response;

    private String name, type, bottlingVolume, bottlingVolumePatch, namePatch;
    private String[] composition = new String[1];
    private int volume ;

    @BeforeAll
    void setUp(){
        composition[0] = "MyComposition";
        name = "Wine" + Instant.now();
        type = "sweet";
        volume = 100;
        bottlingVolume = "1.5L";

        bottlingVolumePatch = "0.5L";
        namePatch = "namePatched";
    }

    @Test
    void testAddWine(){

        addWine(name, type, volume, composition, bottlingVolume);

        System.out.println("Wine add is done!");
        getWineList();
        assertThat(isWineAvailable(name), is(true));

    }

    @Test
    void testDeleteWine(){

        System.out.println("Wine ID is : " + getWineId(name)[0]);
        deleteWine(name);
        System.out.println("Wine is removed!");
       getWineList();
       assertThat(isWineAvailable(name), is(false));
    }

    @Test
    void testPatchWine(){
        patchWine();
        assertThat(checkIfPatchApplied(), is(true));
    }

    private void addWine(String name, String type, int volume, String[] composition, String bottlingVolume){

        Map<String, Object> volumeMap = new HashMap<>();
        volumeMap.put("value", volume);
        volumeMap.put("unit", "liters");

        Map<String, Object> wineJson = new HashMap<>();
        wineJson.put("id", new Random().nextInt(99) + 1);
        wineJson.put("name", name);
        wineJson.put("volume",volumeMap);
        wineJson.put("type", type);
        wineJson.put("composition", composition);
        wineJson.put("bottlingVolume", bottlingVolume);

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(wineJson)
                .when()
                .post(WINE_URL);
    }

    private boolean checkIfPatchApplied(){

        String jsonBottling = JsonPath.with(response.prettyPrint()).get("find { wineItem -> wineItem.name == '" + name + "' }.bottlingVolume");
        String jsonName = JsonPath.with(response.prettyPrint()).get("find { wineItem -> wineItem.name == '" + name + "' }.name");

        if(jsonBottling.equals(bottlingVolume)  || jsonName.equals(name))
            return false;
        return true;

    }

    private void patchWine(){

        Map<String, String> winePatchMap = new HashMap<>();
        winePatchMap.put("name", namePatch);
        winePatchMap.put("bottlingVolume", bottlingVolumePatch);

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(winePatchMap)
                .when()
                .patch(WINE_URL + "/" + getWineId(name));
    }

    private boolean isWineAvailable(String name){
        return JsonPath.with(response.prettyPrint()).get("name").toString().contains(name);
    }

    private void getWineList() {
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get(WINE_URL);
        System.out.println(response.prettyPrint());
    }

    private int[] getWineId(String name) {
        int[] ids = {JsonPath.with(response.prettyPrint()).get("find { wineItem -> wineItem.name == '" + name + "' }.id")};
        return ids;
    }

    private void deleteWine(String name){
        response = RestAssured.given().contentType("*/*").when().delete(WINE_URL + "?q=" + getWineId(name));
    }


}
