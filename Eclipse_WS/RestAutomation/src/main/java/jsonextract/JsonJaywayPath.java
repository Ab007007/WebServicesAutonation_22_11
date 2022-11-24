package jsonextract;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import data.GlobalVariables;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import util.BaseClass;

public class JsonJaywayPath extends BaseClass
{
	
	@Test
	public void validateUsingJayWayLibrary() {
		Response response = RestAssured.
				 given().
					param("key", GlobalVariables.key).
					param("token", GlobalVariables.token).
						log().headers().
						log().params().
				when().
					get("1/boards/637d98fded95a8015df994b9").
				then().
					assertThat().statusCode(200).
				extract().response();
		
		String jsonString = response.asString();
		
		Map<String, ?> rootElement = JsonPath.read(jsonString, "$");
		System.out.println(rootElement);
		
		System.out.println("Printing ID");
		String id = JsonPath.read(jsonString, "$.id");
		System.out.println(" ID : " + id);
			
		System.out.println("Printing prefs Array");
		List<Map<String,?>> backgrowndArray =  JsonPath.read(jsonString, "$.prefs.backgroundImageScaled");
		
		for (Map<String, ?> map : backgrowndArray) 
		{
			System.out.println(map);
			
		}
	
		System.out.println("print all urls");
		List<String> urls = JsonPath.read(jsonString, "$..url");
		for (String string : urls) {
			System.out.println(urls);
		}
		
		System.out.println("height above 1000");
		List<Integer> height = JsonPath.read(jsonString, "$.prefs.backgroundImageScaled[*].[?(@.height>1000)].height");
		for (Integer integer : height) {
			System.out.println(integer);
		}
	}

}
