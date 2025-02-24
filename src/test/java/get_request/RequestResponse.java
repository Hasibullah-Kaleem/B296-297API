package get_request;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RequestResponse {

    /*
        1) We will use the Postman for manual testing.
        2) We will use the Rest Assured library for automation tests.
        3) Rest Assured makes use of the Gherkin Language for its predefined methods.
              - Given	: Context, preconditions.
              - When	: Actions, get(), post() etc.
              - Then	: Assertions, claims.
              - And     : Can be used for "multiple" situations.

              1 - Set URL:
                    Prepare the endpoint URL with base url + path variables + query parameters if they exist

              2 - Set the payload / expected data:
                  Provide the payload in the request (Information we want to send).

              3 - Send the request and get the response.

              4 - Make assertions / claims:
                  Make some tests on the response by some assertions, some claims.

        */
    @Test
    public void test01(){


        //  1 - Set URL: https://petstore.swagger.io/v2/pet/306
        String endPoint = "https://petstore.swagger.io/v2/pet/550";
        //  2 - Set the payload / expected data:
        //  3 - Send the request and get the response.
        Response response = given().when().get(endPoint);
        response.print(); // Prints the response body in a single line
        response.prettyPrint(); // Prints the response body in JSON-style.
        System.out.println("Status Code = " + response.statusCode());
        System.out.println("Status Line = " + response.statusLine());
        System.out.println("Content Type = " + response.contentType());
        System.out.println("All headers() = \n " + response.headers());
        System.out.println("Time = " + response.time());
        //  4 - Make assertions / claims:



    }

}
