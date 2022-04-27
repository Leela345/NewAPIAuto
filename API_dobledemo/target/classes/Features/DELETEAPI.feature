Feature: This feature is to Delete  data from  API

Scenario Outline: Check if user is able to delete data from  API 

Given I want to execute service <serviceName>
When I submit the Delete Mentioned request as per the data in Excel Worksheet <EWorksheet>
Then I validate status code


Examples:
|serviceName|EWorksheet|
|DeleteOrder|4|

