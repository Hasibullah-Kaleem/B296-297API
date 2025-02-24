package delete_requests;

import base_url.TodoBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class Delete01 extends TodoBaseUrl {

     /*
    Given
        https://jsonplaceholder.typicode.com/todos/198
    When
        User sends a DELETE request to the URL
    Then
        Status code should be 200
    And
        Response body should be this: {}
     */

    @Test
    public void test01() {
        //1 - Set the URL
        spec.pathParams("first","todos","second", 198);
        //2 - Set the payload / expected data
        //3 - Send the request
        Response response = given(spec).when().delete("{first}/{second}");
        //4 - Make Assertions
        response.then()
                .statusCode(200);

        //First way
        Map<String, Object> expectedData = new HashMap<>();
        System.out.println("expectedData = " + expectedData);

        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(actualData, expectedData);

        //Second way
        assertEquals(actualData.size(), 0);

        //Third way
        assertTrue(actualData.isEmpty());
    }
}
