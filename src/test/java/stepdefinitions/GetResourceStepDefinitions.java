package stepdefinitions;

import actions.PostActions;
import helpers.StringHelper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.PostModel;
import org.assertj.core.api.SoftAssertions;
import utils.ApplicationConfiguration;
import utils.ApplicationConfigurationLoader;
import utils.Context;

import java.util.ArrayList;
import java.util.List;

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

    @When("User requests to see a post with id {int}")
    public void user_requests_to_see_a_post_with_id(Integer id) {
        context.setPost(postActions.sendPostRequest(id));
    }

    @When("User requests to see all posts")
    public void user_requests_all_posts() {
        List<PostModel> posts = postActions.sendPostsRequest();
        context.setPosts(posts);
    }

    @When("User requests to see a post with invalid {string}")
    public void userRequestsToSeeAPostWithInvalidId(String id) {
        context.setResponseCode(postActions.sendUnsuccessfulPostRequest(id));
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
    }

    @Then("User gets {int} error")
    public void user_gets_error(int error) {
        assertThat(context.getResponseCode()).isEqualTo(error);
    }


    @Then("User gets a list of all posts")
    public void user_gets__all_posts() {
        List<PostModel> posts = context.getPosts();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(posts).isNotEmpty();
        softly.assertThat(posts).size().isGreaterThanOrEqualTo(99);
        softly.assertThat(posts).allSatisfy(postModel -> {
            assertThat(postModel.getUserId()).isPositive();
            assertThat(postModel.getId()).isPositive();
            assertThat(postModel.getBody()).isNotEmpty();
            assertThat(postModel.getTitle()).isNotEmpty();
        });
        softly.assertAll();
    }
}
