package stepdefinitions;

import actions.GetActions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.Context;

import static org.assertj.core.api.Assertions.assertThat;

public class SharedStepDefinitions {

    private Context context;
    private GetActions getActions = new GetActions();

    public SharedStepDefinitions(Context context) {
        this.context = context;
    }

    @When("User requests to see a post with id {int}")
    @Given("User has an existing post {int}")
    public void user_requests_to_see_a_post_with_id(Integer id) {
        context.setActualPost(getActions.sendPostRequest(id));
    }

    @Then("User gets {int} error")
    public void user_gets_error(int error) {
        assertThat(context.getResponseCode())
                .withFailMessage(String.format("Error %s was returned instead of expected %s", context.getResponseCode(), error))
                .isEqualTo(error);
    }
}
