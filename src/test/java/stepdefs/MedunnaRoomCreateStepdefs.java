package stepdefs;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.Select;
import pages.MedunnaHomePage;
import pages.MedunnaRoomPage;
import utilities.Driver;

public class MedunnaRoomCreateStepdefs {

    MedunnaHomePage medunnaHomePage = new MedunnaHomePage();
    MedunnaRoomPage medunnaRoomPage = new MedunnaRoomPage();

    //bir ariable nin üstüne gelip altinda cizgi cikinca control basili
    // tiklarsan nerelerde kullanildigini görürsün

    public static int roomNumberFaker;

    public static String roomId;//diger classlardan erisebilmek icin class levelinda static yaptik

    @When("Click on ItemsAndTitles")
    public void clickOnItemsAndTitles() {
        medunnaHomePage.itemsdAndTitles.click();


    }

    @And("click on Room option")
    public void clickOnRoomOption() {
        medunnaHomePage.roomOption.click();
    }


    @And("click on Create a new room button")
    public void clickOnCreateANewRoomButton() {
        medunnaRoomPage.createANewRoomButton.click();
    }

    @And("enter {string} room number input")
    public void enterRoomNumberInput(String roomNumber) {
        roomNumberFaker = Faker.instance().number().numberBetween(100000, 1000000);
        medunnaRoomPage.roomNumberInput.sendKeys(roomNumberFaker + "");
        //send keys int kabul etmedigi icin roomnumberfaker kirmizi yandi bununn icin concatination ile
        //onu string yaptik


    }

    @And("select Suite option from Room Type dropdown")
    public void selectSuiteOptionFromRoomTypeDropdown() {
        new Select(medunnaRoomPage.roomTypeDropDown).selectByIndex(3);
        //select objesinin icine web element yazilir.
    }

    @And("click on Status checkbox")
    public void clickOnStatusCheckbox() {
        medunnaRoomPage.statusCheckbox.click();
    }

    @And("enter {string} in Price input")
    public void enterInPriceInput(String price) {
        medunnaRoomPage.priceInput.sendKeys(price);
    }

    @And("enter {string} in Description input")
    public void enterInDescriptionInput(String description) {
        medunnaRoomPage.descriptionInput.sendKeys(description);
    }

    @And("click on Save button")
    public void clickOnSaveButton() throws InterruptedException {
        medunnaRoomPage.saveSubmitButton.click();
        Thread.sleep(2000);
        roomId = medunnaRoomPage.alert.getText().replaceAll("[^0-9]","");
        System.out.println("roomId = " + roomId);

    }

    @And("close the application")
    public void closeTheApplication() {
        Driver.quitDriver();
    }
}
