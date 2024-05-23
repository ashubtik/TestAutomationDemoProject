Feature: API CRUD operations with contacts

  Background:
    Given User opens Thinking Tester platform login page
    When User logs in with valid credentials
    Then Contact List page is open
    Given Authorization token extracted from cookies

  @GetContactAPIScenario
  Scenario: Verify contact response is equal to expected
    When GET request is sent to retrieve contact with id '6648ef10f6e13900133f9eb2'
    Then GET response has status code 200
    And GET response body is equal to expected
      | First Name        | Pretty          |
      | Last Name         | Woman           |
      | Date of Birth     | 2021/12/12      |
      | Email             | woman@gmail.com |
      | Phone             | 1234567         |
      | Street Address 1  | New Jersey      |
      | Street Address 2  | 13 street       |
      | City              | New York        |
      | State or Province | New Jersey      |
      | Postal Code       | 49-300          |
      | Country           | USA             |
    Given User logs out

  @PatchAndPutContactAPIScenario
  Scenario: Validate contact is updated by PATCH and PUT calls
    When PATCH request is sent to partially update contact with id '6648ef10f6e13900133f9eb2'
      | City    | Wroclaw |
      | Country | Poland  |
    Then PATCH response has status code 200
    And PATCH response body is equal to expected
      | First Name        | Pretty          |
      | Last Name         | Woman           |
      | Date of Birth     | 2021/12/12      |
      | Email             | woman@gmail.com |
      | Phone             | 1234567         |
      | Street Address 1  | New Jersey      |
      | Street Address 2  | 13 street       |
      | City              | Wroclaw         |
      | State or Province | New Jersey      |
      | Postal Code       | 49-300          |
      | Country           | Poland          |
    When PUT request is sent to update contact with id '6648ef10f6e13900133f9eb2'
      | First Name        | Pretty          |
      | Last Name         | Woman           |
      | Date of Birth     | 2021/12/12      |
      | Email             | woman@gmail.com |
      | Phone             | 1234567         |
      | Street Address 1  | New Jersey      |
      | Street Address 2  | 13 street       |
      | City              | New York        |
      | State or Province | New Jersey      |
      | Postal Code       | 49-300          |
      | Country           | USA             |
    Then PUT response has status code 200
    And PUT response body is equal to expected
      | First Name        | Pretty          |
      | Last Name         | Woman           |
      | Date of Birth     | 2021/12/12      |
      | Email             | woman@gmail.com |
      | Phone             | 1234567         |
      | Street Address 1  | New Jersey      |
      | Street Address 2  | 13 street       |
      | City              | New York        |
      | State or Province | New Jersey      |
      | Postal Code       | 49-300          |
      | Country           | USA             |
    Given User logs out

  @PostAndDeleteContactAPIScenario
  Scenario: Validate contact is created by POST and then deleted by DELETE calls
    When POST request is sent to create a new contact from JSON file 'contact.json'
    Then POST response has status code 201
    And POST response body is equal to the contact from JSON file
    When DELETE request is sent to delete created contact
    Then DELETE response has status code 200
    When GET request is sent to retrieve contact with id 'deleted'
    Then GET response has status code 404
    Given User logs out
    Given Browser is closed