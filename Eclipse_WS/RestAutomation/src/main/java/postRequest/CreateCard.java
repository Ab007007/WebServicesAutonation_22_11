package postRequest;

import org.testng.annotations.Test;
import static org.hamcrest.CoreMatchers.equalTo;

import data.GlobalVariables;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import util.BaseClass;

public class CreateCard extends BaseClass {

	
	
	@Test (priority = 1)
	public void createList() { 
		RestAssured.
		given().
			queryParam("key",GlobalVariables.key).
			queryParam("token",GlobalVariables.token).
			queryParam("idBoard","637ef63ee1bfa102df646611").
			queryParam("name","Eclipse_List1").
			queryParam("Value","true").
			headers("Content-Type","application/json").
		when().
			post("/1/lists/").
		then().
			assertThat().statusCode(200).and().
			contentType(ContentType.JSON).and().log().all().
			body("name" , equalTo("Eclipse_List1") );	
	}

	// Automate Create Card
	@Test(priority = 2)
	public void createCard() {

		System.out.println(RestAssured.baseURI);
		// For POST request we need to pass parameters in required format
		RestAssured.given().queryParam("key", GlobalVariables.key). // Query parameter
				queryParam("token", GlobalVariables.token).queryParam("name", "My_Eclipse_Board1_card1")
				.queryParam("desc", "My_Eclipse_Board1_card1_description")
				.queryParam("idList", "637f0c5c3808060030026328").headers("Content-Type", "application/json"). // header
																												// parameter
				when().post("/1/cards"). // endpoint
				then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and()
				.body("name", equalTo("My_Eclipse_Board1_card1")); // using static imports here
	}

}
