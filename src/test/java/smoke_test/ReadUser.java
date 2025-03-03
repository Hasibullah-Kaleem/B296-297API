package smoke_test;


import base_url.UserBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pojos.UserPojo;

import static io.restassured.RestAssured.given;

public class ReadUser extends UserBaseUrl {
    /*
    Given
        https://67bde786321b883e790e6e9d.mockapi.io/users/10
    When
        The user sends a GET request to the endpoint
    Then
        The response status code should be 200
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

    @Test
    public void test01() {
        //1 - Set the URL
        spec.pathParams("first", "users", "second", CreateUser.id);
        //2 - Set the expected data
        UserPojo expectedData = new UserPojo("Bob", "Smith", "Backend Developer", "USA");
        //3 - Send the request
        Response response = given(spec).get("{first}/{second}");
        response.prettyPrint();
        //4 - Make Assertions
        UserPojo actualData = response.as(UserPojo.class);
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(response.statusCode(), 200);
        softAssert.assertEquals(actualData.getFirstname(), expectedData.getFirstname());
        softAssert.assertEquals(actualData.getLastname(), expectedData.getLastname());
        softAssert.assertEquals(actualData.getJob(), expectedData.getJob());
        softAssert.assertEquals(actualData.getCountry(), expectedData.getCountry());

        softAssert.assertAll();
    }
}