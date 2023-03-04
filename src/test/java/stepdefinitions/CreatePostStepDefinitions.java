package stepdefinitions;

import actions.post.CreatePostActions;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.PostModel;
import org.assertj.core.api.SoftAssertions;
import utils.Context;

public class CreatePostStepDefinitions {
    private Context context;
    private CreatePostActions createPostActions = new CreatePostActions();

    public CreatePostStepDefinitions(Context context) {
        this.context = context;
    }

    @When("User creates a new post")
    public void user_creates_new_post(DataTable table) {
        PostModel newPostModel = PostModel.builder()
                .body(table.asList().get(3))
                .title(table.asList().get(4))
                .userId(Integer.parseInt(table.asList().get(5)))
                .build();

        PostModel newPost = createPostActions.createNewPost(newPostModel);
        context.setPost(newPost);
        context.setActualPost(newPostModel);
    }

    @When("User tries to create an invalid new post")
    public void user_creates_invalid_post(DataTable table) {
        PostModel newInvalidPostModel = PostModel.builder()
                .body(table.asList().get(3))
                .title(table.asList().get(4))
                .userId(Integer.parseInt(table.asList().get(5)))
                .build();

        int responseCode = createPostActions.createInvalidNewPost(newInvalidPostModel);
        context.setResponseCode(responseCode);
    }

    @Then("User can see a new post with the correct details")
    public void user_can_see_new_post() {
        PostModel actualPost = context.getActualPost();
        PostModel expectedPost = context.getPost();

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(actualPost).usingRecursiveComparison().ignoringFields("id").isEqualTo(expectedPost);
        softly.assertThat(expectedPost.getId()).isPositive();
        softly.assertAll();
    }
}
