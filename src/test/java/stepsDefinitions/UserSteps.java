package stepsDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.User;
import util.Context;

import static org.assertj.core.api.Assertions.assertThat;

public class UserSteps extends Context {

    private RequestSpecification spec;

    private Response response;

    @Given("I provide email and password")
    public void iProvideAndPassword() {
        User user = new User();
        user.setName("testName2");
        user.setEmail("testEmail2@email");
        user.setPassword("123");

        this.spec = RestAssured.given()
                .spec(this.baseSpec())
                .body(user);
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

        assertThat((String) jsonPath.get("name")).isEqualTo("testName");
        assertThat((String) jsonPath.get("email")).isEqualTo("testEmail@email");
    }

    @Given("I am authenticated")
    public void iAmAuthenticated() {
        String token = this.testContext().getToken();
        this.spec = RestAssured.given()
                .spec(this.baseSpec())
                .header("Authorization", "Bearer " + token);
    }

    @When("I call user detail endpoint")
    public void iCallUserDetailEndpoint() {
        this.response = this.spec.when()
                .get("/userDetail");
    }

    @Then("the response body should contain id, name, email")
    public void theResponseBodyShouldContainIdNameEmail() {
        String response = this.response.then()
                .extract()
                .response()
                .asString();
        this.testContext().setResponse(this.response);
        JsonPath jsonResponse = new JsonPath(response);

        assertThat((String) jsonResponse.get("id")).isNotNull();
        assertThat((String) jsonResponse.get("name")).isEqualTo("testName");
        assertThat((String) jsonResponse.get("email")).isEqualTo("testEmail@email");
    }

}
