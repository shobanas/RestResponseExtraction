package com.responseExtraction.xmlPath;



import org.junit.BeforeClass;
import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.internal.path.xml.NodeChildrenImpl;

// import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.path.xml.XmlPath.*;

import org.hamcrest.Matchers.*;


public class CurrencyExchangeXmlExample {

	
	
	@BeforeClass
	public static void init() {
		RestAssured.baseURI = "https://query.yahooapis.com";
		RestAssured.basePath = "/v1/public";
		
	}
	
	// extract count value from the xml response
	@Test
	public void test001() {
		String count =  given()
				.param("q", "SELECT * FROM yahoo.finance.xchange WHERE pair in (\"USDTHB\", \"USDINR\", \"USDCAD\", \"USDAUD\", \"USDEUR\", \"USDBRL\")")
				.param("format", "xml")
				.param("diagnostics", "true")
				.param("env", "store://datatables.org/alltableswithkeys")
				.when()
				.get("/yql")
				.then()
				.extract()
				.path("query.@yahoo:count");
	
				System.out.println("value of count is " + count);
		
	}
	
	// extract lang value from the xml response
	@Test
	public void test002() {
		String  lang =  given()
					.param("q", "SELECT * FROM yahoo.finance.xchange WHERE pair in (\"USDTHB\", \"USDINR\", \"USDCAD\", \"USDAUD\", \"USDEUR\", \"USDBRL\")")
					.param("format", "xml")
					.param("diagnostics", "true")
					.param("env", "store://datatables.org/alltableswithkeys")
					.when()
					.get("/yql")
					.then()
					.extract()
					.path("query.@yahoo:lang");
		
					System.out.println("value of lang is " + lang);
			
		}
		
	// extract Name value from the first rate array elem in response
	@Test
	public void test003() {
		String  name =  given()
						.param("q", "SELECT * FROM yahoo.finance.xchange WHERE pair in (\"USDTHB\", \"USDINR\", \"USDCAD\", \"USDAUD\", \"USDEUR\", \"USDBRL\")")
						.param("format", "xml")
						.param("diagnostics", "true")
						.param("env", "store://datatables.org/alltableswithkeys")
						.when()
						.get("/yql")
						.then()
						.extract()
						.path("query.results.rate[0].Name");
				
						System.out.println("value of Name is " + name);
					
		}
				
		// extract all values from the rate array as a String
		@Test
		public void test004() {
			// extract entire response as a string
			String  xmlResp =  given()
							.param("q", "SELECT * FROM yahoo.finance.xchange WHERE pair in (\"USDTHB\", \"USDINR\", \"USDCAD\", \"USDAUD\", \"USDEUR\", \"USDBRL\")")
							.param("format", "xml")
							.param("diagnostics", "true")
							.param("env", "store://datatables.org/alltableswithkeys")
							.when()
							.get("/yql")
							.andReturn()
							.asString();
					
			// extract the values under rate elem 
					
			String valuesUnderRate =  with(xmlResp)
							                 .get("query.results.rate")
							                 .toString();
					
				
			System.out.println("values under rate array are " + valuesUnderRate);
					
		}
				
		// Find the size of  rate array in response xml
		@Test
	    public void test005() {
		// extract rate array as an object as we don't know the datatype
		Object o = given()
		    .param("q", "SELECT * FROM yahoo.finance.xchange WHERE pair in (\"USDTHB\", \"USDINR\", \"USDCAD\", \"USDAUD\", \"USDEUR\", \"USDBRL\")")
		    .param("format", "xml")
		    .param("diagnostics", "true")
			.param("env", "store://datatables.org/alltableswithkeys")
			.when()
			.get("/yql")
			.then()
			.extract()
			.path("query.results.rate");
			
			// find the class of the object as "NodeChildrenImpl"
			System.out.println("the object is of class : " + o.getClass());
			
			NodeChildrenImpl  rateElems = given()
				    .param("q", "SELECT * FROM yahoo.finance.xchange WHERE pair in (\"USDTHB\", \"USDINR\", \"USDCAD\", \"USDAUD\", \"USDEUR\", \"USDBRL\")")
				    .param("format", "xml")
				    .param("diagnostics", "true")
					.param("env", "store://datatables.org/alltableswithkeys")
					.when()
					.get("/yql")
					.then()
					.extract()
					.path("query.results.rate");
			
			// find the size of the rateElems array"
			System.out.println("the size of the rate array : " + rateElems.size());
				
	  }
		// extract all Names from the response as a String
	@Test
	public void test006() {
	// extract entire response as a string
		String  xmlResp =  given()
						.param("q", "SELECT * FROM yahoo.finance.xchange WHERE pair in (\"USDTHB\", \"USDINR\", \"USDCAD\", \"USDAUD\", \"USDEUR\", \"USDBRL\")")
						.param("format", "xml")
						.param("diagnostics", "true")
						.param("env", "store://datatables.org/alltableswithkeys")
						.when()
						.get("/yql")
					    .andReturn()
						.asString();
							
					// extract the names under rate elem 
							
		String names =  with(xmlResp)
								.get("query.results.rate.findAll{it.Name}.Name")
								.toString();
							
						
	    System.out.println("names under rate array are " + names);
							
	}		
		
	// extract all info from the response for the rate elem with Name "USD/AUD"
		@Test
		public void test007() {
		// extract entire response as a string
			String  xmlResp =  given()
							.param("q", "SELECT * FROM yahoo.finance.xchange WHERE pair in (\"USDTHB\", \"USDINR\", \"USDCAD\", \"USDAUD\", \"USDEUR\", \"USDBRL\")")
							.param("format", "xml")
							.param("diagnostics", "true")
							.param("env", "store://datatables.org/alltableswithkeys")
							.when()
							.get("/yql")
						    .andReturn()
							.asString();
								
						// extract the names under rate elem 
								
			String values =  with(xmlResp)
									.get("query.results.rate.findAll{it.Name == 'USD/AUD'}")
									.toString();
								
							
		    System.out.println("values under rate array for Name USD/AUD are " + values);
								
		}		
					
		// extract Rate info from the response for the rate elem with ATTRIBUTE id="USDINR"
		// we'll use depth search in this example
		@Test
		public void test008() {
		// extract entire response as a string
		String  xmlResp =  given()
						.param("q", "SELECT * FROM yahoo.finance.xchange WHERE pair in (\"USDTHB\", \"USDINR\", \"USDCAD\", \"USDAUD\", \"USDEUR\", \"USDBRL\")")
						.param("format", "xml")
						.param("diagnostics", "true")
						.param("env", "store://datatables.org/alltableswithkeys")
						.when()
						.get("/yql")
						.andReturn()
						.asString();
										
		// attributes should be located with preceding @symbol as below
		// id is not a node, an attribute
		
		
										
		String rate =  with(xmlResp)
							.get("query.results.rate.findAll{it.@id == 'USDINR'}.Rate")
							.toString();
		// we can also use depth search in the response string to locate Rate for a specific id attribute
										
		String rate1 =  with(xmlResp)
				.get("**.findAll{it.@id == 'USDINR'}.Rate")
				.toString();	
		
		
		System.out.println("Rate value for id USDINR is " + rate);
		System.out.println("Rate value for id USDINR is " + rate1);								
		}		
													
		
		
				
	
}
