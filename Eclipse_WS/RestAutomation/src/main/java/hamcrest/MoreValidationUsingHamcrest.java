package hamcrest;

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import data.GlobalVariables;
import io.restassured.RestAssured;

public class MoreValidationUsingHamcrest {
	
	@Test
	public void validateJsonResponse() {
		RestAssured.
			 given().
				param("key", GlobalVariables.key).
				param("token", GlobalVariables.token).
					log().headers().
					log().params().
			when().
				get("https://api.trello.com/1/boards/637d98fded95a8015df994b9").
			then().
				assertThat().statusCode(200).log().all().
				body("prefs", hasKey("permissionLevel")).and().
				body("prefs.switcherViews[0]",hasKey("_id")).and().
				body("prefs.switcherViews[0]",hasEntry("_id","637d98fded95a8015df994ba"));
			
	}
	
	@Test
	public void complexJson() {
		RestAssured.
			 given().
				param("key", GlobalVariables.key).
				param("token", GlobalVariables.token).
					log().headers().
					log().params().
			when().
				get("https://api.trello.com/1/boards/637d98fded95a8015df994b9").
			then().
				assertThat().statusCode(200).log().all().
				body("prefs", hasKey("permissionLevel")).and().
				body("prefs.switcherViews[0]",hasKey("_id")).and().
				body("prefs.switcherViews.findAll{it._id == \"637d98fded95a8015df994ba\" }",
						hasItems(hasEntry("_id","637d98fded95a8015df994ba")))
				.and().body("prefs.switcherViews.findAll{it._id == \"637d98fded95a8015df994ba\" }",
				hasItems(hasEntry("viewType","Board")));
		
			
	}
	
	
	
	@Test
	public void groovyFunctions() {
		RestAssured.
		 given().
			param("key", GlobalVariables.key).
			param("token", GlobalVariables.token).
				log().headers().
				log().params().
		when().
			get("https://api.trello.com/1/boards/637d98fded95a8015df994b9").
		then().
			assertThat().statusCode(200).log().all().
			body("prefs.size()", equalTo(25)).and().
			body("prefs.size()", greaterThan(10)).and().
			body("prefs.size()", lessThanOrEqualTo(25));
	}
	
	
	@Test
	public void hasItemsDemo() {
		RestAssured.
			 given().
				param("key", GlobalVariables.key).
				param("token", GlobalVariables.token).
					log().headers().
					log().params().
			when().
				get("https://api.trello.com/1/boards/637d98fded95a8015df994b9").
			then().
				assertThat().statusCode(200).log().all().
				body("prefs", hasKey("permissionLevel")).and().
				body("prefs.switcherViews.viewType",hasItems("Board","Table","Calendar","dsadf"));
		
			
	}
	
	
	@Test
	public void allValidation() {
		RestAssured.
			 given().
				param("key", GlobalVariables.key).
				param("token", GlobalVariables.token).
					log().headers().
					log().params().
			when().
				get("https://api.trello.com/1/boards/637d98fded95a8015df994b9").
			then().
				assertThat().statusCode(200).log().all().
				body("prefs", hasKey("permissionLevel")).and().
				body("prefs.switcherViews.viewType",hasItems("Board","Table","Calendar","dsadf")).and().
				body("prefs.size()", equalTo(25)).and().
				body("prefs.size()", greaterThan(10)).and().
				body("prefs.size()", lessThanOrEqualTo(25)).and().
				body("prefs.switcherViews[0]",hasKey("_id")).and().
				body("prefs.switcherViews.findAll{it._id == \"637d98fded95a8015df994ba\" }",
						hasItems(hasEntry("viewType","Board")));
			
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
