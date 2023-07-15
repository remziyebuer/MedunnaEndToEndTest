package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(
        features = "src/test/resources/endtoend_features",//content root alinir
        glue = {"stepdefs","hooks"},//source root//eger buraya hooks belirtmezsem spec null olur
        //iki deger verdigimizde curly braces icine almamiz gerekli
        tags = "@e2e",
        dryRun = false

)
public class Runner {

}
