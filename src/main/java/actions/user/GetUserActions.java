package actions.user;

import actions.BaseActions;
import models.UserModel;
import utils.ApplicationConfiguration;
import utils.ApplicationConfigurationLoader;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class GetUserActions extends BaseActions {
    protected static ApplicationConfiguration appConfig = ApplicationConfigurationLoader.getConfig();

    public List<UserModel> sendUsersRequest() {
        return Arrays.stream(given(initSpec())
                .when()
                .get(appConfig.usersUrl())
                .then()
                .log().ifError()
                .statusCode(200)
                .extract().as(UserModel[].class)).collect(Collectors.toList());
    }

    public UserModel sendUserRequest(int id) {
        return given(initSpec())
                .when()
                .get(appConfig.usersUrl() + String.format("/%d", id))
                .then()
                .log().ifError()
                .statusCode(200)
                .extract().as(UserModel.class);
    }
}
