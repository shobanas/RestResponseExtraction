package com.fileDownload.examples;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class FileDownloadExample1 {

	
	// download a file and compare with expected file size
	@Test
	public void verifyDownloadedFile() {
		
		// get the file path from project root dir 
		
		File inputFile = new File(System.getProperty("user.dir") + File.separator+"geckodriver-v0.14.0-arm7hf (2).tar.gz");
		
		// find length of file
				
	    int expectedFileSize = (int)inputFile.length();
		
		System.out.println("The expected size of the file is " + expectedFileSize + " bytes");
		
		
		//https://github.com/mozilla/geckodriver/releases/download/v0.14.0/geckodriver-v0.14.0-arm7hf.tar.gz
		
		//download the same file using a get request through RestAssured lib and extract as ByteArray
		
		byte[] actualFile = given()
		.when()
		.get("https://github.com/mozilla/geckodriver/releases/download/v0.14.0/geckodriver-v0.14.0-arm7hf.tar.gz")
		.then()
		.extract()
		.asByteArray();
		
		System.out.println("The actual size of the file is " + actualFile.length + " bytes");
		
		assertThat(expectedFileSize, equalTo(actualFile.length));
		
		
		
		
	}
	
}
