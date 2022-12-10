package tests;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.http.ContentType;
import io.restassured.internal.matcher.xml.XmlXsdMatcher;
import io.restassured.matcher.RestAssuredMatchers;

public class soapApiTest {
	@Test
	public void createPostReqForAddingTwoNumbers() throws IOException {

		FileInputStream fileInputStream = new FileInputStream(
				new File("D:\\Interview Preparation\\RapidApiTest\\src\\test\\resources\\soapBody.xml"));
		baseURI = "http://www.dneonline.com";
		given().accept(ContentType.XML).contentType("text/xml").when().body(IOUtils.toString(fileInputStream, "UTF-8"))
				.post("/calculator.asmx").then().assertThat().statusCode(200).log().all().and()
				.body("//*:AddResult.text()", equalTo("8"));

	}
	
	@Test
	public void ValidateSchemeOfAddRequset() throws IOException {

		FileInputStream fileInputStream = new FileInputStream(
				new File("D:\\Interview Preparation\\RapidApiTest\\src\\test\\resources\\soapBody.xml"));
		baseURI = "http://www.dneonline.com";
		given().accept(ContentType.XML).contentType("text/xml").when().body(IOUtils.toString(fileInputStream, "UTF-8"))
				.post("/calculator.asmx").then().assertThat()
				.body(RestAssuredMatchers
				.matchesXsdInClasspath("D:\\Interview Preparation\\RapidApiTest\\src\\test\\resources\\JsonSchemes\\cal.xsd"));

	}
	
}
