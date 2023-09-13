package utils;

import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class APIUtils {
	public static String authenticate(String url, String username, String password) {
		JSONObject body = new JSONObject();
		body.put("username", username);
		body.put("password", password);
		
		Response res = RestAssured
			.given()
				.contentType(ContentType.JSON)
				.relaxedHTTPSValidation() // disable SSL verification
				.body(body.toString()) // converts the values to a proper json format
			.when()
				.post(url)
			.then().extract().response();
		
		return res.cookie("LPAnimalShelter");
		
	}
}
