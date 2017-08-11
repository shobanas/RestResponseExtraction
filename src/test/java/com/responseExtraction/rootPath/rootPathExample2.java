package com.responseExtraction.rootPath;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jayway.restassured.RestAssured;

public class rootPathExample2 {

	// package all url params into a HashMap instead of attaching multiple key-value pairs
	static HashMap<String, Object>  parameters = new HashMap<String, Object>();
	
	
	@BeforeClass
	public static void init() {
		
		RestAssured.baseURI = "https://query.yahooapis.com";		
		RestAssured.basePath = "/v1/public";
		RestAssured.rootPath = "query.results.rate";
		
		parameters.put("q", "SELECT * FROM yahoo.finance.xchange WHERE pair in (\"USDTHB\", \"USDINR\", \"USDCAD\", \"USDAUD\", \"USDEUR\", \"USDBRL\")");
		parameters.put("format", "json");
		// parameters.put("diagnostics", "true");
		parameters.put("env", "store://datatables.org/alltableswithkeys");
		
	}
	

	
	// Assert on Name
		@Test
		public  void  test002() {
			given()
			.params(parameters)
			.when()
			.get("/yql")
			.then()
			.body("Name", hasItem("USD/INR"));
			
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
				.body("Name", hasItems("USD/INR", "USD/AUD", "USD/CAD"));
					
				// array of names is returned in the query, assert for a set of strings
				// using built in HamCrest assertions on list (hasItems) 
					
					 
		}
			
		
		
		// chain of multiple assertions in RestAssured
		@Test
		public  void  test005() {
				given()
				.params(parameters)
				.when()
				.get("/yql")
				.then()
				.body("Name", hasItems("USD/INR", "USD/AUD", "USD/CAD"))
				.body("id",  hasItem("USDINR"));
							
	
			// can chain assertions on multiple fields and conditions				
							 
		}	
		
		@AfterClass
		public static void tearDown()
		{
			RestAssured.reset();
			
		}
	
}
