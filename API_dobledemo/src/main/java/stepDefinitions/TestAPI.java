package stepDefinitions; 

import cucumber.api.java.en.Given;

import cucumber.api.java.en.Then;

import cucumber.api.java.en.When;

import org.apache.log4j.Logger;
import org.testng.Assert;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.List;

import ExcelOperations.BaseExcel;

import io.restassured.RestAssured;

import io.restassured.response.Response;

import io.restassured.response.ResponseBody;


import io.restassured.specification.RequestSpecification; 

public class TestAPI { 

	static Logger log = Logger.getLogger(TestAPI.class);

	public BaseExcel excel= new BaseExcel(); //To create an object of BaseExcel Class

	public String[][] requestData; //This is the 2D string array which will contain all the data from Input excel for a particular worksheet

	Response response=null;

			RequestSpecification request;   

			//String filepath= "C:/Users/ha295e/Desktop/API_Data/test.xlsx";  
			String filepath= "C:\\Automation Stuff\\API Automation\\Leela_API-master\\API_Demo\\API_dobledemo\\TestData\\test.xlsx";
			
			@Given("^I want to execute service (.+)$")

			public void givenIwantToexecuteSercive(String service) {

				log.info("We are going to execute service " +service);       

			} 

			@When("^I submit the GET request as per the data in Excel Worksheet (.+)$")

			public void whenIsubmitgetrequest(String st2) throws IOException {


				int no=Integer.parseInt(st2); //Converting the value of Excel worksheet to Integer

				requestData=excel.readExcel(filepath, no); //Capturing the input data from excel to 2D string array

				String baseURI=requestData[1][1]; //Capturing the value of baseURI from excel into variable

				int count=requestData.length; //Initializing the variable with array length

				String [] Qparam=new String[count];

				String [] Qvalue=new String[count];

				String SERVICE_ENDPOINT = null;

				int chk=0;

				//Following loop will capture all the Query parameter name and value in Qparam and Qvalue array respectively.
				for (int i=1;i<count;i++)

				{

					if(!(requestData[i][2].isEmpty()))

					{

						Qparam[i]=requestData[i][2];

						Qvalue[i]=requestData[i][3];

						chk++;

					}                                         

				}

				//Following if condition and for loop build the resulting API URI based onthe query parameters received from the input excel.

				SERVICE_ENDPOINT =baseURI;

				String Query="";

				if(chk==1)

				{

					SERVICE_ENDPOINT = baseURI+"?"+requestData[1][2]+"="+requestData[1][3];

				}

				if(chk>1)

				{

					SERVICE_ENDPOINT = baseURI+"?"+requestData[1][2]+"="+requestData[1][3];
					for(int i=1;i<chk;i++)

					{
						Query = Query.concat("&"+requestData[i+1][2]+"="+requestData[i+1][3]);

						SERVICE_ENDPOINT = SERVICE_ENDPOINT.concat(Query);

					}

				}               

				request = RestAssured.given();

				request.header("header1","User1"); //Passed the value of header parameter

				request.header("header2","User2");//Passed the value of header parameter

				response = request.when().get(SERVICE_ENDPOINT);//Submit the request



				ResponseBody body = response.getBody();                            

				excel.writeExcel(filepath, no, 10, 1, response.prettyPrint());//Print the service response in excel 


			}


			@SuppressWarnings("deprecation")

			@Then("^I validate status code$")  

			public void whenIvalidateStatusCode()  {

				String status1=requestData[1][5];
				
				String status=status1.replace(".0","");
				//Capture the expected value of status code passed in input excel

				int statusCode = response.getStatusCode();
				//Capture the resulting status code after submitting the request

				int code=Integer.parseInt(status);

				Assert.assertEquals(code, statusCode);


			} 

			@Then("^I validate mandatory tag in response from Excel Worksheet (.+)$")

