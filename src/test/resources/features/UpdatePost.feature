@post
Feature: Test methods for updating posts

  @severity=blocker
  Scenario: User sends update to an existing post
    When User sends updates to the post
      | body        | title        | userId | postId |
      | update body | update title | 1      | 1      |
    Then User gets an updated post with the correct details

  @severity=blocker
  Scenario Outline: User sends a partial update to an existing post
    Given User has an existing post 1
    When User sends partial update to the post for "<field>"
    Then User gets a partially updated post with the correct details
    Examples:
      | field |
      | body  |
      | title |