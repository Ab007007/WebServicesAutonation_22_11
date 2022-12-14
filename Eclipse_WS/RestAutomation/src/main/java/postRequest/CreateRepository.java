package postRequest;

import static org.hamcrest.CoreMatchers.equalTo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateRepository {
	private String myProject;
	private String barrierToken;
	
	@BeforeMethod
	public void setup() {
		RestAssured.baseURI = "https://api.github.com";
		myProject = "API_Demo_Eclipse_Project";
		barrierToken = "Bearer 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";
		
	}
	@Test(priority = 1)
	public void createRepository() {
		
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
		RestAssured.
			given().
				headers("Authorization",barrierToken).
				headers("Content-Type","application/json").
			when().
				get("/repos/Ab007007/"+myProject).
			then().
				assertThat().statusCode(200).log().all();
		
	}
	
	
	@Test(priority = 3)
	public void deleteRepository() {
		RestAssured.
		given().
			headers("Authorization",barrierToken).
			headers("Content-Type","application/json").
		when().
			delete("/repos/Ab007007/"+myProject).
		then().
			assertThat().statusCode(204).log().all();
	}
}
