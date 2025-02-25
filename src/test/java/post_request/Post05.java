package post_request;

import base_url.TodoBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.TodoPayLoad;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Post05 extends TodoBaseUrl {

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
    public void test01() {
        //1 - Set the URL
        spec.pathParam("first","todos");
        //2 - Set the payload / expected data
        TodoPayLoad payLoad = new TodoPayLoad(55,"Tidy your room",false);
        //3 - Send the request
        Response response = given(spec).body(payLoad).when().post("{first}");
        //4 - Make Assertions
        TodoPayLoad actualData = response.as(TodoPayLoad.class);

        assertEquals(response.statusCode(),201);
        assertEquals(actualData.getUserId(),payLoad.getUserId());
        assertEquals(actualData.getTitle(),payLoad.getTitle());
        assertEquals(actualData.getCompleted(),payLoad.getCompleted());

    }
}
