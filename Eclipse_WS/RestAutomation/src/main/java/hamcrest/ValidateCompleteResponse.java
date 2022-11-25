package hamcrest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.testng.annotations.Test;

import data.GlobalVariables;
import io.restassured.RestAssured;

public class ValidateCompleteResponse {

	
	
	@Test
	public void validateResponse() throws IOException, JSONException 
	{
		
		String expectedValues = new String(Files.readAllBytes
				(Paths.get("responsedata\\apiboard_response.txt")));
		System.out.println("Expected Result : "  + expectedValues);
		String actualValues = 
				RestAssured.
		 given().
			param("key", GlobalVariables.key).
			param("token", GlobalVariables.token).
				log().headers().
				log().params().
		when().
			get("https://api.trello.com/1/boards/637d98fded95a8015df994b9").asString();
		
		JSONAssert.assertEquals(expectedValues, actualValues, true);
	
	}
}
