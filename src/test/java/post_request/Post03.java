package post_request;

import base_url.TodoBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Post03 extends TodoBaseUrl {

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
        spec.pathParam("first", "todos");
        //2 - Set the payload / expected data
        String payload = "{\n" +
                "    \"userId\": 55,\n" +
                "    \"title\": \"Tidy your room\",\n" +
                "    \"completed\": false\n" +
                "}";
        Map<String,Object> payLoadMap = new HashMap<>();
        payLoadMap.put("userId",55);
        payLoadMap.put("title","Tidy your room");
        payLoadMap.put("completed",false);
        System.out.println(payLoadMap);
        System.out.println(payload);
        //3 - Send a request
        Response response = given(spec).body(payLoadMap).when().post("{first}");
        response.prettyPrint();
        //4 - Make Assertions
        JsonPath jsonPath = response.jsonPath();

        //payloadMap.put("id", 201); This is also usable for the missing id information. But it is not
        //really a good practice. Just taking the id from the testcase is more suitable.

        //Payload maps can also be used in assertions as expected data. That's why we said step 2
        //is setting the payload aka expected data in the first place.

        //Since we send the payload map in the request, it becomes what we want to see, what we expect.
        //That's why we can say, the map is our expected data.
        assertEquals(jsonPath.getInt("userId"), payLoadMap.get("userId"));
        assertEquals(jsonPath.getString("title"), payLoadMap.get("title"));
        assertEquals(jsonPath.getBoolean("completed"), payLoadMap.get("completed"));
        assertEquals(jsonPath.getInt("id"), 201);

    }

    @Test
    public void test02() {
        //1 - Set the URL
        spec.pathParam("first", "todos");
        //2 - Set the payload / expected data

        Map<String, Object> payLoadMap = new HashMap<>();
        payLoadMap.put("userId", 55);
        payLoadMap.put("title", "Tidy your room");
        payLoadMap.put("completed", false);
        System.out.println(payLoadMap);

        //3 - Send a request
        Response response = given(spec)
                .body(payLoadMap)
                .when()
                .post("{first}");
        response.prettyPrint();
        //4 - Make Assertions

        Map<String,Object> actualData = response.as(HashMap.class);
        System.out.println(actualData);

        assertEquals(actualData.get("userId"),payLoadMap.get("userId"));
        assertEquals(actualData.get("title"),payLoadMap.get("title"));
        assertEquals(actualData.get("completed"),payLoadMap.get("completed"));
        assertEquals(actualData.get("id"),201);

    }
}
