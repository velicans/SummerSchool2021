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
public class MustApiTestTema {


    public static final String MUST_URL = "https://endavawineapp.azurewebsites.net/must/";
    Response response;


    @Test
    void testMust ( ) {

    }

    String name = "Chianti" + Instant.now ( );
    int Volume = 150;
    String type = "Sweet";


    @Test

    private void addMust ( String name, String type, int volume ) {

        Map< String, Object > volumeMap = new HashMap<> ( );
        volumeMap.put ( "value", volume );
        volumeMap.put ( "measuring unit", "liters" );

        Map< String, Object > mustBody = new HashMap<> ( );
        mustBody.put ( "name", name );
        mustBody.put ( "volume", volumeMap );
        mustBody.put ( "type", type );

        response = RestAssured.given ( ).contentType ( ContentType.JSON ).body ( mustBody ).when ( ).post ( MUST_URL );

    }

    private boolean isMustAvailable ( String name ) {
        return JsonPath.with ( response.prettyPrint ( ) ).get ( "name" ).toString ( ).contains ( name );
    }

    private void getMustList ( ) {
        response = RestAssured.given ( ).contentType ( ContentType.JSON ).when ( ).get ( MUST_URL );
        System.out.println ( response.prettyPrint ( ) );
    }

    private Object[] getMustId ( String name ) {
        Object[] ids = new Object[]{JsonPath.with ( response.prettyPrint ( ) ).get ( "find { must -> must.name == '" + name + "' }.id" )};
        return ids;
    }

    private void deleteMust ( String name ) {
        response = RestAssured.given ( ).contentType ( ContentType.JSON ).body ( getMustId ( name ) ).when ( ).delete ( MUST_URL );
    }

}












