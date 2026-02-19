package payloads;

public class Payloads {

	public static String bookPayload(String isbn, String aisle) {
		return  "{ \r\n"
				+ "\"name\":\"Learn Appium Automation with Java\",\r\n"
				+ "\"isbn\":\""+isbn+"\",\r\n"
				+ "\"aisle\":\""+aisle+"\",\r\n"
				+ "\"author\":\"Aswathy Martin\"\r\n"
				+ "}\r\n"
				+ "";
	}
	
	public static String deleteBookPayload(String id) {
		return "{\r\n"
				+ "\"ID\" : \""+id+"\"\r\n"
				+ "} \r\n"
				+ "";
		
	}
}
