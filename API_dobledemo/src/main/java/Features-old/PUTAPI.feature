Feature: This feature is to check PUT data in API

Scenario Outline: Check if user is able to modify data using  PUT service

Given I want to execute service <serviceName>
When I the POST request as per the data in Excel Worksheet <EWorksheet>
And I validate status code
And I validate mandatory tag in response from Excel Worksheet <EWorksheet>
And I validate response content
And I validate header parameter in response

Examples:

|serviceName|EWorksheet|
|CreateUser|1|

