package stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pages.MedunnaHomePage;
import utilities.Driver;

public class MedunnaSignInStepdefs {

    MedunnaHomePage medunnaHomePage = new MedunnaHomePage();
    @Given("Go to {string}")
    public void goTo(String url) {
        Driver.getDriver().get(url);
    }

    @When("click on user icon")
    public void clickOnUserIcon() {
        medunnaHomePage.userIcon.click();

    }

    @And("click on sign in option")
    public void clickOnSignInOption() {
        medunnaHomePage.signInOption.click();
    }

    @And("enter username")
    public void enterUsername() {

    }

    @And("enter password")
    public void enterPassword() {
    }

    @And("click on sign in submit  button")
    public void clickOnSignInSubmitButton() {
    }


}
