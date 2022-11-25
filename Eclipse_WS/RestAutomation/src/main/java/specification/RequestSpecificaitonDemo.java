package specification;

import static org.hamcrest.CoreMatchers.equalTo;

import org.apache.http.client.methods.RequestBuilder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RequestSpecificaitonDemo {

	private String myProject;
	private String barrierToken;
	
	static RequestSpecification reqSpec;
	static RequestSpecBuilder builder;
	
	static ResponseSpecification resSpec;
	static ResponseSpecBuilder rBuilder;
	
	
	@BeforeMethod
	public void setup() {
		RestAssured.baseURI = "https://api.github.com";
		myProject = "API_Demo_Eclipse_Project";
		barrierToken = "Bearer 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";
		builder = new RequestSpecBuilder();
		builder.addHeader("Authorization", barrierToken);
		builder.addHeader("Content-Type", "application/json");
		reqSpec = builder.build();
		
		
		rBuilder = new ResponseSpecBuilder();
		rBuilder.expectStatusCode(200);
		rBuilder.expectBody("owner.login", equalTo("Ab007007"));
		rBuilder.expectHeader("Server", "GitHub.com");
		rBuilder.expectHeader("Content-Type", "application/json; charset=utf-8");
		
		resSpec = rBuilder.build();
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
				assertThat().statusCode(200).log().all().
				contentType(ContentType.JSON).and().
				body("name" , equalTo(myProject)).log().all();	;
		
	}

	@Test(priority = 2)
	public void getReporitoryWithReqSpec() {
		
		RestAssured.
			given().
				spec(reqSpec).
			when().
				get("/repos/Ab007007/"+myProject).
			then().
				assertThat().statusCode(200).log().all().
				contentType(ContentType.JSON).and().
				body("name" , equalTo(myProject)).log().all();	;
		
	}
	@Test(priority = 2)
	public void getReporitoryWithReqAndResSpec() {
		
		RestAssured.
			given().
				spec(reqSpec).
			when().
				get("/repos/Ab007007/"+myProject).
			then().
				spec(resSpec).
				body("name", equalTo("API_Demo_Eclipse_Project"));
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
