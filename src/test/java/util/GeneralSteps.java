package util;

import io.cucumber.java.en.And;
import io.restassured.response.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class GeneralSteps extends Context {

    @And("status code is {int}")
    public void statusCodeIs(int statusCode) {
        Response response = this.testContext().getResponse();

        assertThat(response.statusCode()).isEqualTo(statusCode);
    }
}
