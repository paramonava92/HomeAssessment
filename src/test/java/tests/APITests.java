package tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.APIUtils;

import java.io.File;
import java.util.Map;

import org.hamcrest.Matchers;


public class APITests {
	final String COOKIE_KEY = "LPAnimalShelter";
	final String BASE_URL = "https://localhost:44429";
	String token;
	
	@BeforeTest
	public void authenticate() {
		token = APIUtils.authenticate("https://localhost:44429/api/User/login", "admin", "@dmin123");
	}
	
	@Test
	public void getAnimals() {
		Response res = RestAssured
			.given()
				.relaxedHTTPSValidation() // disable SSL verification
				.cookie(COOKIE_KEY, token)
			.when()
				.get(BASE_URL + "/api/Animals")
			.then()
				.assertThat()
				.statusCode(Matchers.equalTo(200))
				.body("[0].id", Matchers.is(Matchers.instanceOf(Number.class)))
				.body("[0].shelterId", Matchers.is(Matchers.instanceOf(Number.class)))
				.body("[0].type", Matchers.is(Matchers.instanceOf(String.class)))
				.body("[0].name", Matchers.is(Matchers.instanceOf(String.class)))
				.body("[0].weight", Matchers.is(Matchers.instanceOf(Number.class)))
				.body("[0].kennel", Matchers.is(Matchers.instanceOf(Map.class)))
				.extract().response();
			
		res.prettyPrint();
	}
	
	@Test(dataProvider = "createAnimalProvider")
	public void createAnimal(int shelterId, String type, String name, int weight) {
		
		Response res = RestAssured
				.given()
					.relaxedHTTPSValidation() // disable SSL verification
					.cookie(COOKIE_KEY, token)
					.multiPart("ShelterId", shelterId)
					.multiPart("Type", type)
					.multiPart("Name", name)
					.multiPart("Weight", weight)
					.multiPart("Image", new File("src/test/resources/dog.jpeg"))
					.contentType(ContentType.MULTIPART)
				.when()
					.post(BASE_URL + "/api/Animals")
				.then()
					.assertThat()
					.statusCode(Matchers.equalTo(201)) // DEFECT: Server returns 500 when uploading an image
					.body("id", Matchers.is(Matchers.instanceOf(Number.class)))
					.body("shelterId", Matchers.is(Matchers.equalTo(shelterId)))
					.body("type", Matchers.is(Matchers.equalTo(type)))
					.body("name", Matchers.is(Matchers.equalTo(name)))
					.body("weight", Matchers.is(Matchers.equalTo(weight)))
					.extract().response();
		
		System.out.println("*****");
		System.out.println(res.statusCode());
		res.prettyPrint();
	}
	
	@DataProvider(name = "createAnimalProvider")
	public Object[][] createAnimalDataProvider() {
		return new Object[][] {
			{1, "Dog", "Max", 14} 
		};
	}
}
