package TestRunner;

//import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//@RunWith(Cucumber.class)
@CucumberOptions(features = { "src//test//resources//Features//smokes.feature" },

		glue = "StepDefination",

		plugin = { "pretty", "html:reports/myreport.html",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" },

		dryRun = false, // checks mapping between scenario steps and step definition methods
		monochrome = true, // to avoid junk characters in output
		publish = true // to publish report in cucumber server

)

public class testRunnerTestNG extends AbstractTestNGCucumberTests {

}