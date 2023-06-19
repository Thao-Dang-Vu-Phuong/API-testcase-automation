package com.api.auto.testcase_cach_2;

import com.api.auto.utils.PropertiesFileUtils;
import com.api.auto.utils_cach_2.FetchingJson;

import java.util.HashMap;
import java.io.File;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.api.auto.testcase.TC_API_Login;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class TC_API_CreatedWork {
private Response response;
private ResponseBody resBody;
private JsonPath bodyJson;
private String myWork;
private String myExperience;
private String myEducation;


@BeforeClass
public void init() {
	
	this.myWork = "tester";
	this.myExperience = "1 year";
	this.myEducation = "bachelor";
	
	File createdWorkBody = new File("./json_files/createdWorkBody.json");
	String tokenValue = FetchingJson.getToken();
	
	RequestSpecification req = given()
			.baseUri(PropertiesFileUtils.getProperties("baseurl"))
			.basePath(PropertiesFileUtils.getProperties("createWorkPath"))
			.contentType(ContentType.JSON)
			.header("token", tokenValue)
			.when()
			.body(createdWorkBody);
	
	
	response = req.post();
	resBody = response.getBody();
	bodyJson = response.jsonPath();
	
	System.out.println(response.asPrettyString());
}

@AfterClass
public void afterTest() {
	baseURI = null;
	basePath = null;
	//PropertiesFileUtils.saveToken("");
}


@Test(priority=0)
public void TC01_Validate201Created () {
	assertEquals(response.getStatusCode(), 201, "Validate status code Failed!");
}

@Test(priority=1)
public void TC02_ValidateId () {
	assertTrue(resBody.asString().contains("id"), "Validate id field Failed");
}

@Test(priority=2)
public void TC03_ValidateNameWork () {
	assertTrue(resBody.asString().contains("nameWork"), "Validate nameWork field Failed");
	String actNameWork = bodyJson.get("nameWork");
	assertEquals(actNameWork, myWork,"nameWork is not matched!");
}

@Test(priority=3)
public void TC04_ValidateExperience () {
	assertTrue(resBody.asString().contains("experience"), "Validate experience field Failed");
	String actExperience = bodyJson.get("experience");
	assertEquals(actExperience, myExperience, "experience is not matched!");
}

@Test(priority=4)
public void TC05_ValidateEducation () {
	assertTrue(resBody.asString().contains("education"), "Validate education field Failed");
	String actEducation = bodyJson.get("education");
	assertEquals(actEducation, myEducation, "education is not matched!");
}

}
