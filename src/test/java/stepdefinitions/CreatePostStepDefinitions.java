package stepdefinitions;

import actions.CreateActions;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.PostModel;
import org.assertj.core.api.SoftAssertions;
import utils.Context;

public class CreatePostStepDefinitions {
    private Context context;
    private CreateActions createActions = new CreateActions();

    public CreatePostStepDefinitions(Context context) {
        this.context = context;
    }

    @When("User creates a new post")
    public void user_creates_new_post(DataTable table) {
        PostModel newPost = PostModel.builder()
                .body(table.asList().get(3))
                .title(table.asList().get(4))
                .userId(Integer.parseInt(table.asList().get(5)))
                .build();

        context.setPost(createActions.createNewPost(newPost));
        context.setActualPost(newPost);
    }

    @When("User tries to create an invalid new post")
    public void user_creates_invalid_post(DataTable table) {
        PostModel newPost = PostModel.builder()
                .body(table.asList().get(3))
                .title(table.asList().get(4))
                .userId(Integer.parseInt(table.asList().get(5)))
                .build();

        context.setResponseCode(createActions.createInvalidNewPost(newPost));
    }

    @Then("User can see a new post with the correct details")
    public void user_can_see_new_post() {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(context.getActualPost()).usingRecursiveComparison()
                .ignoringFields("id").isEqualTo(context.getPost());
        softly.assertThat(context.getPost().getId()).isPositive();
        softly.assertAll();
    }
}
