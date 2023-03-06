package stepdefinitions;

import actions.post.GetPostActions;
import helpers.StringHelper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.PostModel;
import org.assertj.core.api.SoftAssertions;
import utils.ApplicationConfiguration;
import utils.ApplicationConfigurationLoader;
import utils.Context;

import java.util.List;

import static helpers.StringHelper.urlEncode;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.StringConstants.POSTBODY;
import static utils.StringConstants.POSTTITLE;

public class GetPostStepDefinitions {

    protected static ApplicationConfiguration appConfig = ApplicationConfigurationLoader.getConfig();
    private Context context;
    private GetPostActions getPostActions = new GetPostActions();


    public GetPostStepDefinitions(Context context) {
        this.context = context;
    }

    @When("User requests to see all posts")
    public void user_requests_all_posts() {
        List<PostModel> posts = getPostActions.getListOfPosts(appConfig.postsUrl(), "");
        context.setPosts(posts);
    }

    @When("User requests to see the post with invalid id {string}")
    public void user_requests_post_with_invalid_id(String id) {
        int statusCode = getPostActions.sendUnsuccessfulRequest(appConfig.postsUrl(), id);
        context.setResponseCode(statusCode);
    }

    @When("User requests to see all posts for user {string}")
    public void user_requests_all_posts_with_userId(String userId) {
        List<PostModel> posts = getPostActions.getListOfPosts(appConfig.userPostUrl(), userId);
        context.setPosts(posts);
        context.setUserId(Integer.parseInt(userId));
    }

    @When("User requests to see all posts for a user with invalid {string}")
    public void user_requests_all_posts_with_invalid_userId(String userId) {
        List<PostModel> posts = getPostActions.getListOfPosts(appConfig.userPostUrl(), urlEncode(userId));
        context.setPosts(posts);
    }

    @Then("User gets a valid response for requested post")
    public void user_gets_valid_response() {
        PostModel expectedPost = PostModel.builder()
                .body(POSTBODY)
                .title(POSTTITLE)
                .userId(1)
                .id(context.getActualPost().getId()).build();

        PostModel actualPost = context.getActualPost();
        actualPost.setBody(StringHelper.removeNewLinesFromBody(actualPost.getBody()));
        assertThat(actualPost).isEqualTo(expectedPost);
    }


    @Then("User gets a list of all posts")
    public void user_gets_all_posts() {
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

    @Then("User gets a list of posts for the specified user")
    public void user_gets_posts_for_specified_user() {
        List<PostModel> posts = context.getPosts();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(posts).isNotEmpty();
        softly.assertThat(posts).allSatisfy(postModel -> assertThat(postModel.getUserId()).isEqualTo(context.getUserId()));
        softly.assertAll();
    }

    @Then("User gets empty response")
    public void user_gets_emptyResponse() {
        assertThat(context.getPosts()).size().isEqualTo(0);
    }

}
