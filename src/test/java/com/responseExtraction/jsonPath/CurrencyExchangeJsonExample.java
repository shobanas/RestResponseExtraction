package com.responseExtraction.jsonPath;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.jayway.restassured.RestAssured.*;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import org.junit.BeforeClass;


public class CurrencyExchangeJsonExample {

	@BeforeClass
	public static void init() {
		RestAssured.baseURI = "https://query.yahooapis.com";
		RestAssured.basePath = "/v1/public";
		
		
	}
	
	// extract 'count' value from response
	@Test
	public void test001() {
	int count =  given()
				.param("q", "SELECT * FROM yahoo.finance.xchange WHERE pair in (\"USDTHB\", \"USDINR\", \"USDCAD\", \"USDAUD\", \"USDEUR\", \"USDBRL\")")
				.param("format", "json")
				.param("diagnostics", "true")
				.param("env", "store://datatables.org/alltableswithkeys")
				.when()
				.get("/yql")
				.then()
				.extract()
				.path("query.count");
	
				System.out.println("value of count is " + count);
	
	}
	
	// extract 'lang' value from response
		@Test
		public void test002() {
		String lang =  given()
					.param("q", "SELECT * FROM yahoo.finance.xchange WHERE pair in (\"USDTHB\", \"USDINR\", \"USDCAD\", \"USDAUD\", \"USDEUR\", \"USDBRL\")")
					.param("format", "json")
					.param("diagnostics", "true")
					.param("env", "store://datatables.org/alltableswithkeys")
					.when()
					.get("/yql")
					.then()
					.extract()
					.path("query.lang");
		
					System.out.println("value of lang is " + lang);
		
		}
	
		// extract 'Name' for the first rate array elem from the response  
				@Test
				public void test003() {
				String name =  given()
							.param("q", "SELECT * FROM yahoo.finance.xchange WHERE pair in (\"USDTHB\", \"USDINR\", \"USDCAD\", \"USDAUD\", \"USDEUR\", \"USDBRL\")")
							.param("format", "json")
							.param("diagnostics", "true")
							.param("env", "store://datatables.org/alltableswithkeys")
							.when()
							.get("/yql")
							.then()
							.extract()
							.path("query.results.rate[0].Name");
				
							System.out.println("value of first elem name is " + name);
				
				}
				
				// extract all rate array elem object values from response
				@Test
				public void test004() {
				List<String>  rateVals =  given()
							.param("q", "SELECT * FROM yahoo.finance.xchange WHERE pair in (\"USDTHB\", \"USDINR\", \"USDCAD\", \"USDAUD\", \"USDEUR\", \"USDBRL\" )")
							.param("format", "json")
							.param("diagnostics", "true")
							.param("env", "store://datatables.org/alltableswithkeys")
							.when()
							.get("/yql")
							.then()
							.extract()
							.path("query.results.rate");
				
							System.out.println("list of rate array objects are " + rateVals);
				
				}		
				
				// extract size of rate array elem from response
				@Test
				public void test005() {
				int  sizeOfRateArr =  given()
							.param("q", "SELECT * FROM yahoo.finance.xchange WHERE pair in (\"USDTHB\", \"USDINR\", \"USDCAD\", \"USDAUD\", \"USDEUR\", \"USDBRL\" )")
							.param("format", "json")
							.param("diagnostics", "true")
							.param("env", "store://datatables.org/alltableswithkeys")
							.when()
							.get("/yql")
							.then()
							.extract()
							.path("query.results.rate.size()");
				
							System.out.println("size of rate array is " + sizeOfRateArr);
				
				}		
							
				
	
