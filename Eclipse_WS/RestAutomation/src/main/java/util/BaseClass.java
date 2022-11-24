package util;

import io.restassured.RestAssured;

public class BaseClass {
	static {
		RestAssured.baseURI = "https://api.trello.com";	
	}
	
}