			public void validateMandatoryTag(String st2) throws IOException {

				int no=Integer.parseInt(st2);

				int rowcount=requestData.length;

				ResponseBody body = response.getBody();

				String bodyAsString = body.asString();

				for(int i=1;i<rowcount;i++)

				{

					String Mandatoryfield=requestData[i][6];//Capture the mandatory tag names from input excel
					Boolean value=bodyAsString.contains(Mandatoryfield);
					//To verify if mandatory tag is present in response.
					if(value)
					{
						excel.writeExcel(filepath, no, 7, i, "Mandatory Tag Present"); 
						//This will write the result in excel.

					}
					else

					{

						excel.writeExcel(filepath, no, 7, i, "Mandatory Tag Not Present"); 
						//This will write the result in excel.

					}

				}

			}   

			@Then("^I validate response content$")

			public void i_validate_response_content() throws InterruptedException{

				String contentType = response.header("Content-Type");

				assertEquals(contentType, "application/json; charset=utf-8");
				//To verify if the response is coming in valid JSON format

			} 

			@Then("^I validate tag values in response from Excel Worksheet (.+)$")

			public void i_validate_tag_values_in_response(String st2) throws InterruptedException, IOException{

				int no=Integer.parseInt(st2);

				int rowcount=requestData.length;

				for(int i=1;i<rowcount;i++)

				{

					String tagname=requestData[i][6];

					//String val =(response.path(requestData[1][4]+"."+tagname));
					
					String val =(response.path(tagname).toString());
					//To capture the value of the tags provided in input excel

					String Exval=requestData[i][8];

					if(val.contains(Exval))

					{

						excel.writeExcel(filepath, no, 9, i, "Tag Value Matched"); 
						//This will write the result in excel.

					}
					else

					{
						excel.writeExcel(filepath, no, 9, i, "Tag Value Does Not Matched,Value obtained is "+val); 
						//This will write the result in excel.

					}

				}


			}

			@Then("^I validate header parameter in response$")

			public void I_validate_header_parameter_in_response(){

				String journeyId = response.header("Content-Type");

				assertEquals(journeyId, "application/json; charset=utf-8"); //To validate the value of header in response.

			} 

			@When("^I the POST request as per the data in Excel Worksheet (.+)$")

			public void I_submit_POST_request(String st2) throws IOException {              

				int no=Integer.parseInt(st2);

				requestData=excel.readExcel(filepath, no);

				String FileName=requestData[1][1];//To capture the value of Json file name in variable

				String baseURI=requestData[1][2];//To capture the value of baseURI in variable     

				//File file = new File("C:/Users/ha295e/Desktop/API_Data/"+FileName);
				File file = new File("C:\\Automation Stuff\\API Automation\\Leela_API-master\\API_Demo\\API_dobledemo\\TestData\\"+FileName);
				
				
				response=RestAssured.given().header("Authorization", "Bearer "+"ee52ce825d7745e000b213429880ae185c9ed7a99194f0c8fa4e269a0ecd600a").body(file).with().
						contentType("application/json").then().expect().when().post(baseURI);//Invoke the POST request
												
				int Response_Post  = response.getStatusCode();
				
				if (Response_Post == 500)
					
				{
					excel.writeExcel(filepath, no, 8, 1, "Data Already Exist "+ Response_Post +" -Internal server error");//To print the response in excel//To print the response in excel 
				}
				else
				{
					String bodyAsString = response.asString();

					excel.writeExcel(filepath, no, 8, 1, bodyAsString);//To print the response in excel 
				
					
				}
				
		
			}
			
			//////
			
			@When("^I submit the delete request as per the data in Excel Worksheet (.+)$")

