package com.fileUpload.examples;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class FileUploadExample1 {

	// upload a .gif file to zamzar.com to convert to png format
	@Test
	public void uploadFileExample1() {
		
		String zamzarApiKey = "7c4f7f777d44c806d4c1d4c2b38c651c4f3fe7b2";
		String endPoint = "https://sandbox.zamzar.com/v1/jobs";
		
		File inputFile  = new File(System.getProperty("user.dir")+File.separator+"dancing_Banana.gif");
		
		System.out.println("The input file size is " + inputFile.length() + " bytes");
		given()
		.auth()
		.basic(zamzarApiKey, "")
		.multiPart("source_file", inputFile)
		.multiPart("target_format", "png")
		.when()
		.post(endPoint)
		.then()
		.log()
		.all();
		
		
	}
	
}
