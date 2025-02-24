package base_url;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

import static utilities.AuthorizeContactList.generateToken;

public class ContactListBaseUrl {

    /*
    Our purpose with this class is increasing the maintainability of our tests by configuring common
     test parameters such as base url and content-type etc. in a central configuration class so we can
      run the configurations before every test, allowing us to use common parameters.

     */
    protected RequestSpecification spec;
    @BeforeMethod
    public void setUp(){
        spec= new RequestSpecBuilder()
                .setBaseUri("https://thinking-tester-contact-list.herokuapp.com")
                .addHeader("Authorization",generateToken())
                .setContentType(ContentType.JSON)
                .build();
    }
}
