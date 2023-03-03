Feature: Test methods for updating posts

  @severity=blocker
  Scenario: User send updates to the existing post
    When User sends updates to the post
      | body        | title        | userId | postId |
      | update body | udpate title | 1      | 1      |
    Then User gets an updated post with the correct details

  @severity=blocker
  Scenario Outline: User send partial update to the existing post
    Given User has an existing post 1
    When User sends partial update to the post for "<field>"
    Then User gets a partially updated post with the correct details
    Examples:
      | field |
      | body  |
      | title |