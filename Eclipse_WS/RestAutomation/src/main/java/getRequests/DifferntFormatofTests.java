package getRequests;

import org.testng.annotations.Test;

import data.GlobalVariables;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class DifferntFormatofTests {

	
	@Test
	public void bddFormat() {
	
		RestAssured.
			given().
				param("key", GlobalVariables.key).
				param("token", GlobalVariables.token).
			when().
				get("https://api.trello.com/1/boards/637d98fded95a8015df994b9").
			then().
				assertThat().statusCode(200);
		
		System.out.println("ended");
		
	}
	
	
	@Test
	public void restAssuredFormat() {
		RequestSpecification rSpec = RestAssured.given();
		rSpec.param("key", GlobalVariables.key);
		rSpec.param("token", GlobalVariables.token);
		Response response = rSpec.get("https://api.trello.com/1/boards/637d98fded95a8015df994b9");
	
		response.prettyPrint();
		
		ValidatableResponse validateRes = response.then();
		validateRes.statusCode(200);
	}
	@Test
	public void givenExpectFormt() {
		RestAssured.
		given().
			param("key", GlobalVariables.key).
			param("token", GlobalVariables.token).
		expect().
			statusCode(200).
		when().
			get("https://api.trello.com/1/boards/637d98fded95a8015df994b9");
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
