package Day6;

import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class JsonSchemaValidation {
	@Test
	void jsonSchemaValidation() {
		
		given()
		
		.when()
			.get("https://reqres.in/api/users?page=2")
		.then()
			.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("userlistschema.json"));
	}
}
