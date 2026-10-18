package com.company.qa.utils;

import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        String environment = System.getProperty("env", "qa")
                .trim()
                .toLowerCase();

        if (!environment.equals("qa") && !environment.equals("prod")) {
            throw new IllegalArgumentException(
                    "Invalid environment: " + environment
                    + ". Use qa or prod.");
        }

        String configFile = "config/config-" + environment + ".properties";

        try (InputStream input = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream(configFile)) {

            if (input == null) {
                throw new RuntimeException(
                        "Cannot find configuration file: " + configFile);
            }

            properties.load(input);

        } catch (IOException e) {
            throw new RuntimeException(
                    "Failed to load configuration: " + configFile, e);
        }
    }

    public static String getBrowser() {
        return System.getProperty(
                "browser",
                properties.getProperty("browser", "chrome"))
                .trim()
                .toLowerCase();
    }

    public static String getBaseUrl() {
        return properties.getProperty(
                "baseUrl",
                "https://opensource-demo.orangehrmlive.com/"
        ).trim();
    }
}