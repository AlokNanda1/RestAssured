package Day8;

import org.testng.ITestContext;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetUser {
    @Test
    void getUser(ITestContext context) {
        // Set a valid ID
        int id = (int) context.getSuite().getAttribute("user_id");  // Replace this with a valid user ID
        
        String bearerToken = "c8a82c5aa13e5f81cf88095406c40d0ae106cd5fb9b0f01a197eb6812528a2dd";
        
        given()
            .headers("Authorization", "Bearer " + bearerToken)
            .pathParam("id", id)
        .when()
            .get("https://gorest.co.in/public/v2/users/{id}")
        .then()
            .statusCode(200)  // Expect a 200 response if the user ID is valid
            .log().all();
    }
}
