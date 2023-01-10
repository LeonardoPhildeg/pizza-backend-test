Feature: User

  @Only
  Scenario Outline: Create new user
    Given I provide email and password
    When I call user endpoint
    Then the response body should contain name, email
    And status code is <code>
      Examples:
      | code |
      | 200  |

  Scenario Outline: User details
    Given I am authenticated
    When I call user detail endpoint
    Then the response body should contain id, name, email
    And status code is <code>
    Examples:
      | code |
      | 200  |