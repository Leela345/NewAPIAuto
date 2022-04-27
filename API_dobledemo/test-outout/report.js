$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("C:/Automation Stuff/API Automation/Leela_API-master/API_Demo/API_dobledemo/src/main/java/FeatureFiles/GETAPI.feature");
formatter.feature({
  "line": 1,
  "name": "This feature is to check GET data from  API",
  "description": "",
  "id": "this-feature-is-to-check-get-data-from--api",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "line": 3,
  "name": "Check if user is able to submit GET API request",
  "description": "",
  "id": "this-feature-is-to-check-get-data-from--api;check-if-user-is-able-to-submit-get-api-request",
  "type": "scenario_outline",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 5,
  "name": "I want to execute service \u003chttps://simple-tool-rental-api.glitch.me\u003e",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "I submit the GET request as per the data in Excel Worksheet \u003cEWorksheet\u003e",
  "keyword": "When "
});
formatter.step({
  "line": 7,
  "name": "I validate status code",
  "keyword": "Then "
});
formatter.step({
  "line": 8,
  "name": "I validate mandatory tag in response from Excel Worksheet \u003cEWorksheet\u003e",
  "keyword": "Then "
});
formatter.step({
  "line": 9,
  "name": "I validate response content",
  "keyword": "Then "
});
formatter.examples({
  "line": 12,
  "name": "",
  "description": "",
  "id": "this-feature-is-to-check-get-data-from--api;check-if-user-is-able-to-submit-get-api-request;",
  "rows": [
    {
      "cells": [
        "serviceName",
        "EWorksheet"
      ],
      "line": 13,
      "id": "this-feature-is-to-check-get-data-from--api;check-if-user-is-able-to-submit-get-api-request;;1"
    },
    {
      "cells": [
        "GetUsers",
        "0"
      ],
      "line": 14,
      "id": "this-feature-is-to-check-get-data-from--api;check-if-user-is-able-to-submit-get-api-request;;2"
    }
  ],
  "keyword": "Examples"
});
formatter.scenario({
  "line": 14,
  "name": "Check if user is able to submit GET API request",
  "description": "",
  "id": "this-feature-is-to-check-get-data-from--api;check-if-user-is-able-to-submit-get-api-request;;2",
  "type": "scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 5,
  "name": "I want to execute service \u003chttps://simple-tool-rental-api.glitch.me\u003e",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "I submit the GET request as per the data in Excel Worksheet 0",
  "matchedColumns": [
    1
  ],
  "keyword": "When "
});
formatter.step({
  "line": 7,
  "name": "I validate status code",
  "keyword": "Then "
});
formatter.step({
  "line": 8,
  "name": "I validate mandatory tag in response from Excel Worksheet 0",
  "matchedColumns": [
    1
  ],
  "keyword": "Then "
});
formatter.step({
  "line": 9,
  "name": "I validate response content",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "\u003chttps://simple-tool-rental-api.glitch.me\u003e",
      "offset": 26
    }
  ],
  "location": "TestAPI.givenIwantToexecuteSercive(String)"
});
formatter.result({
  "duration": 236914700,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "0",
      "offset": 60
    }
  ],
  "location": "TestAPI.whenIsubmitgetrequest(String)"
});
formatter.result({
  "duration": 8175877100,
  "status": "passed"
});
formatter.match({
  "location": "TestAPI.whenIvalidateStatusCode()"
});
formatter.result({
  "duration": 6492000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "0",
      "offset": 58
    }
  ],
  "location": "TestAPI.validateMandatoryTag(String)"
});
formatter.result({
  "duration": 879011600,
  "status": "passed"
});
formatter.match({
  "location": "TestAPI.i_validate_response_content()"
});
formatter.result({
  "duration": 376600,
  "status": "passed"
});
});