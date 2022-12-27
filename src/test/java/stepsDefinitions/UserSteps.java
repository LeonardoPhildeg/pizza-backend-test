package stepsDefinitions;

import io.restassured.path.json.JsonPath;
import pojo.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import util.Context;

import static org.assertj.core.api.Assertions.assertThat;

public class UserSteps extends Context {

    private RequestSpecification spec;
    private Response response;

    @Given("I provide email and password")
    public void iProvideAndPassword() {
        User user = new User("testName","testEmail@email", "123");

        this.spec = RestAssured.given()
                .spec(new RequestSpecBuilder()
                        .setBaseUri("http://localhost:3333")
                        .setContentType(ContentType.JSON)
                        .setBody(user)
                        .build());
    }

    @When("I call user endpoint")
    public void iCallUserEndpoint() {
        this.response = this.spec.when()
                .post("/user");
    }

    @Then("the response body should contain name, email")
    public void theResponseBodyShouldContainNameEmail() {
        this.response = this.response.then()
                .extract()
                .response();
        this.testContext().setResponse(this.response);

        JsonPath jsonPath = new JsonPath(this.response.asString());
        Object name = jsonPath.get("name");
        Object email = jsonPath.get("email");

        assertThat(name).isEqualTo("testName");
        assertThat(email).isEqualTo("testEmail@email");
    }

    @Given("I am authenticated")
    public void iAmAuthenticated() {

    }

    @When("I call user detail endpoint")
    public void iCallUserDetailEndpoint() {
    }

    @Then("the response body should contain id, name, email")
    public void theResponseBodyShouldContainIdNameEmail() {
    }
}
