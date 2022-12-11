package tests;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BillingTest {

	static String token;

	@Test(priority = 1)
	public void loginToBilling() throws JsonProcessingException {

		LoginRequset loginrequset = new LoginRequset();
		loginrequset.setUsername("admin");
		loginrequset.setPassword("admin");
		ObjectMapper mapper = new ObjectMapper();
		String loginJson = mapper.writeValueAsString(loginrequset);
		// System.out.println(loginJson);
		baseURI = "http://localhost:8086/api/authenticate";
		Response response = given().header("Content-Type", "application/json").when().body(loginJson).post();
		token = response.jsonPath().get("id_token");

	}

	@Test(priority = 2)
	public void getAllCustomers() {
		baseURI = "http://localhost:8086/api/dashboard/";
		given().auth().oauth2(token).when().get("/customers").then().assertThat().body("[0].value", equalTo(223)).log()
				.all();

	}

}
