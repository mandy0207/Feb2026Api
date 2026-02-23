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

public class LearnSerialization {

	@Test
	public void seralizationTest() {
		
		Faker faker= new Faker();
		String isbn=faker.internet().password();
		String aisle= faker.number().digits(4);
		String bookName= faker.book().title();
		String author=faker.book().author();
		
		Book book = new Book(bookName, isbn, aisle, author);
		
		RestAssured.baseURI=ApiResources.LibraryManagementBaseUrl.getResource();
	
		given().log().all().header("Content-Type", ContentType.JSON).body(book).
		when().post(ApiResources.postBook.getResource()).then().assertThat().statusCode(HttpURLConnection.HTTP_OK).body("Msg", equalTo("successfully added")).log().all();
	}
}
