@user
Feature: Test GET method for getting one or multiple users

  @severity=blocker
  Scenario: User requests all existing users
    When User requests to see all users
    Then User gets a list of all users

  @severity=blocker
  Scenario: User requests existing user
    When User requests to see a user with id 1
    Then User gets a valid response for requested user

  @severity=minor
  Scenario Outline: User requests user with invalid or non-existing id
    When User requests to see the post with invalid id "<postId>"
    Then User gets 404 error
    Examples:
      | postId  |
      | 999     |
      | abc     |
      | -1      |
      | 0       |
      | !       |
      | [blank] |
      | a/b     |
