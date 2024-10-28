package day3;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

public class CookiesDemo {
	@Test
	void testCookies() {
		given()
		
		.when()
			.get("https://www.google.com/")
		
		.then()
			.cookie("AEC","Value")
			.log().all();
	}
	
	@Test
	void getCookiesInfo() {
		Response res = given()
		
		.when()
			.get("https://www.google.com/");
		
		//get single cookie info
		String cookie_value = res.getCookies("AEC");
		System.out.println("Value of the cookies is"+cookie_value);
		
		//get all cookies info
		Map<String,String> cookies_values = res.getCookies();
		//System.out.println(cookies_values.keySet());
		
		for(String k:cookies_values.keySet()) {
			String cookie_values=res.getCookie(k);
			System.out.println(k+"      "+cookie_value);
			
		}
		
	}

}
