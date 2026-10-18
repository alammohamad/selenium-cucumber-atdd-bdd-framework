package com.company.qa.runners;

import org.testng.IExecutionListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestResult;
import org.testng.ITestListener;

import com.company.qa.notifications.EmailNotification;

public class EmailTestListener implements IExecutionListener, ISuiteListener, ITestListener {

    private int passed = 0;
    private int failed = 0;
    private int skipped = 0;

    @Override
    public void onExecutionStart() {
        passed = 0;
        failed = 0;
        skipped = 0;
        System.out.println("Email listener: Test execution started.");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        passed++;
    }

    @Override
    public void onTestFailure(ITestResult result) {
        failed++;
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        skipped++;
    }

    @Override
    public void onStart(ISuite suite) {
        System.out.println("Email listener: Test suite started.");
    }

    @Override
    public void onFinish(ISuite suite) {
        String browser = System.getProperty("browser", "chrome");
        String environment = System.getProperty("env", "qa");

        String status = failed > 0 ? "FAILED" : "PASSED";

        String subject = "Selenium-Cucumber BDD - " + status;

        String body =
                "Automation Test Execution Summary\n\n"
                + "Status: " + status + "\n"
                + "Passed: " + passed + "\n"
                + "Failed: " + failed + "\n"
                + "Skipped: " + skipped + "\n"
                + "Environment: " + environment.toUpperCase() + "\n"
                + "Browser: " + browser + "\n\n"
                + "Please review the test reports for details.";

        try {
            EmailNotification.sendEmail(subject, body);
        } catch (Exception e) {
            System.err.println("Unable to send test execution email.");
            e.printStackTrace();
        }
    }

    @Override
    public void onExecutionFinish() {
        System.out.println("Email listener: Test execution finished.");
    }
}
