

package com.company.qa.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver getDriver(String browser) {

        if (driver == null) {

            if (browser == null || browser.isBlank()) {
                browser = "chrome";
            }

            switch (browser.trim().toLowerCase()) {

                case "chrome":
                    driver = new ChromeDriver();
                    break;

                case "firefox":
                    driver = new FirefoxDriver();
                    break;

                case "edge":
                    driver = new EdgeDriver();
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
