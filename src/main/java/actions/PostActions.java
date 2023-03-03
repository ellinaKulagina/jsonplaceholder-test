package actions;

import io.restassured.http.ContentType;
import models.PostModel;
import utils.ApplicationConfiguration;
import utils.ApplicationConfigurationLoader;

import static io.restassured.RestAssured.given;

public class PostActions extends BaseActions {

    protected static ApplicationConfiguration appConfig = ApplicationConfigurationLoader.getConfig();

    public PostModel sendPostRequest (int id) {
        return given(initSpec(ContentType.JSON))
                .when()
                .get(appConfig.postsUrl() + String.format("/%s", id))
                .then()
                .log().ifError()
                .statusCode(200)
                .extract().as(PostModel.class);
    }
}
