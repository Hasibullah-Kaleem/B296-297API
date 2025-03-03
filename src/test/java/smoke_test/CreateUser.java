package smoke_test;

import base_url.UserBaseUrl;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pojos.UserPojo;

import static io.restassured.RestAssured.given;

public class CreateUser extends UserBaseUrl {
    /*
    Given
        https://67bde786321b883e790e6e9d.mockapi.io/users
    And
        {
            "firstname": "Bob",
            "lastname": "Smith",
            "job": "Backend Developer",
            "country": "USA"
        }
    When
        The user sends a POST request to the endpoint
    Then
        The response status code should be 201
    And
        Response body should be:
        {
            "firstname": "Bob",
            "lastname": "Smith",
            "job": "Backend Developer",
            "country": "USA",
            "id": "1"
        }
     */

    /*
    This test is for a POST request, meaning, we create a user. The response has an id field
    for the object we created. We used JsonPath to extract that id, and assigned it to a static
    field, meaning, we can share the id to other classes (requests). So we can apply the CRUD
    flow, just like we had in Postman. We will not leave any test traces, any trash behind.
     */

    static String id;

    @Test
    public void test01() {
        //1 - Set the URL
        spec.pathParam("first", "users");
        //2 - Set the payload
        UserPojo payload = new UserPojo("Bob", "Smith", "Backend Developer", "USA");
        //3 - Send the request
        Response response = given(spec).body(payload).when().post("{first}");
        response.prettyPrint();
        //4 - Make Assertions
        UserPojo actualData = response.as(UserPojo.class);
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(response.statusCode(), 201, "Status code does not match the expected value.");
        softAssert.assertEquals(actualData.getFirstname(), payload.getFirstname(), "First name does not match the " +
                "expected value.");
        softAssert.assertEquals(actualData.getLastname(), payload.getLastname(), "Last name does not match the " +
                "expected value.");
        softAssert.assertEquals(actualData.getJob(), payload.getJob(), "Job does not match the expected value.");
        softAssert.assertEquals(actualData.getCountry(), payload.getCountry(), "Country does not match the expected " +
                "value.");

        softAssert.assertAll();

        id = response.jsonPath().getString("id");
    }
}