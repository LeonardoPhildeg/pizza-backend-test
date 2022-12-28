Feature: Category

  Scenario Outline: Create new category success
    Given I provide the <name> for new category
    When I call category endpoint
    Then the response body should contain name, id
    And status code is <code>
      Examples:
        | name    | code |
        | "Pizza" | 200  |
        | "Suco"    | 200  |

  Scenario Outline: Create new category without authenticated user
    Given I provide the <name> for new category with not authenticated user
    When I call category endpoint
    Then I should not have response body
    And status code is <code>
      Examples:
        | name    | code |
        | "Pizza" | 401  |
