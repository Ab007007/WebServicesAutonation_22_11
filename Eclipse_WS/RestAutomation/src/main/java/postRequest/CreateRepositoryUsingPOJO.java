package postRequest;

import static org.hamcrest.CoreMatchers.equalTo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import pojo.GitRepoPOJO;

public class CreateRepositoryUsingPOJO {
	
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
		GitRepoPOJO gitObj = new GitRepoPOJO();
		gitObj.setName(myProject);
		gitObj.setDesc(myProject+"_Desc");
		
		RestAssured.
			given().
				headers("Authorization",barrierToken).
				headers("Content-Type","application/json").
				body(gitObj).
			when().	
				post("/user/repos").
			then().
				assertThat().statusCode(201).and().
				contentType(ContentType.JSON).and().
				body("name" , equalTo(myProject)).log().all();	
	}



}
