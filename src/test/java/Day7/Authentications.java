package Day7;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class Authentications {
	@Test
	void testBasicAuth() {
		given()
			.auth().basic("postman", "password")
			
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated",equalTo(true))
			.log().all();
	}
	@Test
	void testDigestAuth() {
		given()
			.auth().digest("postman", "password")
			
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated",equalTo(true))
			.log().all();
	}
	@Test
	void testPreemtiveAuth() {
		given()
			.auth().preemptive().basic("postman", "password")
			
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated",equalTo(true))
			.log().all();
	}
	//@Test
	void bearerTokenAuth() {
		String bearerToken = "ksjjhuid";
		given()
			.headers("Authorisation","Bearer "+bearerToken)
		.when()
			.get("url")
			
		.then()
			.statusCode(200)
			.log().all();
	}
	@Test
	void OAuth1() {
		given()
			.auth().oauth("consumerKey", "consumerSecret", "acessToken", "tokenSecret")
		.when()
			.get("url")
		.then()
			.statusCode(200)
			.log().all();
	}
	@Test
	void testOAuth2() {
		given()
		.auth().oauth2("oauth2 token")
	.when()
		.get("url")
	.then()
		.statusCode(200)
		.log().all();
	}
	@Test
	void testAPIKeyAuthentication() {
		given()
			.queryParam("keyname", "keyvalue")
		.when()
			.get("url")
		.then()
			.statusCode(200)
			.log().all();
	}
	
}
