Feature: Get a resource

  Scenario: User requests existing post
    Given User requests to see a post with id 1
    Then User gets a valid response for requested post