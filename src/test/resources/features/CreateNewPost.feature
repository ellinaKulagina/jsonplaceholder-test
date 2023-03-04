@post
Feature: Test POST method for creating new posts

  @severity=blocker
  Scenario: User creates a new post successfully
    When User creates a new post
      | body      | title      | userId |
      | test body | test title | 1      |
    Then User can see a new post with the correct details

  @severity=medium
  Scenario: User tries to create a new post with invalid userId
    When User tries to create an invalid new post
      | body      | title      | userId |
      | test body | test title | -1     |
    Then User gets 500 error

  @severity=medium
  Scenario: User tries to create a new post with empty title and body
    When User tries to create an invalid new post
      | body    | title   | userId |
      | [blank] | [blank] | 1      |
    Then User gets 500 error
