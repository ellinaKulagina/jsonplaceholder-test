package actions.post;

import actions.BaseActions;
import models.PostModel;
import utils.ApplicationConfiguration;
import utils.ApplicationConfigurationLoader;

import static io.restassured.RestAssured.given;

public class CreatePostActions extends BaseActions {
    protected static ApplicationConfiguration appConfig = ApplicationConfigurationLoader.getConfig();

    public PostModel createNewPost(PostModel post) {
        return given(initSpec())
                .body(post)
                .when()
                .post(appConfig.postsUrl())
                .then()
                .log().ifError()
                .statusCode(201)
                .extract().as(PostModel.class);
    }

    public int createInvalidNewPost(PostModel post) {
        return given(initSpec())
                .body(post)
                .when()
                .post(appConfig.postsUrl())
                .then()
                .extract().statusCode();
    }
}