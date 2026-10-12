//// if use only one browser eg. chrome
//package com.company.qa.hooks;
//
//import com.company.qa.utils.DriverFactory;
//import io.cucumber.java.After;
//import io.cucumber.java.Before;
//
//public class Hooks {
//
//    @Before
//    public void setUp() {
//        DriverFactory.getDriver("chrome");
//    }
//
//    @After
//    public void tearDown() {
//        DriverFactory.quitDriver();
//    }
//}
// Below one is working fine
//package com.company.qa.hooks;
//
//import com.company.qa.utils.ConfigReader;
//import com.company.qa.utils.DriverFactory;
//
//import io.cucumber.java.After;
//import io.cucumber.java.Before;
//import io.cucumber.java.Scenario;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//public class Hooks {
//
//    private static final Logger logger =
//            LogManager.getLogger(Hooks.class);
//
//    @Before
//    public void setUp(Scenario scenario) {
//
//        logger.info("========================================");
//        logger.info("STARTING SCENARIO: {}", scenario.getName());
//
//        DriverFactory.getDriver(ConfigReader.getBrowser());
//    }
//
//    
//    @After
//    public void tearDown(Scenario scenario) {
//
//        if (scenario.isFailed()) {
//            logger.error("SCENARIO FAILED: {}", scenario.getName());
//            logger.error("Failure status: {}", scenario.getStatus());
//        } else {
//            logger.info("SCENARIO PASSED: {}", scenario.getName());
//        }
//
//        DriverFactory.quitDriver();
//
//        logger.info("FINISHED SCENARIO: {}", scenario.getName());
//        logger.info("========================================");
//    }
//    
//}

//// Try capture failure screen shot-------

package com.company.qa.hooks;

import com.company.qa.utils.ConfigReader;
import com.company.qa.utils.DriverFactory;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Hooks {

    private static final Logger logger =
            LogManager.getLogger(Hooks.class);

    @Before
    public void setUp(Scenario scenario) {

        logger.info("========================================");
        logger.info("STARTING SCENARIO: {}", scenario.getName());

        DriverFactory.getDriver(ConfigReader.getBrowser());
    }

    @After
    public void tearDown(Scenario scenario) {

        WebDriver driver = DriverFactory.getDriver(
                ConfigReader.getBrowser());

        if (scenario.isFailed()) {
            logger.error("SCENARIO FAILED: {}", scenario.getName());

            try {
            	logger.info("Browser URL before screenshot: {}", driver.getCurrentUrl());
            	logger.info("Browser title before screenshot: {}", driver.getTitle());

            	Thread.sleep(5000);
            	
                // Capture the actual browser screenshot
                byte[] screenshot =
                        ((TakesScreenshot) driver)
                                .getScreenshotAs(OutputType.BYTES);
                logger.info("Screenshot byte size: {}", screenshot.length);

                // 1. Attach screenshot to the Cucumber HTML report
                scenario.attach(
                        screenshot,
                        "image/png",
                        "Failure Screenshot"
                );

                logger.info(
                        "Failure screenshot attached to Cucumber report");

                // 2. Save screenshot as a PNG file on disk
                Path screenshotDirectory = Paths.get(
                        "target", "failure-screenshots");

                Files.createDirectories(screenshotDirectory);

                String safeScenarioName = scenario.getName()
                        .replaceAll("[^a-zA-Z0-9.-]", "_");

                String fileName = safeScenarioName + "-"
                        + System.currentTimeMillis() + ".png";

                Path screenshotFile =
                        screenshotDirectory.resolve(fileName);

                Files.write(screenshotFile, screenshot);

                logger.info(
                        "Screenshot saved to: {}",
                        screenshotFile.toAbsolutePath());

            } catch (Exception e) {
                logger.error("Could not capture or save failure screenshot", e);
            }

        } else {
            logger.info("SCENARIO PASSED: {}", scenario.getName());
        }

        DriverFactory.quitDriver();

        logger.info("FINISHED SCENARIO: {}", scenario.getName());
        logger.info("========================================");
    }
}
