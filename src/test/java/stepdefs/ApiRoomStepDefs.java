package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;

import static base_urls.MedunnaBaseUrl.spec;
import static io.restassured.RestAssured.given;

public class ApiRoomStepDefs {
    @Given("send get request")
    public void sendGetRequest() {
        //Set the Url
        spec.pathParams("first","api","second","rooms").queryParams("sort","createdDate,desc");
        //Set the expected Data
        //Send the request and get the response
        Response response = given(spec).get("{first}/{second}");
        response.prettyPrint();//bu bana tüm odalari verir.




    }

    @Then("validate body")
    public void validateBody() {
        //
    }
}
