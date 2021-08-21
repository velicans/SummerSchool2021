package Sesiunea14.tema;

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
public class WineApiTest {

    private static final String WINE_URL = "https://endavawineapp.azurewebsites.net/wines";
    Response response;

    private String name, type, bottlingVolume, bottlingVolumePatch, namePatch;
    private String[] composition = new String[1];
    private int volume ;

    @BeforeAll
    void setUp(){
        String[] types = {"sweet", "dry", "semi sweet"};
        composition[0] = "String1";
        name = "WINE" + Instant.now();
        type = new Utils().randomMustType(types);
        volume = 150;
        bottlingVolume = "1L";

        bottlingVolumePatch = "0.7L";
        namePatch = "namePatched";
    }

    @Test
    void testAddWine(){

        addWine(name, type, volume, composition, bottlingVolume);

        System.out.println("============Wine add complete==============");

        System.out.println("============Wine List==============");

        getWineList();

        System.out.println("=============================================");

        assertThat(isWineAvailable(name), is(true));

    }

    @Test
    void testDeleteWine(){

        System.out.println("WINE ID is : " + getWineId(name)[0]);

        deleteWine(name);

        System.out.println("=========Wine has been deleted=======");

        System.out.println("==================List after delete===============");

        getWineList();

        System.out.println("=============================================");

        assertThat(isWineAvailable(name), is(false));
    }

    @Test
    void testPatchWine(){

        patchWine();

        assertThat(checkIfPatchSuccessful(), is(true));

    }

    private void addWine(String name, String type, int volume, String[] composition, String bottlingVolume){

        Map<String, Object> volumeMap = new HashMap<>();
        volumeMap.put("value", volume);
        volumeMap.put("unit", "liters");

        Map<String, Object> wineBody = new HashMap<>();
        wineBody.put("id", new Random().nextInt(99) + 1);
        wineBody.put("name", name);
        wineBody.put("volume",volumeMap);
        wineBody.put("type", type);
        wineBody.put("composition", composition);
        wineBody.put("bottlingVolume", bottlingVolume);

        response = RestAssured.given().contentType(ContentType.JSON).body(wineBody).when().post(WINE_URL);
    }

    private boolean checkIfPatchSuccessful(){

        String jsonBottling = JsonPath.with(response.prettyPrint()).get("find { must -> must.name == '" + name + "' }.bottlingVolume");
        String jsonName = JsonPath.with(response.prettyPrint()).get("find { must -> must.name == '" + name + "' }.name");

        if(jsonBottling.equals(bottlingVolume)  || jsonName.equals(name))
            return false;
        return true;
    }

    private void patchWine(){

        Map<String, Object> patchObj = new HashMap<>();
        patchObj.put("name", namePatch);
        patchObj.put("bottlingVolume", bottlingVolumePatch);

        response = RestAssured.given().contentType(ContentType.JSON).body(patchObj).when().patch(WINE_URL + "/" + getWineId(name));
    }

    private boolean isWineAvailable(String name){
        return JsonPath.with(response.prettyPrint()).get("name").toString().contains(name);
    }

    private void getWineList() {
        response = RestAssured.given().contentType(ContentType.JSON).when().get(WINE_URL);
        System.out.println(response.prettyPrint());
    }

    private int[] getWineId(String name) {
        int[] ids = {JsonPath.with(response.prettyPrint()).get("find { must -> must.name == '" + name + "' }.id")};
        return ids;
    }

    private void deleteWine(String name){
        response = RestAssured.given().contentType(ContentType.JSON).when().delete(WINE_URL + "?q=" + getWineId(name));
    }

}
