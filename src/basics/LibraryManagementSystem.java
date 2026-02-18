package basics;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import payloads.Payloads;

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
		String addBookResponse=given().log().all().header("Content-Type", "application/json").body(Payloads.bookPayload(isbn, aisle)).
		when().post("/Library/Addbook.php").then().assertThat().statusCode(200).body("Msg", equalTo("successfully added")).log().all().extract().response().asString();
		
		JsonPath js= new JsonPath(addBookResponse);
		String actualMsg=js.get("Msg");
		
		Assert.assertEquals(actualMsg, "successfully added");
		
	}
	
}
