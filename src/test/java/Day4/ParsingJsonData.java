package Day4;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class ParsingJsonData {
	
	@Test(priority=1)
	void testJsonResponse() {
		
		//Approach1
//		given()
//			.contentType("ContentType.JSON")
//		
//		.when()
//			.get("https://reqres.in/api/users?page=2")
//
//		
//		.then()
//			.statusCode(200)
//			.headers("Content-Type","application/json; charset=utf-8")
//			//get the json path of the element you want to get by copying the response to jsonpath finder online
//			.body("data[3].first_name", equalTo("Byron"));
		
		//Approach2
		Response res = given()
			.contentType("ContentType.JSON")
		
		.when()
			.get("https://reqres.in/api/users?page=2");
//			Assert.assertEquals(res.getStatusCode(),200);
//			Assert.assertEquals(res.header("Content-Type"),"application/json; charset=utf-8");
//			
//			String firstname = res.jsonPath().get("data[4].first_name").toString();
//			Assert.assertEquals(firstname, "George");
		//capture and validate data using JSOn object class
		JSONObject jo = new JSONObject(res.toString()); //converting response to json object type
		
		for(int i =0;i<jo.getJSONArray("data").length();i++) 
		{
			String email = jo.getJSONArray("data").getJSONObject(i).get("email").toString();
			System.out.println(email);
		}
		
		
	
	}

}
