package get_request;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class Get03 {

    public static void main(String[] args) {
    /*
        Given
            https://petstore.swagger.io/v2/pet/550
        When
            User sends the GET request to the URL
        Then
            HTTP Status Code should be 202
        And
            The Content-type should be "application/json"
        And
            "name" should contain "bob"
        And
            "status" value should be "available"
        And
            "name" value under "category" should be"cat"
        And
            The first object under "tags" should have a "name" value of "string"
 */
    }

    @Test
    public void test01() {

        //1 - Set the URL
        String url = "https://petstore.swagger.io/v2/pet/550";
        //2 - Set the payload
        //3 - Send the request / get a response
        //4 - Make assertions

        given().when().get(url)
                .then().statusCode(200)
                .contentType(ContentType.JSON)
                .body("name",containsString("bob"))
                .body("status",equalTo("available"))
                .body("category.name",equalTo("cat"))
                .body("tags[0].name",equalTo("string"))
                .log().body();

    }
}
