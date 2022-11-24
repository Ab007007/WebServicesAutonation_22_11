package postRequest;

import static org.hamcrest.CoreMatchers.equalTo;

import org.testng.annotations.Test;

import data.GlobalVariables;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import util.BaseClass;

public class CreateBoard extends BaseClass {

	
	@Test
	public void createBoard() {
		System.out.println(RestAssured.baseURI);

		RestAssured.
			given().
				queryParam("key", GlobalVariables.key).
				queryParam("token", GlobalVariables.token).
				queryParam("name", "My_Eclipse_Board").
				headers("Content-Type", "application/json").
			when().
				post("/1/boards/").
			then().
				assertThat().statusCode(200).and().
				contentType(ContentType.JSON).and().
				body("name" , equalTo("My_Eclipse_Board"));			
		
	
	}
}
