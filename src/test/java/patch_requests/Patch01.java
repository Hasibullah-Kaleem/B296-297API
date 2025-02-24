package patch_requests;

import base_url.TodoBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Patch01 extends TodoBaseUrl {

       /*
    Given
        https://jsonplaceholder.typicode.com/todos/198

        {
            "userId": 10,
            "title": "Wash the dishes",
            "completed": true
        }
    When
        User sends a PATCH request to the URL
    Then
        Status code should be 200
    And
        Response body should be this:
        {
            "userId": 10,
            "title": "Wash the dishes",
            "completed": true,
            "id": 198
        }
     */

    @Test
    public void test01() {

            //1 - Set the URL
            spec.pathParams("first", "todos", "second", 198);
            //2 - Set the payload
            Map<String, Object> payload = new HashMap<>();
            payload.put("title", "Wash the dishes");
            //3 - Send the request
            Response response = given(spec)
                    .body(payload)
                    .when()
                    .patch("{first}/{second}");
            //4 - Make assertions
            //We properly structure our expected data for assertions. By this time, payload is not going to be
            //used for the requests, but for assertions.
            payload.put("userId", 10);
            payload.put("completed", true);
            payload.put("id", 198);

            Map<String, Object> actualData = response.as(HashMap.class);

            assertEquals(actualData.get("userId"), payload.get("userId"));
            assertEquals(actualData.get("title"), payload.get("title"));
            assertEquals(actualData.get("completed"), payload.get("completed"));
            assertEquals(actualData.get("id"), payload.get("id"));

    }
}