	// extract 'Name' values from response
		@Test
		public void test006() {
		List<String>  names =  given()
					.param("q", "SELECT * FROM yahoo.finance.xchange WHERE pair in (\"USDTHB\", \"USDINR\", \"USDCAD\", \"USDAUD\", \"USDEUR\", \"USDBRL\" )")
					.param("format", "json")
					.param("diagnostics", "true")
					.param("env", "store://datatables.org/alltableswithkeys")
					.when()
					.get("/yql")
					.then()
					.extract()
					.path("query.results.rate.Name");
		
					System.out.println("list of names are " + names);
		
		}
		
		
		// extract the set of values from response if Name field is equal to a specific string
		// uses the Groovy findAll method to retrieve a hash map
			@Test
			public void test007() {
				
			List<HashMap<String, Object>>  values =  given()
							.param("q", "SELECT * FROM yahoo.finance.xchange WHERE pair in (\"USDTHB\", \"USDINR\", \"USDCAD\", \"USDAUD\", \"USDEUR\", \"USDBRL\" )")
							.param("format", "json")
							.param("diagnostics", "true")
							.param("env", "store://datatables.org/alltableswithkeys")
							.when()
							.get("/yql")
							.then()
							.extract()
							.path("query.results.rate.findAll{it.Name=='USD/INR'}");
				
							System.out.println("list of values are " + values);
				
				}
		
				// extract Rate value from response if Name field is equal to a specific string
				// uses the Groovy findAll method to retrieve a hash map and then get Rate elem from that set
				@Test
				public void test008() {
					
				List<HashMap<String, Object>>  values =  given()
									.param("q", "SELECT * FROM yahoo.finance.xchange WHERE pair in (\"USDTHB\", \"USDINR\", \"USDCAD\", \"USDAUD\", \"USDEUR\", \"USDBRL\" )")
									.param("format", "json")
									.param("diagnostics", "true")
									.param("env", "store://datatables.org/alltableswithkeys")
									.when()
									.get("/yql")
									.then()
									.extract()
									.path("query.results.rate.findAll{it.Name=='USD/EUR'}.Rate");
						
									System.out.println("list of Rate values are " + values);
						
						}
				
						// extract the set of Names from response for which the Rate value is > 30
						
						@Test
						public void test009() {
							
						// get the response and then process it for the specific info
						Response resp =  given()
									.param("q", "SELECT * FROM yahoo.finance.xchange WHERE pair in (\"USDTHB\", \"USDINR\", \"USDCAD\", \"USDAUD\", \"USDEUR\", \"USDBRL\" )")
									.param("format", "json")
									.param("diagnostics", "true")
									.param("env", "store://datatables.org/alltableswithkeys")
									.when()
									.get("/yql");
						
						List<String> names = resp
						.then()
						.extract()
						.path("query.results.rate.findAll{it.Rate > '30'}.Name");  // Rate is a number string!
						
						System.out.println("the names which have exchange Rate > 30 " + names);
									
								
						}
	
						// extract  values from response that start with id 'USDB'
						// uses the Groovy findAll method to retrieve a hash map and then get Rate elem from that set
						@Test
						public void test0010() {
							
						List<String>  values =  given()
											.param("q", "SELECT * FROM yahoo.finance.xchange WHERE pair in (\"USDTHB\", \"USDINR\", \"USDCAD\", \"USDAUD\", \"USDEUR\", \"USDBRL\" )")
											.param("format", "json")
											.param("diagnostics", "true")
											.param("env", "store://datatables.org/alltableswithkeys")
											.when()
											.get("/yql")
											.then()
											.extract()
											.path("query.results.rate.findAll{it.id==~/USDB.*/}");
								
											System.out.println("list of values that start with id USDB are " + values);
								
								}
						
						// extract  values from response that end with id 'UD'
						// uses the Groovy findAll method to retrieve a hash map and then get Rate elem from that set
						@Test
						public void test0011() {
							
						List<String>  values =  given()
											.param("q", "SELECT * FROM yahoo.finance.xchange WHERE pair in (\"USDTHB\", \"USDINR\", \"USDCAD\", \"USDAUD\", \"USDEUR\", \"USDBRL\" )")
											.param("format", "json")
											.param("diagnostics", "true")
											.param("env", "store://datatables.org/alltableswithkeys")
											.when()
											.get("/yql")
											.then()
											.extract()
											.path("query.results.rate.findAll{it.id==~/.*UD/}");
								
											System.out.println("list of values that end with id UD are " + values);
								
								}
						
											
										
						
	
	
}
