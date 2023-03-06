package actions.post;

import actions.BaseActions;
import io.restassured.response.Response;
import models.PostModel;
import utils.ApplicationConfiguration;
import utils.ApplicationConfigurationLoader;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class GetPostActions extends BaseActions {
    protected static ApplicationConfiguration appConfig = ApplicationConfigurationLoader.getConfig();

    public List<PostModel> getListOfPosts(String url, String path) {
        Response response = sendGetRequest(url, path);
        return Arrays.stream(response.as(PostModel[].class)).collect(Collectors.toList());
    }

    public PostModel getPost(String url, String path) {
        Response response = sendGetRequest(url, path);
        return response.as(PostModel.class);
    }

    private Response sendGetRequest(String url, String path) {
        return given(initSpec())
                .when()
                .get(url + path)
                .then()
                .log().ifError()
                .statusCode(200)
                .extract().response();
    }
}
