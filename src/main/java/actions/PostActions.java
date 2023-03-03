package actions;

import io.restassured.http.ContentType;
import models.PostModel;
import utils.ApplicationConfiguration;
import utils.ApplicationConfigurationLoader;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<PostModel> sendPostsRequest () {
        return Arrays.stream(given(initSpec(ContentType.JSON))
                .when()
                .get(appConfig.postsUrl())
                .then()
                .log().ifError()
                .statusCode(200)
                .extract().as(PostModel[].class)).collect(Collectors.toList());
    }

    public Integer sendUnsuccessfulPostRequest (String id) {
        return given(initSpec(ContentType.JSON))
                .when()
                .get(appConfig.postsUrl() + id)
                .then()
                .extract().statusCode();
    }

    public PostModel createNewPost(PostModel post) {
        return given(initSpec(ContentType.JSON))
                .body(post)
                .when()
                .post(appConfig.postsUrl())
                .then()
                .log().ifError()
                .statusCode(201)
                .extract().as(PostModel.class);
    }
}
