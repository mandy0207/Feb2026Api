package basics;

import static io.restassured.RestAssured.given;

import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import enums.ApiResources;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import pojo.Book;

public class LearnDeserialization {


	@Test
	public void deserializeTest() throws JsonMappingException, JsonProcessingException {
		RestAssured.baseURI=ApiResources.LibraryManagementBaseUrl.getResource();
		String getBookResponse=given().log().all().header("Content-Type",  ContentType.JSON).queryParam("ID", "193ml18s8st21447")
				.when().get(ApiResources.getBook.getResource()).then().assertThat().statusCode(HttpURLConnection.HTTP_OK).log().all().extract().response().asString();
		
		ObjectMapper mapper= new ObjectMapper();
		Book[] bookArray=mapper.readValue(getBookResponse, Book[].class);
		
		List<Book> books = Arrays.asList(bookArray);
		
		for(Book book : books)
		{
			System.out.println("Printed Value :" + book.getAuthor() + " "+ book.getName() );
		}
		
	
	}
}

