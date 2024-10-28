package Test;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import java.util.HashMap;

public class HTTPRequestsj {
	int id;

    //@Test
    void getUser() {
        given()
        .when()
            .get("https://reqres.in/api/users?page=2")
        .then()
            .statusCode(200)
            .body("page", equalTo(2))
            .log().all();
    }
   //@Test
    void createUser() {
    	//create data using hashmap, but it is not recommended as the data will be hard coded
    	HashMap data = new HashMap();
    	data.put("name", "alok");
    	data.put("job", "qa");
    	
    	
    	id = given()
    		.contentType("application/json")
    		.body(data)
    	
    	.when()
    		.post("https://reqres.in/api/users")
    		//to capture the id from the response
    		.jsonPath().getInt("id");
    	
//    	.then()
//    		.statusCode(201)
//    		.log().all();
    }
//    @Test(priority=3,dependsOnMethods= {"createUser"})
//    void createUser() {
//    	//create data using hashmap, but it is not recommended as the data will be hard coded
//    	HashMap data = new HashMap();
//    	data.put("name", "harsh");
//    	data.put("job", "tester");
//    	
//    	id = given()
//    		.contentType("application/json")
//    		.body(data)
//    	
//    	.when()
//    		.put("https://reqres.in/api/users/"+id)
//    		//to capture the id from the response
//    		.jsonPath().getInt("id")
//    	
//    	.then()
//    		.statusCode(201)
//    		.log().all();
//    }
    @Test(priority=4)
    void deleteUser() {
    	given()
    	
    	.when()
    		.delete("https://reqres.in/api/users/"+id)
    	
    	.then()
    		.statusCode(204)
    		.log().all();
    }
}
