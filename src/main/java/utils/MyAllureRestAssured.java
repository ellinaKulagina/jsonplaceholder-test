package utils;

import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

import java.util.UUID;

public class MyAllureRestAssured extends AllureRestAssured {

    public MyAllureRestAssured() {
        // we only need to override filter method for now, no actual implementation for this method is required
    }

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec, FilterContext filterContext) {
        AllureLifecycle lifecycle = Allure.getLifecycle();
        lifecycle.startStep(UUID.randomUUID().toString(), (new StepResult()).setStatus(Status.PASSED)
                .setName(String.format("%s: %s", requestSpec.getMethod(), requestSpec.getURI())));

        Response response;
        try {
            response = super.filter(requestSpec, responseSpec, filterContext);
        } finally {
            lifecycle.stopStep();
        }

        return response;
    }
}