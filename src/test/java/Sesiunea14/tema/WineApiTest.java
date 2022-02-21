package Sesiunea14.tema;
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
public class WineApiTest {

        public static final String WINE_URL = "https://endavawineapp.azurewebsites.net/wines/";
        private String wineId;
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{}")
                .when()
                .delete(WINE_URL + wineId);




@Test

void testWine(){
        String name = "Chianti" + Instant.now();
        int quantity = 25;
        String type = "sweet";
        AddWine(name, quantity, type);
   
        log.info("wine successfully added.");
        System.out.println("The wine has been successfully added.");

        GetWine();
        assertThat(isWineAvailable(name), is(true));

        String wineId = GetWineId(name);
        System.out.println("the grape ID is: " + wineId);
}

        public String GetWineId ( String name ) {
                String wine = response.prettyPrint();
                return JsonPath.with(wine).get("find { a -> a.name == '" + name + "' }.id").toString();
        }

        private void DeleteWine (String wineId){

                response = RestAssured.given()
                        .contentType(ContentType.JSON)
                        .body("[" +wineId + "]")
                        .when()
                        .delete(WINE_URL);

        }

        //failed
        private void patchWine(){

                response = RestAssured.given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .body("{}")
                        .when()
                        .delete(WINE_URL + wineId);

        }

        private boolean isWineAvailable ( String name ) {
                String wine = response.prettyPrint();
                return JsonPath.with(wine).get("name").toString().contains(name);
        }

        private void GetWine ( ) {
                response = RestAssured.given()
                        .contentType(ContentType.JSON)
                        .when()
                        .get(WINE_URL);


                log.info(response.prettyPrint());

        }

        private void AddWine ( String name, int quantity, String type ) {
                Map<String, Object> quantityMap = new HashMap<>();
                quantityMap.put("value", quantity);
                quantityMap.put("unit", "liters");

                Map<String, Object> bodyMap = new HashMap<>();
                bodyMap.put("name", name);
                bodyMap.put("volume", quantityMap);
                bodyMap.put("type", type);

                response = RestAssured.given()
                        .contentType(ContentType.JSON)
                        .body(bodyMap)
                        .when()
                        .post(WINE_URL);

                log.info(response.prettyPrint());



        }



}