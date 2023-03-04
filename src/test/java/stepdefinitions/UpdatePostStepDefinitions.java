package stepdefinitions;

import actions.post.UpdatePostActions;
import helpers.StringHelper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.internal.com.google.gson.JsonObject;
import models.PostModel;
import org.assertj.core.api.SoftAssertions;
import utils.Context;

import static org.assertj.core.api.Assertions.assertThat;

public class UpdatePostStepDefinitions {

    private Context context;
    private UpdatePostActions updatePostActions = new UpdatePostActions();

    public UpdatePostStepDefinitions(Context context) {
        this.context = context;
    }

    @When("User sends updates to the post")
    public void user_updates_post(DataTable table) {
        PostModel updatedPostModel = PostModel.builder()
                .body(table.asList().get(4))
                .title(table.asList().get(5))
                .userId(Integer.parseInt(table.asList().get(6)))
                .id(Integer.parseInt(table.asList().get(7)))
                .build();

        PostModel updatedPost = updatePostActions.updatePost(updatedPostModel);

        context.setPost(updatedPost);
        context.setActualPost(updatedPostModel);
    }

    @When("User sends partial update to the post for {string}")
    public void user_sends_partial_update(String field) {
        JsonObject requestParams = new JsonObject();

        switch (field) {
            case "body" -> requestParams.addProperty("body", "updated body");
            case "title" -> requestParams.addProperty("title", "updated title");
            default -> System.out.printf("Oops! Wrong field type %s%n", field);
        }
        ;

        int actualPostId = context.getActualPost().getId();
        PostModel partiallyUpdatedPost = updatePostActions.partiallyUpdatePost(requestParams, actualPostId);

        context.setPartialUpdate(field);
        context.setPost(partiallyUpdatedPost);
    }

    @Then("User gets an updated post with the correct details")
    public void user_gets_updated_post() {
        assertThat(context.getActualPost()).isEqualTo(context.getPost());
    }

    @Then("User gets a partially updated post with the correct details")
    public void user_gets_partially_updated_post() {
        String updatedField = context.getPartialUpdate();
        PostModel initialPost = context.getActualPost();
        initialPost.setBody(StringHelper.removeNewLinesFromBody(initialPost.getBody()));

        PostModel updatedPost = context.getPost();
        updatedPost.setBody(StringHelper.removeNewLinesFromBody(updatedPost.getBody()));

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(updatedPost).usingRecursiveComparison().ignoringFields(updatedField).isEqualTo(initialPost);

        String actualField = "";
        switch (updatedField) {
            case "body" -> actualField = updatedPost.getBody();
            case "title" -> actualField = updatedPost.getTitle();
            default -> System.out.printf("Oops! Wrong field type %s%n", updatedField);
        }
        ;

        softly.assertThat(actualField).isEqualTo(String.format("updated %s", updatedField));
        softly.assertAll();
    }
}
