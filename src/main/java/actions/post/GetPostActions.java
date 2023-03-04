package actions.post;

import actions.BaseActions;
import models.PostModel;
import utils.ApplicationConfiguration;
import utils.ApplicationConfigurationLoader;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class GetPostActions extends BaseActions {
    protected static ApplicationConfiguration appConfig = ApplicationConfigurationLoader.getConfig();

    public PostModel sendPostRequest(int id) {
        return given(initSpec())
                .when()
                .get(appConfig.postsUrl() + String.format("/%s", id))
                .then()
                .log().ifError()
                .statusCode(200)
                .extract().as(PostModel.class);
    }

    public List<PostModel> sendPostsRequest(String urlPath) {
        return Arrays.stream(given(initSpec())
                .when()
                .get(appConfig.postsUrl() + urlPath)
                .then()
                .log().ifError()
                .statusCode(200)
                .extract().as(PostModel[].class)).collect(Collectors.toList());
    }
}
