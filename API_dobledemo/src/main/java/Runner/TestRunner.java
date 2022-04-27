package Runner;

import java.io.File;

import org.junit.runner.RunWith;
import org.testng.annotations.AfterClass;

import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


	@RunWith(Cucumber.class)
	@CucumberOptions(
			features = "C:\\Automation Stuff\\API Automation\\Leela_API-master\\API_Demo\\API_dobledemo\\src\\main\\java\\FeatureFiles\\GETAPI.feature", //the path of the feature files
			
			//features = "C:\\Automation Stuff\\API Automation\\Leela_API-master\\API_Demo\\API_dobledemo\\src\\main\\java\\Features\\POSTAPI.feature", //the path of the feature files
			
			//features = "C:/Users/ha295e/Desktop/API_Workspace/API_Workspace/src/main/java/Features/DELETEAPI.feature", //the path of the feature files
			  
			//features = "C:\\Automation Stuff\\API Automation\\Leela_API-master\\API_Demo\\API_dobledemo\\src\\main\\java\\Features\\PATCHAPI.feature", //the path of the feature files
			
			//features = "C:\\Automation Stuff\\API Automation\\Leela_API-master\\API_Demo\\API_dobledemo\\src\\main\\java\\Features\\DELETEAPI.feature", //the path of the feature files
			
			glue={"stepDefinitions"}, //the path of the step definition files
			format= {"pretty","html:test-outout", "json:json_output/cucumber.json", "junit:junit_xml/cucumber.xml"}, //to generate different types of reporting
			plugin = {"com.cucumber.listener.ExtentCucumberFormatter:output/report.html"},// plugin to generate extent html report saved in output folder
			monochrome = true, //display the console output in a proper readable format
			strict = true, //it will check if any step is not defined in step definition file
			dryRun = false //to check the mapping is proper between feature file and step def file
			//tags = {"~@SmokeTest" , "~@RegressionTest", "~@End2End"}			
			)
	 
	public class TestRunner 
	
	{
		@AfterClass
	    public static void setup() {
	        Reporter.loadXMLConfig(new File("C:\\Automation Stuff\\API Automation\\Leela_API-master\\API_Demo\\API_dobledemo\\src\\main\\resources\\extent-config.xml"));
	        //Reporter.setSystemInfo("user", System.getProperty("user.name"));
	        //Reporter.setSystemInfo("os", "Mac OSX");
	        Reporter.setTestRunnerOutput("Sample test runner output message");
	    } 
		
	 
	}
	
	//ORed : tags = {"@SmokeTest , @RegressionTest"} -- execute all tests tagged as @SmokeTest OR @RegressionTest
	//ANDed : tags = tags = {"@SmokeTest" , "@RegressionTest"} -- execute all tests tagged as @SmokeTest AND @RegressionTest
	

