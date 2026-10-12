Feature: OrangeHRM Login

 # User Story
  As an OrangeHRM user
  I want to log into the application
  So that I can access the Dashboard

  @smoke @validLogin
  Scenario: Login with valid credentials
    Given the user is on the OrangeHRM login page
    When the user enters valid username and password
    And clicks the Login button
    Then the Dashboard should be displayed

  @regression @invalidLogin
  Scenario Outline: Login with invalid credentials
    Given the user is on the OrangeHRM login page
    When the user enters invalid username "<username>" and password "<password>"
    And clicks the Login button
    Then an invalid login message should be displayed

    Examples:
      | username      | password     |
      | Invaliduserid | admin123     |
      | Admin         | WrongPass123 |

