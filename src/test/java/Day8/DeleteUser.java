package Day8;

import org.testng.ITestContext;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import org.json.JSONObject;
import org.testng.annotations.Test;

public class DeleteUser {
	@Test
	void testDeleteUser(ITestContext context) {
		String bearerToken = "c8a82c5aa13e5f81cf88095406c40d0ae106cd5fb9b0f01a197eb6812528a2dd";
        
		//int id = (int) context.getAttribute("user_id");
		 int id = (int) context.getSuite().getAttribute("user_id");
        
        given()
        .headers("Authorization", "Bearer " + bearerToken)
        .pathParam("id", id)
        
        .when()
        	.delete("https://gorest.co.in/public/v2/users/{id}")
        .then()
        	.statusCode(204)
        	.log().all();
	}

}
