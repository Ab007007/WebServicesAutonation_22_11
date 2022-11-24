package jsonextract;

import org.testng.Assert;
import org.testng.annotations.Test;

import data.GlobalVariables;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import util.BaseClass;

public class ValidateJsonResponse extends BaseClass 
{
	@Test
	public void validateJsonResponse() {
		Response response = RestAssured.
			 given().
				param("key", GlobalVariables.key).
				param("token", GlobalVariables.token).
					log().headers().
					log().params().
			when().
				get("https://api.trello.com/1/boards/637d98fded95a8015df994b9").
			then().
				assertThat().statusCode(200).
			extract().response();
		
		
		JsonPath responseBody = new JsonPath(response.asString());
		
		System.out.println(" ID " + responseBody.get("id"));
		System.out.println(" NAME " + responseBody.get("name"));
		System.out.println(" prefs " + responseBody.get("prefs"));
		System.out.println(" prefs.permissionLevel " + responseBody.get("prefs.permissionLevel"));
		System.out.println(" prefs.switcherViews " + responseBody.get("prefs.switcherViews"));
		System.out.println(" prefs.switcherViews - first ele id " +
						responseBody.get("prefs.switcherViews[0]._id"));
		
		Assert.assertEquals(responseBody.get("name"), "API_22_11");
		System.out.println("ended");
		
			
	}
}
