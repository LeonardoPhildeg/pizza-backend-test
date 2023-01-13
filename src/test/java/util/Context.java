package util;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static util.TestContext.CONTEXT;

public abstract class Context {

    public static final String BASE_URI = "http://pizza-api:3333";

    public TestContext testContext() {
        return CONTEXT;
    }

    public RequestSpecification baseSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setContentType(ContentType.JSON)
                .build();
    }

}
