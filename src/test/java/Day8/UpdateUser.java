package Day8;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import org.json.JSONObject;
import org.testng.annotations.Test;

public class UpdateUser {
	@Test
	void testUpdateUser(ITestContext context) {
		  Faker faker = new Faker();
	        JSONObject data = new JSONObject();
	        data.put("name", faker.name().fullName());
	        data.put("gender", "Male");
	        data.put("email", faker.internet().emailAddress());
	        data.put("status", "active");

	        // Bearer token for authorization
	        String bearerToken = "c8a82c5aa13e5f81cf88095406c40d0ae106cd5fb9b0f01a197eb6812528a2dd";

	        //int id = (int) context.getAttribute("user_id");
	        int id = (int) context.getSuite().getAttribute("user_id");
	        // Send POST request and capture the response
	        given()
	            .headers("Authorization", "Bearer " + bearerToken)
	            .contentType("application/json")
	            .body(data.toString())
	            .pathParam("id", id)
	        .when()
	            .put("https://gorest.co.in/public/v2/users/{id}")
	        .then()
	        	.statusCode(200)
	        	.log().all();
	        	
	}
}
