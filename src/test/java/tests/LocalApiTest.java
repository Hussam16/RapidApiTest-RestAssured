package tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.http.ContentType;

public class LocalApiTest {
	
	private  String url="http://localhost:3000";
	
  @Test
  public void getAllPost() {
	  baseURI=url;
	  given()
	  .get("/posts")
	  .then()
	  .assertThat()
	  .statusCode(200)
	  .log()
	  .all();
	  
  }
  @Test
  public void createNewPost() {
	  baseURI=url;
	 JSONObject requset = new JSONObject();
	 requset.put("title", "xml-server");
	 requset.put("author", "Tawfik");
	 
	 given()
	 .contentType(ContentType.JSON)
	 .accept(ContentType.JSON)
	 .body(requset.toJSONString())
	 .when()
	 .post("/posts")
	 .then()
	 .assertThat()
	 .statusCode(201)
	 .log().all();
  }
  
  @Test
  public void UpdatePost() {
	  baseURI=url;
	 JSONObject requset = new JSONObject();
	 requset.put("title", "fam-server");
	 requset.put("author", "alaa");
	 
	 given()
	 .contentType(ContentType.JSON)
	 .accept(ContentType.JSON)
	 .body(requset.toJSONString())
	 .when()
	 .put("/posts/2")
	 .then()
	 .assertThat()
	 .statusCode(200)
	 .log().all();
  }
  @Test
  public void patchUpdatePost() {
	  baseURI=url;
	 JSONObject requset = new JSONObject();
	 requset.put("title", "fam-server");
	 requset.put("author", "tawfik");
	 
	 given()
	 .contentType(ContentType.JSON)
	 .accept(ContentType.JSON)
	 .body(requset.toJSONString())
	 .when()
	 .put("/posts/2")
	 .then()
	 .assertThat()
	 .statusCode(200)
	 .log().all();
  }
  
  @Test
  public void deletePost() {
	  baseURI=url;
 	 given()
	 .delete("/posts/2")
	 .then()
	 .assertThat()
	 .statusCode(200)
	 .log().all();
  }
  
}
  
