package Day6;

import org.testng.annotations.Test;

import io.restassured.matcher.RestAssuredMatchers;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class XMLSchemaValidation {
	@Test
	void xmlSchemaValidation() {
		given()
		
		.when()
			.get("https://thetestrequest.com/authors.xml")
		
		.then()
			.assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("authors.xsd"));
	}

}
