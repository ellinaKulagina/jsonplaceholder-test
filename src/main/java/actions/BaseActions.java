package actions;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.NoArgsConstructor;
import utils.MyAllureRestAssured;

@NoArgsConstructor
public class BaseActions {

    public static RequestSpecification initSpec(ContentType contentType) {
        return new RequestSpecBuilder()
                .setContentType(contentType)
                .addFilter(new MyAllureRestAssured())
                .build();
    }
}
