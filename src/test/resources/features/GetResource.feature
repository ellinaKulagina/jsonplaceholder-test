Feature: Test GET method for getting one or multiple posts

  @severity=blocker
  Scenario: User requests existing post
    When User requests to see a post with id 1
    Then User gets a valid response for requested post

  @severity=medium
  Scenario: User requests all existing posts
    When User requests to see all posts
    Then User gets a list of all posts

  @severity=minor
  Scenario Outline: User requests post with invalid or non-existing id
    When User requests to see a post with invalid "<id>"
    Then User gets 404 error
    Examples:
      | id      |
      | 999     |
      | abc     |
      | !       |
      | [blank] |
