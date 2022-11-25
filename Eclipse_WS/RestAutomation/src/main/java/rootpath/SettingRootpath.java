package rootpath;

import static org.hamcrest.CoreMatchers.equalTo;

import java.util.concurrent.TimeUnit;

import org.apache.http.client.methods.RequestBuilder;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SettingRootpath {

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
		rBuilder.expectResponseTime(Matchers.lessThan(4L),TimeUnit.SECONDS);
		
		resSpec = rBuilder.build();
	}
	

	
	//@Test
	public void getReporitoryWithReqAndResSpec() {
		
		RestAssured.
			given().
				spec(reqSpec).
			when().
				get("/repos/Ab007007/"+myProject).
			then().
	//			root("owner").
				spec(resSpec).log().all().and().
				//body("name", equalTo("API_Demo_Eclipse_Project")).and().
				body("id", equalTo(27208952)).and().
				body("login", equalTo("Ab007007"));
		
	}

	@Test
	public void setRootVariable() {
		
//		RestAssured.rootPath = "owner";
		RestAssured.
			given().
				spec(reqSpec).
			when().
				get("/repos/Ab007007/"+myProject).
			then().
				spec(resSpec).log().all().and().
				//body("name", equalTo("API_Demo_Eclipse_Project")).and().
				body("id", equalTo(27208952)).and().
				body("login", equalTo("Ab007007")).
				time(Matchers.lessThan(2L),TimeUnit.SECONDS);
		
		RestAssured.reset();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
