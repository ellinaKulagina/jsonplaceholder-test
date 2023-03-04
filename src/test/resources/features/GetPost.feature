@post
Feature: Test GET method for getting one or multiple posts

  @severity=blocker
  Scenario: User requests all existing posts
    When User requests to see all posts
    Then User gets a list of all posts

  @severity=blocker
  Scenario: User requests existing post
    When User requests to see a post with id 1
    Then User gets a valid response for requested post

  @severity=medium
  Scenario: User requests all posts for a specific user
    When User requests to see all posts for a user 1
    Then User gets a list of posts for the specified user

  @severity=minor
  Scenario Outline: User requests post with invalid or non-existing id
    When User requests to see a post with invalid "<id>"
    Then User gets 404 error
    Examples:
      | id      |
      | 999     |
      | abc     |
      | -1      |
      | 0       |
      | !       |
      | [blank] |

  @severity=minor
  Scenario Outline: User requests all posts for an invalid or non-existing user
    When User requests to see all posts for a user with invalid "<id>"
    Then User gets empty response
    Examples:
      | id      |
      | 999     |
      | abc     |
      | -1      |
      | 0       |
      | !       |
      | [blank] |
