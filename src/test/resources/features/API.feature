Feature: Login to Thinking Tester platform

  Background:
    Given User opens Thinking Tester platform login page

  @GetContactByAPIScenario
  Scenario: Login with valid and invalid credentials
    When User logs in with valid credentials
    Then Contact List page is open
    Given Authorization token extracted from cookies
    When GET request is sent to retrieve contact data for id '6648ef10f6e13900133f9eb2'
    Given User logs out
    Given Browser is closed