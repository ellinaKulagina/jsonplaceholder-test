<h1 align="center">JSON placeholder tests</h1>

## :dart: About ##
This simple test framework is created to test REST API from https://jsonplaceholder.typicode.com/
Tests cover all main functionalities from the [guide page](https://jsonplaceholder.typicode.com/guide/)


## :rocket: Technologies ##
- Java 14 - to support lambda functions
- [Cucumber](https://cucumber.io/) - to support BDD
- [RestAssured](https://rest-assured.io/) - to work with API requests&responses
- [Allure](https://qameta.io/allure-report/) - to generate a html report
- [Assertj](https://github.com/assertj) - to write simple and clean assertions
- [Owner](http://owner.aeonbits.org/docs/welcome/) - to handle properties
- [Lombok](https://projectlombok.org/) - to reduce boilerplate code

## :arrow_forward: Running tests ##
```mvn clean test``` command will run all tests together
```mvn clean test -Dcucumber.filter.tag="@comment"``` command will run tests with selected tags

## :eyeglasses: Generating report ##
1. Manually create allure-results folder in your target folder
2. ```cd target``` and run ```allure serve``` command
3. Enjoy your auto generated html report in browser