			public void whenIsubmitdeleterequest(String st2) throws IOException {


				int no=Integer.parseInt(st2); //Converting the value of Excel worksheet to Integer

				requestData=excel.readExcel(filepath, no); //Capturing the input data from excel to 2D string array

				String baseURI=requestData[1][1]; //Capturing the value of baseURI from excel into variable

				int count=requestData.length; //Initializing the variable with array length

				String [] Qparam=new String[count];

				String [] Qvalue=new String[count];

				String SERVICE_ENDPOINT = null;

				int chk=0;

				//Following loop will capture all the Query parameter name and value in Qparam and Qvalue array respectively.
				for (int i=1;i<count;i++)

				{

					if(!(requestData[i][2].isEmpty()))

					{

						Qparam[i]=requestData[i][2];

						Qvalue[i]=requestData[i][3];

						chk++;

					}                                         

				}

				//Following if condition and for loop build the resulting API URI based on the query parameters received from the input excel.

				SERVICE_ENDPOINT =baseURI;

				String Query="";

				if(chk==1)

				{

					SERVICE_ENDPOINT = baseURI+"/"+requestData[1][2];

				}

				if(chk>1)

				{

					SERVICE_ENDPOINT = baseURI+"?"+requestData[1][2]+"="+requestData[1][3];
					for(int i=1;i<chk;i++)

					{
						Query = Query.concat("&"+requestData[i+1][2]+"="+requestData[i+1][3]);

						SERVICE_ENDPOINT = SERVICE_ENDPOINT.concat(Query);

					}

				}               

				request = RestAssured.given();

				request.header("header1","User1"); //Passed the value of header parameter

				request.header("header2","User2");//Passed the value of header parameter

				response = request.when().get(SERVICE_ENDPOINT);//Submit the request
				//int Response_Delete  = 0;
				int Response_Delete  = response.getStatusCode();
				
				if (Response_Delete == 404)
					
				{
					excel.writeExcel(filepath, no, 7, 1,requestData[0][2]+" "+ requestData[1][2] +"  Resource not found");//To print the response in excel 
				}
				else
				{
					response  = request.delete(SERVICE_ENDPOINT);
					excel.writeExcel(filepath, no, 7, 1,requestData[0][2]+" "+ requestData[1][2] +"  Resource Deleted");//To print the response in excel 
					
			    }
		}
			
			///
			
			@When("^I submit the PUT request as per the data in Excel Worksheet (.+)$")

			public void I_submit_PUT_request(String st2) throws IOException {              


				int no=Integer.parseInt(st2); //Converting the value of Excel worksheet to Integer

				requestData=excel.readExcel(filepath, no); //Capturing the input data from excel to 2D string array

				String baseURI=requestData[1][1]; //Capturing the value of baseURI from excel into variable

				int count=requestData.length; //Initialising the variable with array length

				String [] Qparam=new String[count];

				String [] Qvalue=new String[count];

				String SERVICE_ENDPOINT = null;

				int chk=0;

				//Following loop will capture all the Query parameter name and value in Qparam and Qvalue array respectively.
				for (int i=1;i<count;i++)

				{

					if(!(requestData[i][2].isEmpty()))

					{

						Qparam[i]=requestData[i][2];

						Qvalue[i]=requestData[i][3];

						chk++;

					}                                         

				}

				//Following if condition and for loop build the resulting API URI based onthe query parameters received from the input excel.

				SERVICE_ENDPOINT =baseURI;

				String Query="";

				if(chk==1)

				{

					SERVICE_ENDPOINT = baseURI+"?"+requestData[1][2]+"="+requestData[1][3];

				}

				if(chk>1)

				{

					SERVICE_ENDPOINT = baseURI+"?"+requestData[1][2]+"="+requestData[1][3];
					for(int i=1;i<chk;i++)

					{
						Query = Query.concat("&"+requestData[i+1][2]+"="+requestData[i+1][3]);

						SERVICE_ENDPOINT = SERVICE_ENDPOINT.concat(Query);

					}

				}               

				request = RestAssured.given();

				request.header("header1","User1"); //Passed the value of header parameter

				request.header("header2","User2");//Passed the value of header parameter

				response = request.when().get(SERVICE_ENDPOINT);//Submit the request



				ResponseBody body = response.getBody();                            

				excel.writeExcel(filepath, no, 10, 1, response.prettyPrint());//Print the service response in excel 

			}
			
