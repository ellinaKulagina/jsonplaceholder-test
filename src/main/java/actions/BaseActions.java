package actions;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.NoArgsConstructor;
import utils.MyAllureRestAssured;

import static helpers.StringHelper.urlEncode;
import static io.restassured.RestAssured.given;

@NoArgsConstructor
public class BaseActions {

    public static RequestSpecification initSpec() {
        return new RequestSpecBuilder()
                .addFilter(new RequestLoggingFilter())
                .setUrlEncodingEnabled(false)
                .setContentType(ContentType.JSON)
                .addFilter(new MyAllureRestAssured())
                .build();
    }

    public Integer sendUnsuccessfulRequest(String url, String id) {
        return given(initSpec())
                .when()
                .get(url + String.format("/%s", urlEncode(id)))
                .then()
                .extract().statusCode();
    }
}
