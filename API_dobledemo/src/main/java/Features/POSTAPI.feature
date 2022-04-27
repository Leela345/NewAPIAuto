Feature: This feature is to check POST API

Scenario Outline: Check if user is able to submit POST service

Given I want to execute service <serviceName>
When I the POST request as per the data in Excel Worksheet <EWorksheet>
Then I validate status code
Then I validate mandatory tag in response from Excel Worksheet <EWorksheet>
Then I validate response content
Then I validate header parameter in response

Examples:

|serviceName|EWorksheet|
|CreateOrder|1|

