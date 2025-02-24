package put_requests;

import base_url.TodoBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Put01 extends TodoBaseUrl {


     /*
    Given
        https://jsonplaceholder.typicode.com/todos/198

        {
            "userId": 21,
            "title": "Wash the dishes",
            "completed": false
        }
    When
        User sends a PUT request to the URL
    Then
        Status code should be 200
    And
        Response body should be this:
        {
            "userId": 21,
            "title": "Wash the dishes",
            "completed": false,
            "id": 198
        }
     */

    @Test
    public void test01() {
        //1 - Set the URL
        spec.pathParams("first","todos","second", 198);

        //2 - Set the payload
        Map<String, Object> payload = new HashMap<>();
        payload.put("userId", 21);
        payload.put("title", "Wash the dishes");
        payload.put("completed", false);

        //3 - Send the request
        Response response = given(spec)
                .body(payload)
                .when()
                .put("{first}/{second}");

        response.prettyPrint();

        //4 - Make Assertions
        response.then().statusCode(200);

        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println(actualData);

        assertEquals(actualData.get("userId"), payload.get("userId"));
        assertEquals(actualData.get("title"), payload.get("title"));
        assertEquals(actualData.get("complete"), payload.get("complete"));
        assertEquals(actualData.get("id"), 198);
    }
}
