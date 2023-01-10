package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"stepsdefinitions", "util"},
        features = {"src/test/java/resources/features"},
        tags = "@only"
)
public class Runner {

}
