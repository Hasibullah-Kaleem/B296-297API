package smoke_test;

import base_url.UserBaseUrl;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pojos.UserPojo;

import static io.restassured.RestAssured.given;

public class UpdateUser extends UserBaseUrl {
    /*
    Given
        https://67bde786321b883e790e6e9d.mockapi.io/users/1
    And
        {
            "firstname": "Jack",
            "lastname": "Jackson",
            "job": "QA Tester",
            "country": "Germany"
        }
    When
        The user sends a PUT request to the endpoint
    Then
        The response status code should be 200
    And
        Response body should be:
        {
            "firstname": "Jack",
            "lastname": "Jackson",
            "job": "QA Tester",
            "country": "Germany",
            "id": "1"
        }
     */

    @Test
    public void test01() {
        //1 - Set the URL
        spec.pathParams("first", "users", "second", CreateUser.id);
        //2 - Set the payload
        UserPojo payload = new UserPojo("Jack", "Jackson", "QA Tester", "Germany");
        //3 - Send the request
        Response response = given(spec).body(payload).when().put("{first}/{second}");
        response.prettyPrint();
        //4 - Make Assertions
        UserPojo actualData = response.as(UserPojo.class);
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(response.statusCode(), 200);
        softAssert.assertEquals(actualData.getFirstname(), payload.getFirstname());
        softAssert.assertEquals(actualData.getLastname(), payload.getLastname());
        softAssert.assertEquals(actualData.getJob(), payload.getJob());
        softAssert.assertEquals(actualData.getCountry(), payload.getCountry());

        softAssert.assertEquals(response.jsonPath().getString("id"), CreateUser.id);
        //If we had a UserResponsePojo
        //softAssert.assertEquals(UserResponsePojo.getId(), CreateUser.id);
        softAssert.assertAll();
    }
}