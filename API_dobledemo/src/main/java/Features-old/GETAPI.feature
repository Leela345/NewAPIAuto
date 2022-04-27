Feature: This feature is to check GET data from  API

Scenario Outline: Check if user is able to submit GET API request

Given I want to execute service <https://simple-tool-rental-api.glitch.me>
When I submit the GET request as per the data in Excel Worksheet <EWorksheet>
Then I validate status code
Then I validate mandatory tag in response from Excel Worksheet <EWorksheet>
Then I validate response content


Examples:
|serviceName|EWorksheet|
|GetUsers|0|

