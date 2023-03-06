package stepdefinitions;

import actions.user.GetUserActions;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.*;
import org.assertj.core.api.SoftAssertions;
import utils.ApplicationConfiguration;
import utils.ApplicationConfigurationLoader;
import utils.Context;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.StringConstants.*;

public class GetUserStepDefinitions {

    protected static ApplicationConfiguration appConfig = ApplicationConfigurationLoader.getConfig();
    private Context context;
    private GetUserActions getUserActions = new GetUserActions();

    public GetUserStepDefinitions(Context context) {
        this.context = context;
    }

    @When("User requests to see all users")
    public void user_requests_all_users() {
        List<UserModel> users = getUserActions.sendUsersRequest();
        context.setUsers(users);
    }

    @Then("User gets a list of all users")
    public void user_gets_all_users() {
        List<UserModel> users = context.getUsers();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(users).isNotEmpty();
        softly.assertThat(users).size().isGreaterThanOrEqualTo(10);
        // not all fields are checked here as I don't know how many of them are mandatory
        softly.assertThat(users).allSatisfy(commentModel -> {
            assertThat(commentModel.getId()).isPositive();
            assertThat(commentModel.getName()).isNotEmpty();
            assertThat(commentModel.getEmail()).isNotEmpty();
            assertThat(commentModel.getUsername()).isNotEmpty();
        });
        softly.assertAll();
    }

    @When("User requests to see a user with id {int}")
    public void user_requests_to_see_a_user_with_id(Integer id) {
        UserModel actualUser = getUserActions.sendUserRequest(id);
        context.setActualUser(actualUser);
    }

    @When("User requests to see a user with invalid {string}")
    public void user_requests_user_with_invalid_userId(String userId) {
        int responseCode = getUserActions.sendUnsuccessfulRequest(appConfig.commentsUrl(), userId);
        context.setResponseCode(responseCode);
    }

    @Then("User gets a valid response for requested user")
    public void user_gets_valid_response_for_user() {
        UserModel expectedUser = UserModel.builder()
                .id(context.getActualUser().getId())
                .name(USERFULLNAME)
                .username(USERNAME)
                .email(USEREMAIL)
                .address(AddressModel.builder().geo(GeoModel.builder().lat(USERLAT).lng(USERLNG).build())
                        .city(USERCITY).suite(USERSUITE).street(USERSTREET).zipcode(USERZIPCODE).build())
                .phone(USERPHONE)
                .website(USERWEBSITE)
                .company(CompanyModel.builder().name(USERCOMPANYNAME).catchPhrase(USERCATCHPHRASE).bs(USERBS).build())
                .build();

        UserModel actualUser = context.getActualUser();
        assertThat(actualUser).isEqualTo(expectedUser);
    }
}
