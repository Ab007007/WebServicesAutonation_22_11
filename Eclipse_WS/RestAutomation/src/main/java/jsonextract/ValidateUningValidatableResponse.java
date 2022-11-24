package jsonextract;

import org.testng.Assert;
import org.testng.annotations.Test;

import data.GlobalVariables;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import util.BaseClass;

public class ValidateUningValidatableResponse extends BaseClass 
{
	@Test
	public void validateJsonResponse() {
		ValidatableResponse responseBody =
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
			
		
		System.out.println(" ID " + responseBody.extract().path("id"));
		System.out.println(" NAME " + responseBody.extract().path("name"));
		System.out.println(" prefs " + responseBody.extract().path("prefs"));
		System.out.println(" prefs.permissionLevel " + responseBody.extract().path("prefs.permissionLevel"));
		System.out.println(" prefs.switcherViews " + responseBody.extract().path("prefs.switcherViews"));
		System.out.println(" prefs.switcherViews - first ele id " +
						responseBody.extract().path("prefs.switcherViews[0]._id"));
		
		Assert.assertEquals(responseBody.extract().path("name"), "API_22_11");
		System.out.println("ended");
		
			
	}
}
