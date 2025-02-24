package get_request;

import base_url.ContactListBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Get08 extends ContactListBaseUrl {

     /*
    Given
        https://thinking-tester-contact-list.herokuapp.com/contacts
    When
        User sends a GET request to the URL
    Then
        HTTP Status code should be 200
    And
        Content Type should be "application/json"
     */

    @Test
    public void test01() {
        //1 - Set the URL
        spec.pathParam("path", "contacts");
        //2 - Set the payload
        //no payload
        //3 - Send the request
        Response response = given(spec)
                .when().get("{path}");
        response.prettyPrint();
    }

}
