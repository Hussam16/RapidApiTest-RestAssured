package tests;

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

public class UsersTest {

	private String url = "https://reqres.in/api";

	@Test(priority = 1)
	@Description("Get All The Users")
	@Severity(SeverityLevel.BLOCKER)
	public void TC_GetUsers_001() {

		baseURI = url;
		given().get("/users?page=2").then().statusCode(200).body("data[1].id", equalTo(8)).log().body();

	}

	@Test(priority = 2)
	@Description("Create a New User")
	@Severity(SeverityLevel.CRITICAL)
	public void TC_CreateUser_002() {

		baseURI = url;

		JSONObject reqObject = new JSONObject();
		reqObject.put("name", "hussam");
		reqObject.put("job", "QA");

		given().body(reqObject.toJSONString()).when().post("/users").then().statusCode(203).log().body();

	}

	@Test(priority = 3)
	@Description("update user ")
	@Severity(SeverityLevel.NORMAL)
	public void TC_Update_User_003() {

		baseURI = url;
		JSONObject reqObject = new JSONObject();
		reqObject.put("name", "hussam");
		reqObject.put("job", "QA");

		given().body(reqObject.toJSONString()).when().put("/users/2").then().statusCode(205).log().body();

	}
	@Test(priority = 4)
	@Description("Delete user ")
	@Severity(SeverityLevel.MINOR)
	public void TC_Delete_User_004() {

		baseURI = url;
		JSONObject reqObject = new JSONObject();

		given().delete("/users/2").then().statusCode(204).log().all();

	}
	
	@Test(priority = 5)
	@Description("Validate the Json Scheme for the get all users request ")
	@Severity(SeverityLevel.CRITICAL)
	public void TC_ValidateJsonScheme_005() {

		baseURI = url;
		given().get("/users?page=2").then().assertThat().body(JsonSchemaValidator.
			      matchesJsonSchema(new File("D:\\Interview Preparation\\RapidApiTest\\src\\test\\resources\\JsonSchemes\\scheme.json")));
	}


}
