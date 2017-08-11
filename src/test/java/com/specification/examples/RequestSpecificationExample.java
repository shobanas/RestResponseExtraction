package com.specification.examples;

import static com.jayway.restassured.RestAssured.given;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.specification.RequestSpecification;

public class RequestSpecificationExample {

	static RequestSpecBuilder  requestBuilder;
	static RequestSpecification requestSpec;

	@BeforeClass
	public static void init() {
		RestAssured.baseURI = "https://query.yahooapis.com";
		RestAssured.basePath = "/v1/public";
		
		requestBuilder = new RequestSpecBuilder();
		
		requestBuilder.addParam("q", "SELECT * FROM yahoo.finance.xchange WHERE pair in (\"USDTHB\", \"USDINR\", \"USDCAD\", \"USDAUD\", \"USDEUR\", \"USDBRL\")");
		requestBuilder.addParam("format", "json");
		requestBuilder.addParam("env", "store://datatables.org/alltableswithkeys");
		
		requestSpec = requestBuilder.build();
		
	}
	
	// extract 'count' value from response
	@Test
	public void test001() {
		
				given()
				.spec(requestSpec)
				.log()
				.all()
				.when()
				.get("/yql")
				.then()
				.statusCode(200);
		
	}
	
}
