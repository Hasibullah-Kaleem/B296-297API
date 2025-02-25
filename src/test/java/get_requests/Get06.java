package get_requests;

import base_url.RestfulBookerBaseUrl;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Get06 extends RestfulBookerBaseUrl {
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
        spec.pathParams("first","booking","second", 1819);
        //2 - Set the payload
        //3 - Send the request/get the response
        //4 - Make Assertions
        given(spec)
                .when()
                .get("{first}/{second}")
                .then().statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname",equalTo("Bob"))
                .body("lastname",equalTo("Smith"))
                .body("totalprice", equalTo(111))
                .body("depositpaid", equalTo(true))
                .body("bookingdates.checkin", equalTo("2018-01-01"))
                .body("bookingdates.checkout", equalTo("2019-01-01"))
                .body("additionalneeds", equalTo("Breakfast"))
                .log().body();


    }


}
