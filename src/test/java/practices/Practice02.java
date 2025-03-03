package practices;

import base_url.RestfulBookerBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Practice02 extends RestfulBookerBaseUrl {
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

        //set url
        spec.pathParams("first","booking","second", 408);
        //set payload
        BookingDatesPojo booking = new BookingDatesPojo("2018-01-01","2019-01-01");
        RestfulPojo payLoad = new RestfulPojo("Bob","Smith",111,true,booking,"Breakfast");
        Response response = given(spec).when().get("{first}/{second}");

        RestfulPojo actualData = response.as(RestfulPojo.class);


        assertEquals(actualData.getFirstname(),payLoad.getFirstname());
        assertEquals(actualData.getLastname(),payLoad.getLastname());
        assertEquals(actualData.getTotalprice(),payLoad.getTotalprice());
        assertEquals(actualData.getDepositpaid(),payLoad.getDepositpaid());
        assertEquals(actualData.getBookingdates().getCheckin(),booking.getCheckin());
        assertEquals(actualData.getBookingdates().getCheckout(),booking.getCheckout());
        assertEquals(actualData.getAdditionalneeds(),payLoad.getAdditionalneeds());

    }
}
