package actions;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.NoArgsConstructor;
import utils.MyAllureRestAssured;

import static io.restassured.RestAssured.given;

@NoArgsConstructor
public class BaseActions {

    public static RequestSpecification initSpec() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addFilter(new MyAllureRestAssured())
                .build();
    }

    public Integer sendUnsuccessfulRequest(String url, String id) {
        return given(initSpec())
                .when()
                .get(url + id)
                .then()
                .extract().statusCode();
    }
}
