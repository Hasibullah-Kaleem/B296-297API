package post_request;

import base_url.TodoBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.Reusables;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class Post04 extends TodoBaseUrl {
    /*
    Given
        https://jsonplaceholder.typicode.com/todos
        {"userId":55, "title":"Tidy your room", "completed":false}
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
    public void test01() throws JsonProcessingException {
        //1 - Set the URL
        spec.pathParam("first", "todos");
        //2 - Set the payload / expected data
        String payloadJson = "{\"userId\":55, \"title\":\"Tidy your room\", \"completed\":false}";

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> payload = objectMapper.readValue(payloadJson, HashMap.class);

        System.out.println("payload = " + payload);

        /*
        readValue method takes the JSON string as the first parameter, and converts it to the type
        provided in the second parameter, which is a Map of String, Object in our case. This way,
        we do not have to enter the data one by one. We save time.

        IMPORTANT!!! ==> import com.fasterxml.jackson.databind.ObjectMapper;
         */

        //3 - Send the request
        Response response = given(spec).body(payload).when().post("{first}");
        //4 - Make Assertions
        Map<String, Object> actualData = response.as(HashMap.class);

        Map<String, Object> actualData1 = objectMapper.readValue(response.asString(), HashMap.class);

        assertEquals(response.statusCode(), 201);
        assertEquals(actualData.get("userId"), payload.get("userId"));
        assertEquals(actualData.get("title"), payload.get("title"));
        assertEquals(actualData.get("completed"), payload.get("completed"));
    }

    @Test
    public void testWithReusableMethod(){
        //1 - Set the URL
        spec.pathParam("first", "todos");
        //2 - Set the payload / expected data
        Map<String, Object> payload = Reusables.convertJsonStringToMap("{\"userId\":55, \"title\":\"Tidy your " +
                "room\", \"completed\":false}");

        //3 - Send the request
        Response response = given(spec).body(payload).when().post("{first}");
        //4 - Make Assertions
        Map<String, Object> actualData = response.as(HashMap.class);

        assertEquals(response.statusCode(), 201);
        assertEquals(actualData.get("userId"), payload.get("userId"));
        assertEquals(actualData.get("title"), payload.get("title"));
        assertEquals(actualData.get("completed"), payload.get("completed"));
    }
}