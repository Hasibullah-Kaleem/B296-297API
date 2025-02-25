package get_request;

import base_url.RestfulBookerBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.RestfulBookerBookingDates;
import pojos.RestfulBookerPayload;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class Get11 extends RestfulBookerBaseUrl {
    /*
    Given
    https://restful-booker.herokuapp.com/booking/2634
    When
        User sends a GET request to the URL
    Then
        Response body should be like this:
    {
        "firstname": "Bob",
        "lastname": "Smith",
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
    public void test01() {
        //1 - Set the URL
        spec.pathParams("first","booking","second", 2634);
        //2 - Set the payload / expected data
        RestfulBookerBookingDates bookingDates = new RestfulBookerBookingDates("2018-01-01", "2019-01-01");
        RestfulBookerPayload expectedData = new RestfulBookerPayload("Bob", "Smith", 111, true, bookingDates, "Breakfast");
        //3 - Send the request
        Response response = given(spec).when().get("{first}/{second}");
        //4 - Make Assertions
        RestfulBookerPayload actualData = response.as(RestfulBookerPayload.class);

        assertEquals(actualData.getFirstname(), expectedData.getFirstname());
        assertEquals(actualData.getLastname(), expectedData.getLastname());
        assertEquals(actualData.getTotalprice(), expectedData.getTotalprice());
        assertEquals(actualData.getDepositpaid(), expectedData.getDepositpaid());
        assertEquals(actualData.getAdditionalneeds(), expectedData.getAdditionalneeds());
        assertEquals(actualData.getBookingdates().getCheckin(), bookingDates.getCheckin());
        assertEquals(actualData.getBookingdates().getCheckout(), bookingDates.getCheckout());
    }
}