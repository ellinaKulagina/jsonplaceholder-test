@comment
Feature: Test GET method for getting one or multiple comments

  @severity=blocker
  Scenario: User requests all existing comments
    When User requests to see all comments
    Then User gets a list of all comments

  @severity=blocker
  Scenario: User requests existing comment
    When User requests to see a comment with id 1
    Then User gets a valid response for requested comment

  @severity=medium
  Scenario: User requests comments for existing post
    When User requests to see all comments for a post with id 1
    Then User gets a valid response with all comments


  @severity=minor
  Scenario Outline: User requests comments with invalid or non-existing id
    When User requests to see a comment with invalid "<id>"
    Then User gets 404 error
    Examples:
      | id      |
      | 999     |
      | abc     |
      | -1      |
      | 0       |
      | !       |
      | [blank] |