package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import stepsDefinitions.Hook;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"stepsDefinitions", "util"},
        features = {"src/test/java/resources/features"},
        tags = "@Only"
)
public class Runner {

    @BeforeClass
    public static void setup() {
        new Hook();
    }

}
