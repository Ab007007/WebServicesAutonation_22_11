package hamcrest;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

import org.testng.annotations.Test;

import data.GlobalVariables;
import io.restassured.RestAssured;

public class SoftAsserts {

	
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
				body("prefs", hasKey("permissionLevel22"),
						"prefs.switcherViews.viewType",hasItems("Board22","Table","Calendar","dsadf")
						,"prefs.size()", equalTo(25),"prefs.switcherViews[0]",hasKey("_id"),
						"prefs.switcherViews.findAll{it._id == \"637d98fded95a8015df994ba\" }",
						hasItems(hasEntry("viewType","Board")));
		
			
	}
}
