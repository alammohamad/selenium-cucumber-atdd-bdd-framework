package com.company.qa.runners;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@Listeners(EmailTestListener.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {
        "com.company.qa.steps",
        "com.company.qa.hooks"
    },
    plugin = {
        "pretty",
        "html:target/cucumber-reports/cucumber.html"
    },
    monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}

//What does this code do-------------
//
//| Code                          | Purpose                                                 |
//| ----------------------------- | ------------------------------------------------------- |
//| `AbstractTestNGCucumberTests` | Connects Cucumber to TestNG                             |
//| `features`                    | Tells Cucumber where to find `.feature` files           |
//| `glue`                        | Tells Cucumber where to find step definitions and hooks |
//| `plugin`                      | Creates readable console output and an HTML report      |
//| `parallel = false`            | Runs scenarios sequentially for now                     |

