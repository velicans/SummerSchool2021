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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MustApiTest {

    private static final String MUST_URL = "https://endavawineapp.azurewebsites.net/must/";
    Response response;

    private String name;
    private String type ;
    private  int volume ;

    @BeforeAll
     void setUp(){
        String[] types = {"sweet", "dry", "semi sweet"};

        name = "MUST" + Instant.now();
        type = new Utils().randomMustType(types);
        volume = 250;
    }


    @Test
    void testAddMust() {

        addMust(name, type, volume);
        System.out.println("============Must add complete==============");



        System.out.println("============Must List==============");
        getMustList();
        System.out.println("=============================================");


        assertThat(isMustAvailable(name), is(true));
    }

    @Test
    void testDeleteMust() {
        System.out.println("MUST ID is : " + getMustId(name)[0]);
        deleteMust(name);
        System.out.println("=========Must has been deleted=======");



        System.out.println("==================List after delete===============");
        getMustList();
        System.out.println("=============================================");


        assertThat(isMustAvailable(name), is(false));
    }


    private void addMust(String name, String type, int volume){

        Map<String, Object> volumeMap = new HashMap<>();
        volumeMap.put("value", volume);
        volumeMap.put("unit", "liters");

        Map<String, Object> mustBody = new HashMap<>();
        mustBody.put("name", name);
        mustBody.put("volume",volumeMap);
        mustBody.put("type", type);

        response = RestAssured.given().contentType(ContentType.JSON).body(mustBody).when().post(MUST_URL);
    }

    private boolean isMustAvailable(String name){
        return JsonPath.with(response.prettyPrint()).get("name").toString().contains(name);
    }

    private void getMustList() {
        response = RestAssured.given().contentType(ContentType.JSON).when().get(MUST_URL);
        System.out.println(response.prettyPrint());
    }

    private int[] getMustId(String name) {
        int[] ids = {JsonPath.with(response.prettyPrint()).get("find { must -> must.name == '" + name + "' }.id")};
        return ids;
    }

    private void deleteMust(String name){
        response = RestAssured.given().contentType(ContentType.JSON).body(getMustId(name)).when().delete(MUST_URL);
    }

}
