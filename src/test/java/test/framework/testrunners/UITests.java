package test.framework.testrunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/test/resources/features/UI.feature"},
        plugin = { "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        tags = "",
        glue = {"test.framework.definitions"})
public class UITests extends AbstractTestNGCucumberTests {
}
