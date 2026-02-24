package basics;

import static io.restassured.RestAssured.given;

import java.net.HttpURLConnection;

import org.testng.annotations.Test;

import enums.ApiResources;
import io.restassured.http.ContentType;
import utils.CreateSpec;
import utils.UniqueGenerator;

public class LearnSpecBuilders {

	@Test
	public void learnsSpecBuilderTest() {
		String response=given().spec(CreateSpec.makeRequestSpec(ApiResources.LibraryManagementBaseUrl.getResource(), ContentType.JSON))
				.body(UniqueGenerator.getUniqueBookObject()).when().post(ApiResources.postBook.getResource()).then()
				.spec(CreateSpec.makeResponseSpec(HttpURLConnection.HTTP_OK)).log().all().extract().response().asString();

	}
}
