package practices;

import base_url.TodoBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Practice01 extends TodoBaseUrl {

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
        // set url
        spec.pathParam("first","todos");
        // set payload
        PayLoadPojo payLoad = new PayLoadPojo(55,"Tidy your room", false);
        // send request

        Response response = given(spec).body(payLoad).when().post("{first}");
        PayLoadPojo actualData = response.as(PayLoadPojo.class);

        // Make assertions

        assertEquals(response.statusCode(),201);
        assertEquals(actualData.getUserId(),payLoad.getUserId());
        assertEquals(actualData.getTitle(),payLoad.getTitle());
        assertEquals(actualData.getCompleted(),payLoad.getCompleted());

    }
}
