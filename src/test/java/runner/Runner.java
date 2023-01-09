package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"stepsDefinitions", "util"},
        features = {"src/test/java/resources/features/category.feature"}
)
public class Runner {

}
