package post_request;


import base_url.RestfulBookerBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pojos.RestfulBookerBookingDates;
import pojos.RestfulBookerPayload;
import pojos.RestfulBookerPostResponse;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class Post06 extends RestfulBookerBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking
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
    When
        User sends a POST request to the URL
    Then
        Status code should be 200
    And
        Response body should be this:
    {
        "bookingid": 823,
        "booking": {
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
    }
    */

    @Test
    public void test01() {
        //1 - Set the URL
        spec.pathParam("first","booking");
        //2 - Set the payload
        RestfulBookerBookingDates bookingDates = new RestfulBookerBookingDates("2018-01-01", "2019-01-01");
        RestfulBookerPayload payload = new RestfulBookerPayload("Bob", "Smith", 111, true, bookingDates, "Breakfast");
        //3 - Send the request / get the response
        Response response = given(spec).body(payload).when().post("{first}");
        //4 - Make Assertions
        RestfulBookerPostResponse actualData = response.as(RestfulBookerPostResponse.class);

        assertEquals(response.statusCode(), 200);

        assertEquals(actualData.getBooking().getFirstname(), payload.getFirstname());
        assertEquals(actualData.getBooking().getLastname(), payload.getLastname());
        assertEquals(actualData.getBooking().getTotalprice(), payload.getTotalprice());
        assertEquals(actualData.getBooking().getDepositpaid(), payload.getDepositpaid());
        assertEquals(actualData.getBooking().getAdditionalneeds(), payload.getAdditionalneeds());
        assertEquals(actualData.getBooking().getBookingdates().getCheckin(), bookingDates.getCheckin());
        assertEquals(actualData.getBooking().getBookingdates().getCheckout(), bookingDates.getCheckout());
    }

    @Test
    public void testSoftAssertion() {
        //1 - Set the URL
        spec.pathParam("first","booking");
        //2 - Set the payload
        RestfulBookerBookingDates bookingDates = new RestfulBookerBookingDates("2018-01-01", "2019-01-01");
        RestfulBookerPayload payload = new RestfulBookerPayload("Bob", "Smith", 111, true, bookingDates, "Breakfast");
        //3 - Send the request / get the response
        Response response = given(spec).body(payload).when().post("{first}");
        //4 - Make Assertions
        RestfulBookerPostResponse actualData = response.as(RestfulBookerPostResponse.class);

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(response.statusCode(), 200);

        softAssert.assertEquals(actualData.getBooking().getFirstname(), payload.getFirstname());
        softAssert.assertEquals(actualData.getBooking().getLastname(), payload.getLastname());
        softAssert.assertEquals(actualData.getBooking().getTotalprice(), payload.getTotalprice());
        softAssert.assertEquals(actualData.getBooking().getDepositpaid(), payload.getDepositpaid());
        softAssert.assertEquals(actualData.getBooking().getAdditionalneeds(), payload.getAdditionalneeds());
        softAssert.assertEquals(actualData.getBooking().getBookingdates().getCheckin(), bookingDates.getCheckin());
        softAssert.assertEquals(actualData.getBooking().getBookingdates().getCheckout(), bookingDates.getCheckout());

        softAssert.assertAll();
    }
}