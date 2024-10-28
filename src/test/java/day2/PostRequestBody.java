package day2;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;


public class PostRequestBody {
	
	//1. post request body using hashmap
	//@Test
	void testPostUsingHashmap() {
		HashMap data = new HashMap();
		
		data.put("name", "alok");
		data.put("location", "chd");
		data.put("phone", "63485");
		
		//to pass multiple values create an array and pass the  values then use the same
		String courseArr[] = {"C","C++"};
		data.put("courses", "courseArr");
		
		given()
			.contentTypes("application/json")
			.body(data)
		
		.when()
			.post("https://localhost:3000/students")
		
		.then()
			.statusCode(201)
			.body("name",equalTo("alok"))
			.body("location",equalTo("chd"))
			.body("phone",equalTo("63485"))
			.body("courses[0]",equalTo("C"))
			.body("courses[1]",equalTo("C++"))
			.header("Content-Type","application/json; charset=utf-8")
			.log().all();	
	}
	//post request using org.json library
	//@Test
	void testPostUsingOrgJson() {
		JSONObject data = new JSONObject();
		
		data.put("name", "alok");
		data.put("location", "chd");
		data.put("phone", "63485");
		
		//to pass multiple values create an array and pass the  values then use the same
		String courseArr[] = {"C","C++"};
		data.put("courses", "courseArr");
		
		given()
			.contentTypes("application/json")
			.body(data.toString())
		
		.when()
			.post("https://localhost:3000/students")
		
		.then()
			.statusCode(201)
			.body("name",equalTo("alok"))
			.body("location",equalTo("chd"))
			.body("phone",equalTo("63485"))
			.body("courses[0]",equalTo("C"))
			.body("courses[1]",equalTo("C++"))
			.header("Content-Type","application/json; charset=utf-8")
			.log().all();	
	}
	//post request using POJO class
		@Test
		void testPostUsingPojo() {
			
			POJO_Post data = new POJO_Post();
			data.setName("alok");
			data.setLocation("US");
			data.setPhone("256859");
			String courseArr[]= {"C","C++"};
			data.setCourse(courseArr);
			
			
			given()
				.contentTypes("application/json")
				.body(data)
			
			.when()
				.post("https://localhost:3000/students")
			
			.then()
				.statusCode(201)
				.body("name",equalTo("alok"))
				.body("location",equalTo("chd"))
				.body("phone",equalTo("63485"))
				.body("courses[0]",equalTo("C"))
				.body("courses[1]",equalTo("C++"))
				.header("Content-Type","application/json; charset=utf-8")
				.log().all();	
		}
		//post request using external json file
				@Test
				void testPostUsingJson() {
					File f=new File(".\\body.json");
					FileReader fr = new FileReader(f);
					JSONTokener jt = new JSONTokener(fr);
					JSONObject data = new JSONObject(jt);
					
					
					given()
						.contentTypes("application/json")
						.body(data.toString())
					
					.when()
						.post("https://localhost:3000/students")
					
					.then()
						.statusCode(201)
						.body("name",equalTo("alok"))
						.body("location",equalTo("chd"))
						.body("phone",equalTo("63485"))
						.body("courses[0]",equalTo("C"))
						.body("courses[1]",equalTo("C++"))
						.header("Content-Type","application/json; charset=utf-8")
						.log().all();	
				}	
		
	@Test(priority=2)
	void deleteStudent() {
		given()
		
		.when()
			.delete("https://localhost:3000/students/4")
		
		.then()
			.statusCode(200);
		
	}
	

}
