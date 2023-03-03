package actions;

import io.cucumber.messages.internal.com.google.gson.JsonObject;
import io.restassured.RestAssured;
import models.PostModel;
import utils.ApplicationConfiguration;
import utils.ApplicationConfigurationLoader;

import static io.restassured.RestAssured.given;

public class UpdateActions extends BaseActions {
    protected static ApplicationConfiguration appConfig = ApplicationConfigurationLoader.getConfig();

    public PostModel updatePost(PostModel post) {
        return RestAssured.given(initSpec())
                .body(post)
                .when()
                .put(GetActions.appConfig.postsUrl() + String.format("/%s", post.getId()))
                .then()
                .log().ifError()
                .statusCode(200)
                .extract().as(PostModel.class);
    }

    public PostModel partiallyUpdatePost(JsonObject update, int id) {
        return given(initSpec())
                .body(update.toString())
                .when()
                .patch(appConfig.postsUrl() + String.format("/%s", id))
                .then()
                .log().ifError()
                .statusCode(200)
                .extract().as(PostModel.class);
    }
}