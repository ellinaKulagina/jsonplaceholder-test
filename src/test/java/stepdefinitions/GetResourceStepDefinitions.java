package stepdefinitions;

import actions.PostActions;
import helpers.StringHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import models.PostModel;
import utils.ApplicationConfiguration;
import utils.ApplicationConfigurationLoader;
import utils.Context;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.StringConstants.POSTBODY;
import static utils.StringConstants.POSTTITLE;

public class GetResourceStepDefinitions {

    protected static ApplicationConfiguration localApplicationConfiguration = ApplicationConfigurationLoader.getConfig();
    private Context context;

    public GetResourceStepDefinitions(Context context) {
        this.context = context;
    }

    private PostActions postActions = new PostActions();

    @Given("User requests to see a post with id {int}")
    public void user_requests_to_see_a_post_with_id(Integer id) {
        System.out.println("step 1 " + id);
        context.setPost(postActions.sendPostRequest(id));
    }

    @Then("User gets a valid response for requested post")
    public void user_gets_valid_response() {
        PostModel expectedPost = PostModel.builder()
                .body(POSTBODY)
                .title(POSTTITLE)
                .userId(1)
                .id(context.getPost().getId()).build();

        PostModel actualPost = StringHelper.removeNewLinesFromBody(context.getPost());
        assertThat(actualPost).isEqualTo(expectedPost);

        System.out.println("step 2");
    }
}
