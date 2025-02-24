package post_request;

import base_url.ContactListBaseUrl;
import base_url.TodoBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;

public class Post02 extends TodoBaseUrl {

    /*
Given
    https://jsonplaceholder.typicode.com/todos

    {
        "userId":55,
        "title":"Tidy your room",
        "completed":false
    }
When
    User sends a POST request to the URL
Then
    Status code should be 201
And
    Response body should be this:
    {
        "userId":55,
        "title":"Tidy your room",
        "completed":false,
        "id":201
    }
 */

    @Test
    public void test01() {
        //1 - Set the URL
        spec.pathParam("path", "todos");
        //2 - Set the payload
        String payload = "{\n" +
                "    \"userId\": 55,\n" +
                "    \"title\": \"Tidy your room\",\n" +
                "    \"completed\": false\n" +
                "}";
        //3 - Send the request
        Response response = given(spec).body(payload).when().post("{path}");
        //4 - Make Assertions
        //First way, jsonPath way
        JsonPath jsonPath = response.jsonPath();
        assertEquals(response.statusCode(), 201);
        assertEquals(jsonPath.getInt("userId"),55);
        assertEquals(jsonPath.getString("title"),"Tidy your room");
        assertFalse(jsonPath.getBoolean("completed"));
        assertEquals(jsonPath.getInt("id"),201);

        //Second way, hamcrest matchers, HW
    }
}
