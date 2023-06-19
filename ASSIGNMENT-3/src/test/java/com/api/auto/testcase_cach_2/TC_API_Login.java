package com.api.auto.testcase_cach_2;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.api.auto.utils.PropertiesFileUtils;
import com.api.auto.utils_cach_2.FetchingJson;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;

import java.io.File;
import java.io.IOException;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class TC_API_Login {
private Response response;
private ResponseBody resBody;
private JsonPath bodyJson;
private String account;
private String password;


	
	@BeforeClass
	public void init() {
		File loginBody = new File("./json_files/loginBody.json");
		this.account = "testerFunix";
		this.password = "Abc13579";

		RequestSpecification request = given()
				.baseUri(PropertiesFileUtils.getProperties("baseurl"))
				.basePath(PropertiesFileUtils.getProperties("loginPath"))
				.contentType(ContentType.JSON)
				.when()
				.body(loginBody);
		
		response = request.post();
		resBody = response.getBody();
		bodyJson = response.jsonPath();
		
		
		System.out.println(resBody.asPrettyString());
	}
	

	
	@Test(priority = 0)
	public void TC01_Validate200OK () {
		assertEquals(response.getStatusCode(), 200, "Validate Status Code Failed!");
	}
	
	@Test(priority = 1)
	public void TC02_ValidateMessage () {
		assertTrue(resBody.asString().contains("message"),"Validate message field Failed!");
		String expMessage = "Đăng nhập thành công";
		String actMessage = bodyJson.get("message");
		assertEquals(actMessage, expMessage, "Message is not matched!");
	}
	
	@Test(priority = 2)
	public void TC03_ValidateToken () throws StreamWriteException, DatabindException, IOException {
		assertTrue(resBody.asString().contains("token"),"Validate token field Failed!");
		String token = bodyJson.get("token");
		FetchingJson.saveToken(token);
		
		
		//PropertiesFileUtils.saveToken(tokenValue);
	}
	
	@Test(priority = 3)
	public void TC04_ValidateUserType () {
		assertTrue(resBody.asString().contains("user"),"Validate user field Failed!");
		assertTrue(resBody.asString().contains("type"),"Validate type field Failed!");
		String expUserType = "UNGVIEN";
		String actUserType = bodyJson.get("user.type");
		assertEquals(actUserType, expUserType, "User Type is not matched");
	}
	
	@Test(priority = 4)
	public void TC05_ValidateAccount () {
		assertTrue(resBody.asString().contains("account"),"Validate account field Failed!");
		
		String actAccount = bodyJson.get("user.account");
		assertEquals(actAccount, account, "Account information is not matched");
		
		assertTrue(resBody.asString().contains("password"),"Validate password field Failed!");
		String actPassword = bodyJson.get("user.password");
		assertEquals(actPassword, password, "Password information is not matched");
	}







	
}
