package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pojos.RoomPojo;

import java.sql.*;

import static org.junit.Assert.assertEquals;
import static stepdefs.MedunnaRoomCreateStepdefs.roomNumberFaker;

public class DataBaseRoomStepdefs {

    Connection connection;
    Statement statement;

    @Given("connect to Database")
    public void connectToDatabase() throws SQLException {
        //1.Adim ; Connection olustur
        connection = DriverManager.getConnection("jdbc:postgresql://medunna.com:5432/medunna_db_v2", "select_user", "Medunna_pass_@6");
        //2.Adim ; Statement olustur
        statement = connection.createStatement();


    }

    @Then("read room and validate")
    public void readRoomAndValidate() throws SQLException {

        RoomPojo expectedData = new RoomPojo(roomNumberFaker, "SUITE", true, 123.00, "Created For End To End Test");

        // 3.Adim ; query calistir
        String sqlQuery = "Select * from room where room_number = " + roomNumberFaker;
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        resultSet.next();


        String roomType = resultSet.getString("room_type");
        Boolean status = resultSet.getBoolean("status");
        Double price = resultSet.getDouble("price");
        String description = resultSet.getString("description");
        Integer roomNumber = resultSet.getInt("room_number");

        assertEquals(expectedData.getRoomType(), roomType);
        assertEquals(expectedData.getStatus(), status);
        assertEquals(expectedData.getPrice(), price);
        assertEquals(expectedData.getDescription(), description);
        assertEquals(expectedData.getRoomNumber(), roomNumber);

    }
}
