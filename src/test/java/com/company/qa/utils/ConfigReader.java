package com.company.qa.utils;

import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream("config/config-qa.properties")) {

            if (input == null) {
                throw new RuntimeException(
                    "Cannot find config/config-qa.properties");
            }

            properties.load(input);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration", e);
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