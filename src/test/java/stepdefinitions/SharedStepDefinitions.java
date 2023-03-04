package stepdefinitions;

import actions.post.GetPostActions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.PostModel;
import utils.Context;

import static org.assertj.core.api.Assertions.assertThat;

public class SharedStepDefinitions {

    private Context context;
    private GetPostActions getPostActions = new GetPostActions();

    public SharedStepDefinitions(Context context) {
        this.context = context;
    }

    @When("User requests to see a post with id {int}")
    @Given("User has an existing post {int}")
    public void user_requests_to_see_a_post_with_id(Integer id) {
        PostModel actualPost = getPostActions.sendPostRequest(id);
        context.setActualPost(actualPost);
    }

    @Then("User gets {int} error")
    public void user_gets_error(int error) {
        int responseCode = context.getResponseCode();

        assertThat(responseCode)
                .withFailMessage(String.format("Error %s was returned instead of expected %s", responseCode, error))
                .isEqualTo(error);
    }
}
