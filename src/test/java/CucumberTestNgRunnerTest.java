import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
    features = {"src/test/resources/features/"},
    glue = {"stepdefinitions"},
    plugin = {"io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"}
)
public class CucumberTestNgRunnerTest extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
