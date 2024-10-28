package Day5;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ParsingXMLResponse {
	@Test
	void TestXMLResponse() {
		//Approach1
//		given()
//		
//		.when()
//			.get("https://thetestrequest.com/authors.xml")
//		.then()
//			.statusCode(200)
//			.header("Content-Type","application/xml; charset=utf-8")
//			 .body("objects.object.name", hasItems(
//                     "Karl Zboncak",
//                     "Jeffie Wolf I",
//                     "Dede Tillman",
//                     "Gracia Keeling",
//                     "Leonard Champlin"))
//		
//			.body("objects.object[0].name",equalTo("Karl Zboncak"));
		
		//Approac2 - Returning the responses using a variable
		Response res = 
		given()
		
		.when()
			.get("https://thetestrequest.com/authors.xml");
			
			Assert.assertEquals(res.getStatusCode(), 200);
			Assert.assertEquals(res.header("Content-Type"), "application/xml; charset=utf-8");
			
			String name = res.xmlPath().get("objects.object[0].name").toString();
			Assert.assertEquals(name, "Karl Zboncak");
		
	}
	@Test
	void testXMLResponseBody() {
		Response res =
		given()
		
		.when()
		.get("https://thetestrequest.com/authors.xml");
		
		//create xml path variable
		XmlPath xmlobj = new XmlPath(res.asString());
		//total no of data in the response
		//ToString - when we want to convert the data into string
		//AsString - When we want to convert the whole response into string
		List<String> authors = xmlobj.getList("objects.object");
		Assert.assertEquals(authors.size(), 5);
		
		//verify author name is present in response
		List<String> author_names = xmlobj.getList("objects.object.name");
		
		boolean status = false;
		for(String authorname:author_names) {
			//System.out.println(authorname); - to print all the author names
			//to validate the author name add boolean statement and if condition
			if(authorname.equals("Dede Tillman")) {
				status=true;
				break;
			}
		}
		Assert.assertEquals(status, true);
				

	}
}
