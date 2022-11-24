package postRequest;

import static org.hamcrest.CoreMatchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateRepository {
	
	
	@Test(priority = 1)
	public void createRepository() {
		
		RestAssured.baseURI = "https://api.github.com";
		String myProject = "API_Demo_Eclipse_Project";
		String barrierToken = "Bearer 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";
		
		RestAssured.
			given().
				headers("Authorization",barrierToken).
				headers("Content-Type","application/json").
				body("{\r\n" + 
						"    \"name\" : \""+ myProject +"\",\r\n" + 
						"    \"description\" : \"MyAPICreatedRepo_23_11_Desc\"\r\n" + 
						"}").
			when().	
				post("/user/repos").
			then().
				assertThat().statusCode(201).and().
				contentType(ContentType.JSON).and().
				body("name" , equalTo(myProject)).log().all();	
	}

	
	@Test(priority = 2)
	public void getReporitory() {
		
	}
	
	
	@Test(priority = 3)
	public void deleteRepository() {
		
	}
}
