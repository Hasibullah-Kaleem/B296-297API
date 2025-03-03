package smoke_test;

import base_url.UserBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pojos.UserPojo;

import static io.restassured.RestAssured.given;

public class DeleteUser extends UserBaseUrl {
    /*
    Given
        https://67bde786321b883e790e6e9d.mockapi.io/users/10
    When
        The user sends a DELETE request to the endpoint
    Then
        The response status code should be 200
    And
        Response body should be:
        {
            "firstname": "Jack",
            "lastname": "Jackson",
            "job": "QA Tester",
            "country": "Germany",
            "id": "10"
        }
     */

    @Test
    public void test01() {
        //1 - Set the URL
        spec.pathParams("first", "users", "second", CreateUser.id);
        //2 - Set the expected data
        UserPojo expectedData = new UserPojo("Jack", "Jackson", "QA Tester", "Germany");
        //3 - Send the request
        Response response = given(spec).delete("{first}/{second}");
        response.prettyPrint();
        //4 - Make Assertions
        UserPojo actualData = response.as(UserPojo.class);
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(response.statusCode(), 200, "Status code does not match the expected value.");
        softAssert.assertEquals(actualData.getFirstname(), expectedData.getFirstname(), "First name does not match " +
                "the expected value.");
        softAssert.assertEquals(actualData.getLastname(), expectedData.getLastname(), "Last name does not match the " +
                "expected value.");
        softAssert.assertEquals(actualData.getJob(), expectedData.getJob(), "Job does not match the expected value.");
        softAssert.assertEquals(actualData.getCountry(), expectedData.getCountry(), "Country does not match the " +
                "expected value.");

        softAssert.assertAll();

    }
}