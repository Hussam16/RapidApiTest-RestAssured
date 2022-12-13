package tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SimpleBookAPI {

	static String bookid;
	static String bookID;

	@Description("Status")
	@Test(priority = 1)
	public void getStatus() {

		RestAssured.baseURI = "https://simple-books-api.glitch.me";
		RestAssured.basePath = "/status";
		RequestSpecification requestSpecfication = RestAssured.given();
		Response response = requestSpecfication.get();
		JsonPath isOk = response.body().jsonPath();
		String valueOfJson = isOk.get("status");
		Assert.assertEquals(valueOfJson, "OK");

	}

	@Description("Validate the limit parameter")
	@Test(priority = 2)
	public void listOfBooksCheckLimitParameters() {
		RestAssured.baseURI = "https://simple-books-api.glitch.me";
		RestAssured.basePath = "/books";
		JsonPath books = RestAssured.given().queryParam("limit", 4).get().body().jsonPath();
		List<Object> list = books.get();
		Assert.assertEquals(list.size(), 4);

	}
 
	@Description("Get All Fiction Books")
	@Test(priority = 3)
	public void listOfBooks() {
		RestAssured.baseURI = "https://simple-books-api.glitch.me";
		RestAssured.basePath = "/books";
		JsonPath books = RestAssured.given().get().body().jsonPath();
		List<String> filterBooknames = books.getList("findAll{it.type=='fiction'}.id");
		bookID = String.valueOf(filterBooknames.get(0));

	}


	@Description("Get Single Book and validate the name 's the Russian")
	@Test(priority = 4)
	public void getSingleBook() {
		RestAssured.baseURI = "https://simple-books-api.glitch.me";
		RestAssured.basePath = "/books/1";
		JsonPath singleBook = RestAssured.given().get().body().jsonPath();
		Assert.assertEquals(singleBook.get("name"), "The Russian");


	}

}
