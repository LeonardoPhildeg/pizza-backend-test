package stepsDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.Category;
import util.Context;

import static org.assertj.core.api.Assertions.assertThat;

public class CategorySteps extends Context {

    private RequestSpecification spec;

    private Response response;

    private Category category;

    @Given("I provide the {string} for new category")
    public void iProvideTheNameForNewCategory(String name) {
        this.category = new Category();
        this.category.setName(name);

        this.spec = RestAssured.given()
                .spec(this.baseSpec())
                .header("Authorization", "Bearer " + this.testContext().getToken())
                .body(category);
        this.testContext().setPayload(this.spec);
    }

    @When("I call category endpoint")
    public void iCallCategoryEndpoint() {
        this.response = this.spec.when()
                .post("/category");
    }

    @Then("the response body should contain name, id")
    public void theResponseBodyShouldContainNameId() {
        this.response = this.response.then()
                .extract()
                .response();
        this.testContext().setResponse(this.response);
        Category category = this.response.as(Category.class);

        assertThat(category.getId()).isNotNull();
        assertThat(category.getName()).isEqualTo(this.category.getName().toLowerCase());
    }

    @Given("I provide the {string} for new category with not authenticated user")
    public void iProvideTheNameForNewCategoryWithNotAuthenticatedUser(String name) {
        this.category = new Category();
        this.category.setName(name);

        this.spec = RestAssured.given()
                .spec(this.baseSpec())
                .body(category);
    }

    @Then("I should not have response body")
    public void iShouldNotHaveResponseBody() {
        this.response = this.response.then()
                .extract()
                .response();
        this.testContext().setResponse(this.response);

        assertThat(this.response.body().asString()).isEmpty();
    }
}
