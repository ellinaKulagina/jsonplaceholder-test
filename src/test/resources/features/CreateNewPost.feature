Feature: Test POST method for creating new posts

  @severity=blocker
  Scenario: User creates new post successfully
    When User creates a new post
      | body      | title      | userId |
      | test body | test title | 1      |
    Then User can see a new post with the correct details
