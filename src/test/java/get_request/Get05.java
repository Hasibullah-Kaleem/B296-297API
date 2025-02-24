package get_request;

import base_url.PetStoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Get05 extends PetStoreBaseUrl {

      /*
            Given
                https://petstore.swagger.io/v2/pet/findByStatus?status=available
            When
                User sends the GET request to the URL
            Then
                HTTP Status Code should be 200
            And
                The Content-Type should be "application/json"
            And
                There should be an item in the list with an Id of 550
            And
                There should be an item in the list with a name value of "bob"
            And
                There should be items in the list with name values of "bob", "fish", "doggie"
            And
                There should be at least 200 items in the list
            And
                There should be less than 500 items in the list
            And
                The first item in the list should have a category - id 0
            And
                The first item in the list should have a photoUrls value of "string"
            And
                The first item in the list should have tags - id 0
     */

    @Test
    public void test01() {
        //1 - Set the URL
        spec.pathParams("type", "pet", "operation", "findByStatus")
                .queryParam("status", "available");
        //2 - Set the payload
        //3 - Send the request
        Response response = given(spec).when().get("{type}/{operation}");

        response.prettyPrint();
        //4 - Make Assertions
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", hasItem(550))
                .body("name", hasItem("bob"))
                .body("name", hasItems("bob", "fish", "doggie"))
                .body("id", hasSize(greaterThanOrEqualTo(200)))
                .body("id", hasSize(lessThan(1000)))
                .body("id", hasSize(lessThan(500)))//there were more than 500
                .body("[0].category.id", equalTo(0))
                .body("[0].photoUrls[0]", equalTo("string"))
                .body("[0].tags[0].id", equalTo(0));


    }

}
