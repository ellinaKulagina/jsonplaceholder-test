package actions.comment;

import actions.BaseActions;
import models.CommentModel;
import utils.ApplicationConfiguration;
import utils.ApplicationConfigurationLoader;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class GetCommentActions extends BaseActions {
    protected static ApplicationConfiguration appConfig = ApplicationConfigurationLoader.getConfig();

    public List<CommentModel> sendCommentsRequest() {
        return Arrays.stream(given(initSpec())
                .when()
                .get(appConfig.commentsUrl())
                .then()
                .log().ifError()
                .statusCode(200)
                .extract().as(CommentModel[].class)).collect(Collectors.toList());
    }

    public List<CommentModel> getCommentsForPostRequest(Integer postId) {
        return Arrays.stream(given(initSpec())
                .when()
                .get(String.format(appConfig.commentPostUrl(), postId))
                .then()
                .log().ifError()
                .statusCode(200)
                .extract().as(CommentModel[].class)).collect(Collectors.toList());
    }

    public CommentModel sendCommentRequest(int id) {
        return given(initSpec())
                .when()
                .get(appConfig.commentsUrl() + String.format("/%d", id))
                .then()
                .log().ifError()
                .statusCode(200)
                .extract().as(CommentModel.class);
    }
}
