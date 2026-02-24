package basics;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.net.HttpURLConnection;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import enums.ApiResources;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import pojo.Book;
import utils.UniqueGenerator;

public class LearnSerialization {

	@Test
	public void seralizationTest() {
		
		
		
		RestAssured.baseURI=ApiResources.LibraryManagementBaseUrl.getResource();
	
		given().log().all().header("Content-Type", ContentType.JSON).body(UniqueGenerator.getUniqueBookObject()).
		when().post(ApiResources.postBook.getResource()).then().assertThat().statusCode(HttpURLConnection.HTTP_OK).body("Msg", equalTo("successfully added")).log().all();
	}
}
