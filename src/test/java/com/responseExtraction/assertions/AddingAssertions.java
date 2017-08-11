package com.responseExtraction.assertions;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.jayway.restassured.RestAssured.*;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;


public class AddingAssertions {

	// package all url params into a HashMap instead of attaching multiple key-value pairs
	static HashMap<String, Object>  parameters = new HashMap<String, Object>();
	
	
	@BeforeClass
	public static void init() {
		
		RestAssured.baseURI = "https://query.yahooapis.com";		
		RestAssured.basePath = "/v1/public";
		
		parameters.put("q", "SELECT * FROM yahoo.finance.xchange WHERE pair in (\"USDTHB\", \"USDINR\", \"USDCAD\", \"USDAUD\", \"USDEUR\", \"USDBRL\")");
		parameters.put("format", "json");
		// parameters.put("diagnostics", "true");
		parameters.put("env", "store://datatables.org/alltableswithkeys");
		
	}
	
	// Assert on count value, use built in Hamcrest assertions along with RestAssured methods
	@Test
	public  void  test001() {
		given()
		.params(parameters)
		.when()
		.get("/yql")
		.then()
		.body("query.count", equalTo(6));
		// using built in RestAssured assertions on body 
		
		
		// alternate method is to extract response and use JUnit assertions on it
		
	}
	
	// Assert on Name
		@Test
		public  void  test002() {
			given()
			.params(parameters)
			.when()
			.get("/yql")
			.then()
			.body("query.results.rate.Name", hasItem("USD/INR"));
			
			// array of names is returned in the query
			// using built in HamCrest assertions on list (hasItem) 
			
			//alternately you can extract the response into a list of strings and verify if the item exists 
		}
	
		// Assert on multiple Names
		@Test
		public  void  test003() {
				given()
				.params(parameters)
				.when()
				.get("/yql")
				.then()
				.body("query.results.rate.Name", hasItems("USD/INR", "USD/AUD", "USD/CAD"));
					
				// array of names is returned in the query, assert for a set of strings
				// using built in HamCrest assertions on list (hasItems) 
					
					 
		}
			
		// Assert using logical function
		@Test
		public  void  test004() {
				given()
				.params(parameters)
				.when()
				.get("/yql")
				.then()
				.body("query.count", greaterThan(4));
						
		// can use logical functions greaterThan, lessThan, greaterThanOrEqualTo, lessThanOrEqualTo
							
							 
		}
		
		// chain of multiple assertions in RestAssured
		@Test
		public  void  test005() {
				given()
				.params(parameters)
				.when()
				.get("/yql")
				.then()
				.body("query.count", greaterThan(4))
				.body("query.results.rate.Name", hasItems("USD/INR", "USD/AUD", "USD/CAD"))
				.body("query.count", equalTo(6));		
	
			// can chain assertions on multiple fields and conditions				
							 
		}	
		
		
		// extract and assert using Junit assertions
		@Test
		public  void  test006() {
		int count = 	given()
			  .params(parameters)
			  .when()
			  .get("/yql")
			  .then()
			  .extract()
			  .path("query.count");
		
		
	    // alternate method is to extract response and use JUnit assertions on it
		
		assertEquals(6, count);
		
		}		
		
	
}
