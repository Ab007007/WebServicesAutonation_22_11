package getRequests;

import org.testng.annotations.Test;

import data.GlobalVariables;
import io.restassured.RestAssured;
import util.BaseClass;

public class GetBoardWithGlobalParamsInherited extends BaseClass {

	@Test
	public void getBoardTest()  {
		RestAssured.basePath = "/1/boards/637d98fded95a8015df994b9";
		RestAssured.
			given().
				param("key", GlobalVariables.key).
				param("token", GlobalVariables.token).
			when().
				get().
			then().
				assertThat().statusCode(200);
		
		RestAssured.reset();
		System.out.println("ended");
	
	}
}
