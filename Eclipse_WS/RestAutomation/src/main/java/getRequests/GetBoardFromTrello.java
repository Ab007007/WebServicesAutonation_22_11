package getRequests;

import data.GlobalVariables;
import io.restassured.RestAssured;

public class GetBoardFromTrello {

	
	public static void main(String[] args) {
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
}
