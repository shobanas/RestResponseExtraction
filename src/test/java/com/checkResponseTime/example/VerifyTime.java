package com.checkResponseTime.example;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.builder.ResponseSpecBuilder;
import com.jayway.restassured.specification.RequestSpecification;
import com.jayway.restassured.specification.ResponseSpecification;

public class VerifyTime {
	static RequestSpecBuilder  requestBuilder;
	static RequestSpecification requestSpec;
	
	static ResponseSpecBuilder  responseBuilder;
	static ResponseSpecification responseSpec;
	
	static Map<String, Object>  responseHeader = new HashMap<String, Object>();
	
	

	@BeforeClass
	public static void init() {
		RestAssured.baseURI = "https://query.yahooapis.com";
		RestAssured.basePath = "/v1/public";
		
		requestBuilder = new RequestSpecBuilder();
		
		requestBuilder.addParam("q", "SELECT * FROM yahoo.finance.xchange WHERE pair in (\"USDTHB\", \"USDINR\", \"USDCAD\", \"USDAUD\", \"USDEUR\", \"USDBRL\")");
		requestBuilder.addParam("format", "json");
		requestBuilder.addParam("env", "store://datatables.org/alltableswithkeys");
		
		requestSpec = requestBuilder.build();
		
		
		// build the expected response
		// create the response header map
			
		responseHeader.put("Content-Type", "application/json;charset=utf-8");
		responseHeader.put("Server", "ATS");
		
		responseBuilder = new ResponseSpecBuilder();
		responseBuilder.expectHeaders(responseHeader);
		responseBuilder.expectStatusCode(200);
		responseBuilder.expectBody("query.count", equalTo(6));
		
		// responseBuilder.expectResponseTime(lessThan(10L), TimeUnit.SECONDS);
		
		responseSpec = responseBuilder.build();
		
		
		
	}
	
	/***********
	// get the response time value in milliseconds
	@Test
	public void test001() {
		
		long responseTime = 	given()
				.spec(requestSpec)
				.log()
				.all()
				.when()
				.get("/yql")
				.time();
				
		System.out.println("response time : " + responseTime + "ms");		
		
	}
	
	************/
	
	
	// get the response time value in other units
		@Test
		public void test002() {
			
			long responseTime = 	given()
					.spec(requestSpec)
					.log()
					.all()
					.when()
					.get("/yql")
					.timeIn(TimeUnit.SECONDS);
					
			System.out.println("response time : " + responseTime + " seconds");		
			
			// assert response time to be within a limit
			given()
			.spec(requestSpec)
			.log()
			.all()
			.when()
			.get("/yql")
			.then()
			.time(lessThan(10L), TimeUnit.SECONDS);
			
			
		}
	
	
}
