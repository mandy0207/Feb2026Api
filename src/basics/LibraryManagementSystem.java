package basics;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
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
		
		RestAssured.baseURI="http://216.10.245.166";
		//postcall
		String addBookResponse=given().log().all().header("Content-Type", "application/json").body(Payloads.bookPayload(isbn, aisle)).
		when().post("/Library/Addbook.php").then().assertThat().statusCode(200).body("Msg", equalTo("successfully added")).log().all().extract().response().asString();
		
		String actualMsg=JSONParser.getjsonParser(addBookResponse).get("Msg");
		String bookId= JSONParser.getjsonParser(addBookResponse).getString("ID");
		Assert.assertEquals(actualMsg, "successfully added", "Key mismatch");
	
		//getCall
		String getBookResponse=given().log().all().header("Content-Type", "application/json").queryParam("ID", bookId)
		.when().get("/Library/GetBook.php").then().assertThat().statusCode(200).log().all().extract().response().asString();
		
		String actualIsbn=JSONParser.getjsonParser(getBookResponse).getList("isbn").get(0).toString();
		String actualAisle=JSONParser.getjsonParser(getBookResponse).getList("aisle").get(0).toString();
		String combinedID =actualIsbn+actualAisle;
		
		Assert.assertEquals(bookId, combinedID , "Business Logic wrong");
		
		
	}
	
}
