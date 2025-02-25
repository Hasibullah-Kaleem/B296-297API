package get_requests;

import base_url.PetStoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Get04 extends PetStoreBaseUrl {

     /*
            Given
                https://petstore.swagger.io/v2/pet/550
            When
                User sends the GET request to the URL
            Then
                HTTP Status Code should be 200
            And
                The Content-Type should be "application/json"
     */

    @Test
    public void test01() {
        //1 - Set the URL
        spec.pathParams("first", "pet", "second", 550);
        //2 - Set the payload
        //3 - Sending the request
        Response response = given(spec).when().get("{first}/{second}");
        //4 - Make assertions
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }
}
