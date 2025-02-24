package utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AuthorizeContactList {

    @Test
    public void test01() {
        generateToken();
    }

    /*
    Takes our credentials as the payload, sends a POST request to the url with that payload.
    Extracts the token from the response, and returns it with a Bearer prefix for outer usages.
   */

    public static String generateToken(){


        //1- Set the url
        String url = "https://thinking-tester-contact-list.herokuapp.com/users/login";
        //2- Set the payload
        String payload = "{\n" +
                "    \"email\": \"tomjuan@gmail.com\",\n" +
                "    \"password\": \"12345678\"\n" +
                "}";
       //3- Send the request
        Response response = given()
                .body(payload)
                .contentType(ContentType.JSON)
                .when()
                .post(url);
       // response.prettyPrint();

        return "Bearer "+ response.jsonPath().getString("token");
    }
}
