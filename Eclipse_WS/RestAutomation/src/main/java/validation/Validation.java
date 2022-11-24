package validation;

import static org.hamcrest.CoreMatchers.equalTo;


import org.testng.annotations.Test;

import data.GlobalVariables;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class Validation {

	@Test
	public void getBoardTest() {
		RestAssured.baseURI = "https://api.trello.com";
//		RestAssured.port = 8080;
		RestAssured.basePath = "/1/boards/637d98fded95a8015df994b9";
		RestAssured.
			given().
				param("key", GlobalVariables.key).
				param("token", GlobalVariables.token).
			when().
				get().
			then().
				assertThat().statusCode(200).and().
				contentType(ContentType.JSON).and().
				body("id", equalTo("637d98fded95a8015df994b9")).and().
				body("name", equalTo("API_22_22"));
		
		
		RestAssured.reset();
		System.out.println("ended");
	
	}
}