			////
			
			@SuppressWarnings("deprecation")
			@When("^I submit the PATCH request as per the data in Excel Worksheet (.+)$")

			public void I_submit_PATCH_request(String st2) throws IOException {              

				int no=Integer.parseInt(st2);

				requestData=excel.readExcel(filepath, no);

				String FileName=requestData[1][1];//To capture the value of Json file name in variable

				String baseURI=requestData[1][2];//To capture the value of baseURI in variable     

				//File file = new File("C:/Users/ha295e/Desktop/API_Data/"+FileName);
				File file = new File("C:\\Automation Stuff\\API Automation\\Leela_API-master\\API_Demo\\API_dobledemo\\TestData\\"+FileName);
				
				
			//	response=RestAssured.given().header("Authorization", "Bearer "+"ee52ce825d7745e000b213429880ae185c9ed7a99194f0c8fa4e269a0ecd600a").body(file).with().
					//	contentType("application/json").pathParam("orderId", "KGzIaQ5EWRpM7lfDiECB6").then().expect().when().patch(baseURI);//Invoke the POST request
				
				response=RestAssured.given().header("Authorization", "Bearer "+"ee52ce825d7745e000b213429880ae185c9ed7a99194f0c8fa4e269a0ecd600a").body(file).with().
							contentType("application/json").pathParam("orderId", "KGzIaQ5EWRpM7lfDiECB6").then().expect().when().patch(baseURI);//Invoke the POST request
												
				int Response_Post  = response.getStatusCode();
				
				if (Response_Post == 500)
					
				{
					excel.writeExcel(filepath, no, 8, 1, "Data Already Exist "+ Response_Post +" -Internal server error");//To print the response in excel//To print the response in excel 
				}
				else
				{
					String bodyAsString = response.asString();

					excel.writeExcel(filepath, no, 8, 1, bodyAsString);//To print the response in excel 
				
					
				}
				
		
			}
			
			
			@When("^I submit the Delete Mentioned request as per the data in Excel Worksheet (.+)$")

			public void I_submit_DELETE_Mentioned_request(String st2) throws IOException {              

				int no=Integer.parseInt(st2);

				requestData=excel.readExcel(filepath, no);

			//	String FileName=requestData[1][1];//To capture the value of Json file name in variable

				String baseURI=requestData[1][1];//To capture the value of baseURI in variable     

				//File file = new File("C:/Users/ha295e/Desktop/API_Data/"+FileName);
			//	File file = new File("C:\\Automation Stuff\\API Automation\\Leela_API-master\\API_Demo\\API_dobledemo\\TestData\\"+FileName);
				
				
			//	response=RestAssured.given().header("Authorization", "Bearer "+"ee52ce825d7745e000b213429880ae185c9ed7a99194f0c8fa4e269a0ecd600a").body(file).with().
					//	contentType("application/json").pathParam("orderId", "KGzIaQ5EWRpM7lfDiECB6").then().expect().when().patch(baseURI);//Invoke the POST request
				
				response=RestAssured.given().header("Authorization", "Bearer "+"ee52ce825d7745e000b213429880ae185c9ed7a99194f0c8fa4e269a0ecd600a").pathParam("orderId", "KGzIaQ5EWRpM7lfDiECB6").delete(baseURI);//Invoke the POST request
												
				int Response_Post  = response.getStatusCode();
				
				if (Response_Post == 500)
					
				{
					excel.writeExcel(filepath, no, 8, 1, "Data Already Exist "+ Response_Post +" -Internal server error");//To print the response in excel//To print the response in excel 
				}
				else
				{
					String bodyAsString = response.asString();

					excel.writeExcel(filepath, no, 8, 1, bodyAsString);//To print the response in excel 
				
					
				}
				
		
			}
}