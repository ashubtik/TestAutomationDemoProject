package test.framework.testrunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/test/resources/features/API.feature"},
        tags = "",
        glue = {"test.framework.definitions"})
public class APITests extends AbstractTestNGCucumberTests {
}
