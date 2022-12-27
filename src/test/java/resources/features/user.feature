Feature: User

  Scenario: Create new user
    Given I provide email and password
    When I call user endpoint
    Then the response body should contain name, email
    And status code is 200

  Scenario: User details
    Given I am authenticated
    When I call user detail endpoint
    Then the response body should contain id, name, email
    And status code is 200