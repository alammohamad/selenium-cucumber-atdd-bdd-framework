
package com.company.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriver driver;

    // Locators
    private final By usernameField =
            By.xpath("//input[@placeholder='Username']");

    private final By passwordField =
            By.xpath("//input[@placeholder='Password']");

    private final By loginButton =
            By.cssSelector("button[type='submit']");

    private final By invalidLoginMessage =
            By.cssSelector(".oxd-alert-content-text");

    private final By dashboardHeader =
            By.xpath("//div[@class='oxd-topbar-header-title']");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Page actions

    public void enterUsername(String username) {
    	  	   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

           wait.until(
           ExpectedConditions.visibilityOfElementLocated(usernameField)
           ).sendKeys(username);
    }

    public void enterPassword(String password) {
    		   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

           wait.until(
           ExpectedConditions.visibilityOfElementLocated(passwordField)
          ).sendKeys(password);
   }


    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }


    public boolean isInvalidLoginMessageDisplayed() {
    	        WebDriverWait wait =
            new WebDriverWait(driver, Duration.ofSeconds(10));

            try {
            wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                invalidLoginMessage
            )
           );
           return true;
           } catch (org.openqa.selenium.TimeoutException e) {
           return false;
    }
}



	public boolean isDashboardDisplayed() {
	    WebDriverWait wait =
	            new WebDriverWait(driver, Duration.ofSeconds(15));
	
	    try {
	        return wait.until(
	            ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//h6[text()='Dashboard']")
	            )
	        ).isDisplayed();
	    } catch (org.openqa.selenium.TimeoutException e) {
	        return false;
	    }
}

}
