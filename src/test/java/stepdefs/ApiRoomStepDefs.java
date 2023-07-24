package stepdefs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import pojos.RoomPojo;

import static base_urls.MedunnaBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static stepdefs.MedunnaRoomCreateStepdefs.roomId;
import static stepdefs.MedunnaRoomCreateStepdefs.roomNumberFaker;

public class ApiRoomStepDefs {

    Response response;
    RoomPojo expectedData;

    @Given("send get request")
    public void sendGetRequest() {
        //Set the Url
        spec.pathParams("first", "api", "second", "rooms").queryParams("sort", "createdDate,desc");
        //Set the expected Data
        //Send the request and get the response
        response = given(spec).get("{first}/{second}");
        //response.prettyPrint();//bu bana tüm odalari verir.

    }

    @Then("validate body")
    public void validateBody() {
        //Grovy ile kendi olusturdugumuz roomNumber' i kullanarak olusturdugumuz odayi filtreliyoruz
        Object roomType = response.jsonPath().getList("findAll{it.roomNumber==" + roomNumberFaker + "}.roomType").get(0);
        System.out.println("roomType = " + roomType);
        Object status = response.jsonPath().getList("findAll{it.roomNumber==" + roomNumberFaker + "}.status").get(0);
        Object price = response.jsonPath().getList("findAll{it.roomNumber==" + roomNumberFaker + "}.price").get(0);
        Object description = response.jsonPath().getList("findAll{it.roomNumber==" + roomNumberFaker + "}.description").get(0);
        Object roomNumber = response.jsonPath().getList("findAll{it.roomNumber==" + roomNumberFaker + "}.roomNumber").get(0);


        assertEquals(200, response.statusCode());
        assertEquals("SUITE", roomType);
        assertEquals(true, status);
        assertEquals("123.0", price + "");//***burda basta degerler float double iken hoca
        // ikisinide string yapti
        assertEquals("Created For End To End Test", description);
        assertEquals(roomNumberFaker, roomNumber);


        //
    }

    @Given("send get request by id")
    public void sendGetRequestById() {
        //Set the Url -->   https://medunna.com/api/rooms/:id
        spec.pathParams("first", "api", "second", "rooms", "third", roomId);
        //Set the expected Data
        expectedData = new RoomPojo(roomNumberFaker, "SUITE", true, 123.00, "Created For End To End Test");
        //Send the request and get the response
        response = given(spec).get("{first}/{second}/{third}");
        //response.prettyPrint();

    }

    @Then("validate response body")
    public void validateResponseBody() throws JsonProcessingException {
        RoomPojo actualData = new ObjectMapper().readValue(response.asString(), RoomPojo.class);
        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getRoomNumber(), actualData.getRoomNumber());
        assertEquals(expectedData.getPrice(), actualData.getPrice());
        assertEquals(expectedData.getStatus(), actualData.getStatus());
        assertEquals(expectedData.getDescription(), actualData.getDescription());
        assertEquals(expectedData.getRoomType(), actualData.getRoomType());
    }


}
