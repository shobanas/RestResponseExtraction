package com.responseExtraction.rootPath;

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


public class rootPathExamples {

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
	
	
	// chain of multiple assertions in RestAssured
	@Test
	public  void  test001() {
				given()
				.params(parameters)
				.when()
				.get("/yql")
				.then()
				
				// look for elems under rate array to assert
				.root("query.results.rate")
				.body("Name", hasItems("USD/INR", "USD/AUD", "USD/CAD"))
				.body("id", hasItem("USDCAD"))
				
				// look for elems under query to validate
				.root("query")
				.body("count", greaterThan(4))	
				.body("count", equalTo(6));		
		
				// can chain assertions on multiple fields and conditions				
					
				
			}		
	
	
}
