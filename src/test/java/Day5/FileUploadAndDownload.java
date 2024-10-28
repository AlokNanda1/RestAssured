package Day5;

import java.io.File;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class FileUploadAndDownload {
	 @Test
	    void singleFileUpload() {
	        File myfile = new File("C:\\Users\\Cybrain Software\\Downloads\\The Essence of Software Testing.pdf");

	        given()
	            .multiPart("file", myfile)
	            .contentType("multipart/form-data")
	        .when()
	            .post("https://file.io")
	        .then()
	            .statusCode(200)
	            .body("success", equalTo(true))  // Validate the success field
	            .log().all();  // Log the response
	}
	 
	 //for uploading and validating multiple files
	 //@Test
	 void multipleFileUpload() {
		 
		 //by using array we can pass multiple files at once inside the array
		 File myfile1 = new File("C:\\Users\\Cybrain Software\\Downloads\\The Essence of Software Testing.pdf");
		 File myfile2 = new File("C:\\Users\\Cybrain Software\\Downloads\\Sept Update.pdf");

	        given()
	            .multiPart("files", myfile1)
	            .multiPart("files", myfile2)
	            .contentType("multipart/form-data")
	        .when()
	            .post("https://file.io")
	        .then()
	            .statusCode(200)
	            .body("success", equalTo(true))  // Validate the success field
	            .log().all();  // Log the response
	 }
	 @Test(priority=2)
	 void formDownload() {
		 given()
		 
		 .when()
		 	.get("https://file.io/downloadFile/Sept Update.pdf")
		 
		 .then()
		 .statusCode(200)
		 .log().all();
	 }

}
