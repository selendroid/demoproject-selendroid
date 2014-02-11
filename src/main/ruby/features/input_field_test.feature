Feature: Input field test

As a Rubyist, I want to run the First Test at http://selendroid.io/setup.html in a language I understand

Scenario: Get text
Given the input field is enabled
When I put Selendroid in the input field
And fetch the text
Then it should be Selendroid