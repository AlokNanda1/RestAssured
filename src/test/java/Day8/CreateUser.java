package Day8;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;

import io.restassured.response.Response;

public class CreateUser {
    @Test
    void test_CreateUser(ITestContext context) { //used ITestContext to make the id available in other classes after creating user
        // Create fake data for user creation
        Faker faker = new Faker();
        JSONObject data = new JSONObject();
        data.put("name", faker.name().fullName());
        data.put("gender", "Male");
        data.put("email", faker.internet().emailAddress());
        data.put("status", "inactive");

        // Bearer token for authorization
        String bearerToken = "c8a82c5aa13e5f81cf88095406c40d0ae106cd5fb9b0f01a197eb6812528a2dd";

        // Send POST request and capture the response
        Response response = given()
            .headers("Authorization", "Bearer " + bearerToken)
            .contentType("application/json")
            .body(data.toString())
        .when()
            .post("https://gorest.co.in/public/v2/users");

        // Check the status code to ensure the request was successful
        response.then().statusCode(201);  // Expecting 201 Created

        // Extract the 'id' from the JSON response
        int id = response.jsonPath().getInt("id");

        // Print the generated user ID
        System.out.println("Generated ID is: " + id);
        //context.setAttribute("user_id", id);
        //to make the id available at the suite level we can use
        context.getSuite().setAttribute("user_id", id);
    }
}
