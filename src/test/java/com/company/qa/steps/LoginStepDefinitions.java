
package com.company.qa.steps;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.company.qa.pages.LoginPage;
import com.company.qa.utils.ConfigReader;
import com.company.qa.utils.DriverFactory;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginStepDefinitions {

    private static final Logger logger =
            LogManager.getLogger(LoginStepDefinitions.class);

    private WebDriver driver;
    private LoginPage loginPage;

    @Given("the user is on the OrangeHRM login page")
    public void userIsOnLoginPage() {
        logger.info("Opening OrangeHRM login page");

        driver = DriverFactory.getDriver(ConfigReader.getBrowser());
        driver.get(ConfigReader.getBaseUrl());

        loginPage = new LoginPage(driver);
    }

    @When("the user enters valid username and password")
    public void userEntersValidCredentials() {
        logger.info("Entering valid login credentials");

        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
    }

    @When("the user enters invalid username {string} and password {string}")
    public void userEntersInvalidCredentials(String username, String password) {
    	logger.info("Entering invalid login credentials for username: {}", username);

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @When("clicks the Login button")
    public void userClicksLoginButton() {
        logger.info("Clicking Login button");

        loginPage.clickLoginButton();
    }

    @Then("the Dashboard should be displayed")
    public void dashboardShouldBeDisplayed() {
        Assert.assertTrue(
                loginPage.isDashboardDisplayed(),
                "Dashboard was not displayed after login"
        );

        logger.info("Dashboard displayed successfully");
    }
    
    
    //// Simulate below lines to capture screen shots only test, I will keep on commented 
     
//    @Then("the Dashboard should be displayed")
//    public void dashboardShouldBeDisplayed() {
//
//    	Assert.assertTrue(
//    	        false,
//    	        "PURPOSELY FAILED: Testing screenshot capture"
//    	);
//
//        logger.info("Dashboard displayed successfully");
//    }
    
//    Execute: mvn clear test 
//    HTML report: target/cucumber-reports/cucumber.html
//   PNG screenshot: target/failure-screenshots/

    @Then("an invalid login message should be displayed")
    public void invalidLoginMessageShouldBeDisplayed() {
        Assert.assertTrue(
                loginPage.isInvalidLoginMessageDisplayed(),
                "Expected invalid login message was not displayed"
        );

        logger.info("Invalid login message displayed successfully");
    }
}