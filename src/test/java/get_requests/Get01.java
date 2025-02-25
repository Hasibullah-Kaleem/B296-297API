package get_requests;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Get01 {

    /*
    Given
        https://petstore.swagger.io/v2/pet/550
    When
        User sends a GET request to the endpoint.
    Then
        HTTP Status code should be 200.
    And
        Content Type should be "application/json"
    And
        Status Line should be "HTTP/1.1 200 OK"
     */

    @Test
    public void test01(){

        // Set the url
        String endPoint = "https://petstore.swagger.io/v2/pet/550";
        // set the payload
        // send request get response
        Response response = given().when().get(endPoint);
        response.prettyPrint();
        // make assertions
        response.then().assertThat().statusCode(200)
                .and().assertThat().contentType("application/json")
                .and().assertThat().statusLine("HTTP/1.1 200 OK");
    }
}
