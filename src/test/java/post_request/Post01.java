package post_request;

import base_url.PetStoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Post01 extends PetStoreBaseUrl {


    /*
    Given
        https://petstore.swagger.io/v2/pet
    And
        {
            "id": 550,
            "category": {
                "id": 0,
                "name": "CAT"
            },
            "name": "bob",
            "photoUrls": [
                "string"
            ],
            "tags": [
                {
                    "id": 0,
                    "name": "bird"
                }
            ],
            "status": "available"
        }
    When
        User sends a POST request to the URL
    Then
        HTTP Status Code should be 200
    And
        Content Type should be "application/json"
*/
    @Test

    public void test01(){
        spec.pathParam("param","pet");
        String payload = "{\n" +
                "    \"id\": 550,\n" +
                "    \"category\": {\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"CAT\"\n" +
                "    },\n" +
                "    \"name\": \"bob\",\n" +
                "    \"photoUrls\": [\n" +
                "        \"string\"\n" +
                "    ],\n" +
                "    \"tags\": [\n" +
                "        {\n" +
                "            \"id\": 0,\n" +
                "            \"name\": \"string\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"status\": \"available\"\n" +
                "}";
        Response response = given(spec).when().body(payload).post("{param}");
        response.prettyPrint();
        response.then()
                .statusCode(200)
                .contentType(ContentType.JSON);


    }

}
