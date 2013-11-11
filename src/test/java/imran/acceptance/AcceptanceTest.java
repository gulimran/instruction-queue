package imran.acceptance;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@Cucumber.Options(
        format = {"pretty", "html:target/cucumber-report"},
        features = "classpath:imran/acceptance/feature",
        glue = "imran/acceptance/step",
        tags = "~@wip")
public class AcceptanceTest {
}
