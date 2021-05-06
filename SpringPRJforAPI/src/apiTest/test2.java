package apiTest;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

public class test2 {
	public static void main(String[] args) throws Exception {
		HttpResponse<String> response = Unirest
				.get("http://api.openweathermap.org/data/2.5/weather?q=seoul&appid=APIKEY").asString();
		System.out.println("response :" + response); // 객체
		System.out.println("response.getBody() " + response.getBody());

		JsonParser parser = new JsonParser();
		JsonObject result_object = (JsonObject) parser.parse(response.getBody());

		System.out.println("result_object " + result_object);
		System.out.println(result_object.get("weather"));
		System.out.println(result_object.get("weather").getAsJsonArray());

		System.out.println("---------------------------------------------------");
		// System.out.println(result_object.get("weather").get(0)); 배열!
		System.out.println(result_object.get("weather").getAsJsonArray().get(0));
		System.out.println("---------------------------------------------------");

		System.out.println(
				result_object.get("weather").getAsJsonArray().get(0).getAsJsonObject().get("main").getAsString());
	}

}
