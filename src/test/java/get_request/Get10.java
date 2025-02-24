package get_request;

import base_url.RestfulBookerBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class Get10 extends RestfulBookerBaseUrl {
    /*
    Given
    https://restful-booker.herokuapp.com/booking/945
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
        "additionalneeds": "Dinner"
    }
    */

    @Test
    public void test01() {
        //1 - Set the URL
        spec.pathParams("first", "booking", "second", 2124);
        //2 - Set the payload / expected data
        Map<String, Object> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2018-01-01");
        bookingDates.put("checkout", "2019-01-01");

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", "Bob");
        expectedData.put("lastname", "Smith");
        expectedData.put("totalprice", 111);
        expectedData.put("depositpaid", true);
        expectedData.put("bookingdates", bookingDates);
        expectedData.put("additionalneeds", "Dinner");

        System.out.println("expectedData = " + expectedData);
        //3 - Send the request
        Response response = given(spec).get("{first}/{second}");
        //4 - Make Assertions
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        /*
        We have to convert our nested json objects to maps again. We need this to use "get" method of Maps.
        Since get method belongs to the Map interface, we have to cast object to maps.
         */

        assertEquals(actualData.get("firstname"),expectedData.get("firstname"));
        assertEquals(actualData.get("lastname"),expectedData.get("lastname"));
        assertEquals(actualData.get("totalprice"),expectedData.get("totalprice"));
        assertEquals(actualData.get("depositpaid"),expectedData.get("depositpaid"));
        assertEquals(actualData.get("additionalneeds"),expectedData.get("additionalneeds"));

        //First way - Validation by Map ==> NOT RECOMMENDED, UNREADABLE
        assertEquals(((Map<String, Object>) actualData.get("bookingdates")).get("checkin"),bookingDates.get("checkin"));
        assertEquals(((Map<String, Object>) actualData.get("bookingdates")).get("checkout"),bookingDates.get("checkout"));
        //Map<String, Object> object = actualData.get("bookingdates");


        //Second way - JSONPath way, RECOMMENDED, READABLE, UNDERSTANDABLE
        JsonPath jsonPath = response.jsonPath();
        assertEquals(jsonPath.getString("bookingdates.checkin"), bookingDates.get("checkin"));
        assertEquals(jsonPath.get("bookingdates.checkout"), bookingDates.get("checkout"));
    }
}