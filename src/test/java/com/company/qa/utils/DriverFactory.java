package com.company.qa.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver getDriver(String browser) {

        if (driver == null) {

            if (browser == null || browser.isBlank()) {
                browser = "chrome";
            }

            switch (browser.trim().toLowerCase()) {

                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();

                    // Headless mode on GitHub Actions only
                    if ("true".equalsIgnoreCase(
                            System.getenv("GITHUB_ACTIONS"))) {

                        chromeOptions.addArguments("--headless=new");
                        chromeOptions.addArguments("--no-sandbox");
                        chromeOptions.addArguments("--disable-dev-shm-usage");
                    }

                    driver = new ChromeDriver(chromeOptions);
                    break;

                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();

                    // Headless mode on GitHub Actions only
                    if ("true".equalsIgnoreCase(
                            System.getenv("GITHUB_ACTIONS"))) {

                        firefoxOptions.addArguments("-headless");
                    }

                    driver = new FirefoxDriver(firefoxOptions);
                    break;

                case "edge":
                    EdgeOptions edgeOptions = new EdgeOptions();

                    // Headless mode on GitHub Actions only
                    if ("true".equalsIgnoreCase(
                            System.getenv("GITHUB_ACTIONS"))) {

                        edgeOptions.addArguments("--headless=new");
                        edgeOptions.addArguments("--no-sandbox");
                        edgeOptions.addArguments("--disable-dev-shm-usage");
                    }

                    driver = new EdgeDriver(edgeOptions);
                    break;

                default:
                    throw new IllegalArgumentException(
                            "Unsupported browser: " + browser);
            }
        }

        return driver;
    }

    public static void quitDriver() {

        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}

//// WebDriver driver --Stores the browser instance
//// getDriver()---Creates Chrome if it doesn't already exist
//// new ChromeDriver()----Opens Chrome using Selenium
//// driver == null  ---Prevents creating another browser instance while one is already stored

////only if you use only chrome dirver, just for references
//package com.company.qa.utils;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//public class DriverFactory {
//
//    private static WebDriver driver;
//
//    public static WebDriver getDriver() {
//
//        if (driver == null) {
//            driver = new ChromeDriver();
//        }
//
//        return driver;
//    }
//
//    public static void quitDriver() {
//
//        if (driver != null) {
//            driver.quit();
//            driver = null;
//        }
//    }
//}
