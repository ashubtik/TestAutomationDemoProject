package test.framework.definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.testng.Assert.assertTrue;

public class LoginStepDefinition extends BasicSteps {

    @Given("User opens Thinking Tester platform login page")
    public void openTestPlatform() {
        getPageManager().getLoginPage().getPlatform();
    }

    @When("User logs in with valid credentials")
    public void loginWithValidCredentials() {
        getPageManager().getLoginPage().loginToPlatform(config.getUsername(), config.getPassword());
    }

    @Given("Browser is closed")
    public void browserIsClosed() {
        getPageManager().getLoginPage().closeBrowser();
    }

    @When("User logs in with username {string} and password {string}")
    public void logInWithCredentials(String username, String password) {
        getPageManager().getLoginPage().loginToPlatform(username, password);
    }

    @Then("Incorrect username or password error is displayed")
    public void verifyErrorIsDisplayed() {
        assertTrue(getPageManager().getLoginPage().isErrorDisplayed(),
                "Error is not displayed");
    }
}
