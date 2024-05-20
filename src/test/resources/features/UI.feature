#Feature: Login to Thinking Tester platform
#
#  Background:
#    Given User opens Thinking Tester platform login page
#
#  @LoginScenario
#  Scenario: Login with valid and invalid credentials
#    When User logs in with username 'IncorrectUsername' and password 'IncorrectPassword'
#    Then Incorrect username or password error is displayed
#    When User logs in with valid credentials
#    Then Contact List page is open
#    Given User logs out
#
#  @AddContactScenario
#  Scenario: Add a new contact scenario
#    When User logs in with valid credentials
#    Then Contact List page is open
#    When Add a new contact button is clicked
#    Then Add Contact page is open
#    When New contact created with the following data
#      | First Name        | Stacey              |
#      | Last Name         | Michael             |
#      | Date of Birth     | 1990/12/12          |
#      | Email             | s.michael@gmail.com |
#      | Phone             | 24349431987         |
#      | Street Address 1  | Oak Street          |
#      | Street Address 2  | Pine Street         |
#      | City              | Trinity             |
#      | State or Province | Wonder              |
#      | Postal Code       | 12-432              |
#      | Country           | Wonderland          |
#    Then New contact is displayed on Contact List page
#    Given User logs out
#
#  @DeleteContactsScenario
#  Scenario Outline: Delete contacts scenario
#    When User logs in with valid credentials
#    Then Contact List page is open
#    When User clicks '<Contact>' contact
#    Then Contact Details page is open
#    When User clicks delete button
#    Then There is no '<Contact>' in contact list anymore
#    Given User logs out
#
#    Examples:
#      | Contact        |
#      | Stacey Michael |
#      | Jackie Jackson |
#      | Amanda Langley |