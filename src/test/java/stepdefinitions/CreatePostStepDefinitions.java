package stepdefinitions;

import actions.PostActions;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.PostModel;
import org.assertj.core.api.SoftAssertions;
import utils.ApplicationConfiguration;
import utils.ApplicationConfigurationLoader;
import utils.Context;

import static org.assertj.core.api.Assertions.assertThat;

public class CreatePostStepDefinitions {

    protected static ApplicationConfiguration localApplicationConfiguration = ApplicationConfigurationLoader.getConfig();
    private Context context;

    public CreatePostStepDefinitions(Context context) {
        this.context = context;
    }

    private PostActions postActions = new PostActions();

    @When("User creates a new post")
    public void user_creates_new_post(DataTable table) {
        PostModel newPost = PostModel.builder()
                .body(table.asList().get(3))
                .title(table.asList().get(4))
                .userId(Integer.parseInt(table.asList().get(5)))
                .build();

        context.setPost(postActions.createNewPost(newPost));
        context.setActualPost(newPost);
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
