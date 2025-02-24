package get_request;

import base_url.RestfulBookerBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class Get07 extends RestfulBookerBaseUrl {
     /*
    Given
    https://restful-booker.herokuapp.com/booking/467
    When
        User sends a GET request to the URL
    Then
        HTTP Status Code should be 200
    And
        Content type should be "application/json"
    And
        Response body should be like this:
    {
        "firstname": "Jim",
        "lastname": "Brown",
        "totalprice": 111,
        "depositpaid": true,
        "bookingdates": {
            "checkin": "2018-01-01",
            "checkout": "2019-01-01"
        },
        "additionalneeds": "Breakfast"
    }
    */

    @Test
    public void test01(){

        //1 - Set the URL
        spec.pathParams("first","booking","second", 2100 );
        //2 - Set the payload
        //3 - Send the request/get the response
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();
        JsonPath jsonPath = response.jsonPath();

        assertEquals(jsonPath.getString("firstname"),"Bob");
        assertEquals(jsonPath.getString("firstname"),"Bob");
        assertEquals(jsonPath.getString("lastname"),"Smith");
        assertEquals(jsonPath.getInt("totalprice"),111);
        assertTrue(jsonPath.getBoolean("depositpaid"));
        assertEquals(jsonPath.getString("bookingdates.checkin"),"2018-01-01");
        assertEquals(jsonPath.getString("bookingdates.checkout"),"2019-01-01");
        assertEquals(jsonPath.getString("additionalneeds"),"Breakfast");
//        String firstname = jsonPath.getString("firstname");
//        System.out.println("firstname = " + firstname);
//
//        Boolean depositpaid = jsonPath.getBoolean("depositpaid");
//        System.out.println("depositpaid = " + depositpaid);
        //4 - Make Assertions


    }
}
