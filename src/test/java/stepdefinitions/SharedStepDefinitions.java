package stepdefinitions;

import actions.post.GetPostActions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import models.PostModel;
import utils.ApplicationConfiguration;
import utils.ApplicationConfigurationLoader;
import utils.Context;

import static org.assertj.core.api.Assertions.assertThat;

public class SharedStepDefinitions {

    private Context context;
    private GetPostActions getPostActions = new GetPostActions();

    protected static ApplicationConfiguration appConfig = ApplicationConfigurationLoader.getConfig();

    public SharedStepDefinitions(Context context) {
        this.context = context;
    }

    @When("User requests to see a post with id {int}")
    @Given("User has an existing post {int}")
    public void user_requests_to_see_a_post_with_id(Integer id) {
        PostModel post = getPostActions.getPost(appConfig.postsUrl(), "/" + id);
        context.setActualPost(post);
    }

    @Then("User gets {int} error")
    public void user_gets_error(int error) {
        int responseCode = context.getResponseCode();

        assertThat(responseCode)
                .withFailMessage(String.format("Error %s was returned instead of expected %s", responseCode, error))
                .isEqualTo(error);
    }
}
