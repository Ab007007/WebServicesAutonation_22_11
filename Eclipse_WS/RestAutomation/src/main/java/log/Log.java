package log;

import static org.hamcrest.CoreMatchers.equalTo;

import org.testng.annotations.Test;

import data.GlobalVariables;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import util.BaseClass;

public class Log extends BaseClass{

	
	
	@Test
	public void logHeaders() {
		RestAssured.
		given().
			param("key", GlobalVariables.key).
			param("token", GlobalVariables.token).
				log().headers().
		when().
			get("https://api.trello.com/1/boards/637d98fded95a8015df994b9").
		then().
			assertThat().statusCode(200);
	
	System.out.println("ended");
	
	}
	
	@Test
	public void logParams() {
		RestAssured.
		given().
			param("key", GlobalVariables.key).
			param("token", GlobalVariables.token).
				log().headers().
				log().params().
		when().
			get("https://api.trello.com/1/boards/637d98fded95a8015df994b9").
		then().
			assertThat().statusCode(200);
	
	System.out.println("ended");
	
	}
	
	@Test
	public void logEveryThing() {
		RestAssured.
		given().
			param("key", GlobalVariables.key).
			param("token", GlobalVariables.token).
				log().all().
		when().
			get("https://api.trello.com/1/boards/637d98fded95a8015df994b9").
		then().
			assertThat().statusCode(200).log().all();
	
	System.out.println("ended");
	
	}
	
	
	//@Test(priority = 2)
	public void createCard() {

		System.out.println(RestAssured.baseURI);
		// For POST request we need to pass parameters in required format
		RestAssured.
		given().
			queryParam("key", GlobalVariables.key). // Query parameter
			queryParam("token", GlobalVariables.token).
			queryParam("name", "My_Eclipse_Board1_card1").
			queryParam("desc", "My_Eclipse_Board1_card1_description").
			queryParam("idList", "637f0c5c3808060030026328").
			headers("Content-Type", "application/json"). // header
			log().headers().
																												// parameter
		when().
			post("/1/cards"). // endpoint
		then().
			assertThat().statusCode(200).and().
			contentType(ContentType.JSON).and().
			body("name", equalTo("My_Eclipse_Board1_card1")).log().ifValidationFails(); // using static imports here
	}
}
