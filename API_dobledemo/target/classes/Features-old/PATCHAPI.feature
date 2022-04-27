Feature: This feature is to check PATCH API

Scenario Outline: Check if user is able to submit PATCH service

Given I want to execute service <serviceName>
When I submit the PATCH request as per the data in Excel Worksheet <EWorksheet>
Then I validate status code
Then I validate mandatory tag in response from Excel Worksheet <EWorksheet>


Examples:

|serviceName|EWorksheet|
|UpdateOrder|3|

