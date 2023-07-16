package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

import static base_urls.MedunnaBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static stepdefs.MedunnaRoomCreateStepdefs.roomNumberFaker;

public class ApiRoomStepDefs {

    Response response;

    @Given("send get request")
    public void sendGetRequest() {
        //Set the Url
        spec.pathParams("first", "api", "second", "rooms").queryParams("sort", "createdDate,desc");
        //Set the expected Data
        //Send the request and get the response
        response = given(spec).get("{first}/{second}");
        response.prettyPrint();//bu bana tüm odalari verir.

    }

    @Then("validate body")
    public void validateBody() {
        Object roomType = response.jsonPath().getList("findAll{it.roomNumber=="+roomNumberFaker+"}.roomType").get(0);
        System.out.println("roomType = " + roomType);
        Object status = response.jsonPath().getList("findAll{it.roomNumber==623930}.status").get(0);
        Object price = response.jsonPath().getList("findAll{it.roomNumber==623930}.price").get(0);
        Object description = response.jsonPath().getList("findAll{it.roomNumber==623930}.description").get(0);


        assertEquals(200, response.statusCode());
        assertEquals("SUITE", roomType);
        assertEquals(true, status);
        assertEquals("123.0", price+"");
        assertEquals("Created For End To End Test", description);


        //
    }
}
