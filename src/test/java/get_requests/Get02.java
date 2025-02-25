package get_requests;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class Get02 {
    /*
        Given
            https://petstore.swagger.io/v2/pet/0
        When
            User sends the GET request to the URL
        Then
            HTTP Status Code should be 404
        And
            The Status Line should be "HTTP/1.1 404 Not Found"
        And
            The Response Body should CONTAIN "Pet not found"
        And
            The Response Body should NOT CONTAIN "TechproEd"
        And
            Server value should be "Jetty(9.2.9.v20150224)"
 */


    @Test
    public void test01(){

        //1 - Set the URL
        String url = "https://petstore.swagger.io/v2/pet/0";
        //2 - Set the payload
        //3 - Send the request / get a response
        Response response = given().when().get(url);
        response.prettyPrint();
        //4 - Make assertions
        response
                .then().statusCode(404)
                .statusLine("HTTP/1.1 404 Not Found")
                .body(containsString("Pet not found"))
                .body(not(containsString("TechproEd")))
                .header("Server",equalTo("Jetty(9.2.9.v20150224)"));

    }

    @Test
    public void test02(){

        //1 - Set the URL
        String url = "https://petstore.swagger.io/v2/pet/0";
        //2 - Set the payload
        //3 - Send the request / get a response
        Response response = given().when().get(url);
        response.prettyPrint();
        //4 - Make assertions
        assertTrue(response.asString().contains("Pet not found"));
        assertFalse(response.asString().contains("TechproEd"));


    }
}
