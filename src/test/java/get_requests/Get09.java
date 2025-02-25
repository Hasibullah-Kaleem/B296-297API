package get_requests;

import base_url.TodoBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class Get09 extends TodoBaseUrl {
    /*
    Given
        https://jsonplaceholder.typicode.com/todos
    When
        User sends a GET request to the URL
    Then
        Status code should be 200
    And
        Print the todos with IDs greater than 190
    And
        Verify that there are 10 todos with IDs greater than 190
    And
        Print the userIds of todos with IDs less than 5
    And
        Verify that there are 4 todos with IDs less than 5
    And
        Print the titles of todos with IDs less than 5
    And
        Verify that the title "delectus aut autem" belongs to a todo with ID less than 5.
     */

    @Test
    public void test01() {
        //1 - Set the Url
        spec.pathParam("first","todos");
        //2 - Set the Payload
        //3 - Send the Request
        Response response = given(spec).get("{first}");
        //response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();
        //4 - Make Assertions
        List<Integer> idList = jsonPath.getList("id");
        System.out.println("idList = " + idList);
        System.out.println("==================================================");

        List<Integer> completedList = jsonPath.getList("completed");
        System.out.println("completedList = " + completedList);

        System.out.println("==================================================");

        List<Integer> titleList = jsonPath.getList("title");
        System.out.println("titleList = " + titleList);

        System.out.println("==================================================");

        List<Object> list = jsonPath.getList("findAll{it.id>190}");
        System.out.println("list = " + list);

        assertEquals(10,list.size());


        List<Object> list1 = jsonPath.getList("findAll{it.id<5}.userId");
        System.out.println("list1 = " + list1);

        assertEquals(4,list1.size());

        List<Object> list2 = jsonPath.getList("findAll{it.id<5}.title");
        System.out.println("list2 = " + list2);

        assertTrue(list2.contains("delectus aut autem"));

    }
}