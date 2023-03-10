package stepdefinitions;

import actions.comment.GetCommentActions;
import helpers.StringHelper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.CommentModel;
import org.assertj.core.api.SoftAssertions;
import utils.ApplicationConfiguration;
import utils.ApplicationConfigurationLoader;
import utils.Context;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.StringConstants.*;

public class GetCommentStepDefinitions {

    protected static ApplicationConfiguration appConfig = ApplicationConfigurationLoader.getConfig();
    private Context context;
    private GetCommentActions getCommentActions = new GetCommentActions();

    public GetCommentStepDefinitions(Context context) {
        this.context = context;
    }

    @When("User requests to see all comments")
    public void user_requests_all_comments() {
        List<CommentModel> comments = getCommentActions.sendCommentsRequest();
        context.setComments(comments);
    }

    @When("User requests to see all comments for a post with id {int}")
    public void userRequestsToSeeAllCommentsForAPostWithId(int postId) {
        List<CommentModel> comments = getCommentActions.getCommentsForPostRequest(postId);
        context.setComments(comments);
    }

    @When("User requests to see a comment with id {int}")
    public void user_requests_to_see_a_comment_with_id(int postId) {
        CommentModel actualComment = getCommentActions.sendCommentRequest(postId);
        context.setActualComment(actualComment);
        context.setPostId(postId);
    }

    @When("User requests to see a comment with invalid {string}")
    public void user_requests_all_posts_with_invalid_userId(String commentId) {
        int responseCode = getCommentActions.sendUnsuccessfulRequest(appConfig.commentsUrl(), commentId);
        context.setResponseCode(responseCode);
    }

    @Then("User gets a list of all comments")
    public void user_gets_all_comments() {
        List<CommentModel> comments = context.getComments();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(comments).isNotEmpty();
        softly.assertThat(comments).size().isGreaterThanOrEqualTo(500);
        softly.assertThat(comments).allSatisfy(commentModel -> {
            assertThat(commentModel.getId()).isPositive();
            assertThat(commentModel.getPostId()).isPositive();
            assertThat(commentModel.getBody()).isNotEmpty();
            assertThat(commentModel.getName()).isNotEmpty();
            assertThat(commentModel.getEmail()).isNotEmpty();
        });
        softly.assertAll();
    }

    @Then("User gets a valid response for requested comment")
    public void user_gets_valid_response_for_comment() {
        CommentModel expectedComment = CommentModel.builder()
                .body(COMMENTBODY)
                .email(EMAIL)
                .name(NAME)
                .postId(1)
                .id(context.getActualComment().getId()).build();

        CommentModel actualComment = context.getActualComment();
        actualComment.setBody(StringHelper.removeNewLinesFromBody(actualComment.getBody()));
        assertThat(actualComment).isEqualTo(expectedComment);
    }

    @Then("User gets a valid response with all comments")
    public void userGetsAValidResponseWithAllComments() {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(context.getComments()).allSatisfy(commentModel -> {
            assertThat(commentModel.getPostId()).isEqualTo(context.getPostId());
            assertThat(commentModel.getId()).isPositive();
            assertThat(commentModel.getBody()).isNotEmpty();
            assertThat(commentModel.getName()).isNotEmpty();
            assertThat(commentModel.getEmail()).isNotEmpty();
        });
    }
}
