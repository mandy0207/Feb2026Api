package basics;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.net.HttpURLConnection;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import enums.ApiResources;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import payloads.Payloads;
import utils.JSONParser;

public class LibraryManagementSystem {

	@Test
	public void E2ETest() {
	
//		given baseurl, headers, queryparams,body
//		when post,get(any) is made
//		then response is deliverd and validate it
		Faker faker= new Faker();
		String isbn=faker.internet().password();
		String aisle= faker.number().digits(4);
		
		RestAssured.baseURI=ApiResources.LibraryManagementBaseUrl.getResource();
		//postcall
		String addBookResponse=given().log().all().header("Content-Type", ContentType.JSON).body(Payloads.bookPayload(isbn, aisle)).
		when().post(ApiResources.postBook.getResource()).then().assertThat().statusCode(HttpURLConnection.HTTP_OK).body("Msg", equalTo("successfully added")).log().all().extract().response().asString();
		
		String actualMsg=JSONParser.getjsonParser(addBookResponse).get("Msg");
		String bookId= JSONParser.getjsonParser(addBookResponse).getString("ID");
		Assert.assertEquals(actualMsg, "successfully added", "Key mismatch");
	
		//getCall
		String getBookResponse=given().log().all().header("Content-Type",  ContentType.JSON).queryParam("ID", bookId)
		.when().get(ApiResources.getBook.getResource()).then().assertThat().statusCode(HttpURLConnection.HTTP_OK).log().all().extract().response().asString();
		
		String actualIsbn=JSONParser.getjsonParser(getBookResponse).getList("isbn").get(0).toString();
		String actualAisle=JSONParser.getjsonParser(getBookResponse).getList("aisle").get(0).toString();
		String combinedID =actualIsbn+actualAisle;
		
		Assert.assertEquals(bookId, combinedID , "Business Logic wrong");
		
		
		//delete call
		String deleteBookResponse=given().log().all().header("Content-Type", ContentType.JSON).body(Payloads.deleteBookPayload(bookId))
				.when().get(ApiResources.deleteBook.getResource()).then().assertThat().statusCode(HttpURLConnection.HTTP_OK).log().all().extract().response().asString();
		
		String actualDeletedMsg=JSONParser.getjsonParser(deleteBookResponse).get("msg");
		Assert.assertEquals(actualDeletedMsg, "book is successfully deleted");
	}
	
}
