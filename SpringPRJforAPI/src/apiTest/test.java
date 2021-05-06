package apiTest;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

public class test {
	// https://openweathermap.org/
	public static void main(String[] args) throws Exception {
		HttpResponse<String> response = Unirest
				.get("http://api.openweathermap.org/data/2.5/weather?q=seoul&appid=APIKEY").asString();
		System.out.println("response :" + response); // 객체
		System.out.println("response.getBody() :" + response.getBody());

		JsonParser parser = new JsonParser();
		JsonObject result_object = (JsonObject) parser.parse(response.getBody());

		System.out.println(
				result_object.get("weather").getAsJsonArray().get(0).getAsJsonObject().get("main").getAsString());
		System.out.println("#####################################################");

		// https://ip-api.com/#116.46.227.2
		HttpResponse<String> location_response = Unirest.get("http://ip-api.com/json/naver.com").asString();
		JsonObject location_object = (JsonObject) parser.parse(location_response.getBody());
		String let = location_object.get("lat").getAsString();
		String lon = location_object.get("lon").getAsString();
		String city = location_object.get("city").getAsString();

		HttpResponse<String> weather_response = Unirest
				.get("http://api.openweathermap.org/data/2.5/weather?lat=" + let + "&lon=" + lon + "&appid=APIKEY")
				.asString();

		JsonObject weather_object = (JsonObject) parser.parse(weather_response.getBody());

		String main = weather_object.get("weather").getAsJsonArray().get(0).getAsJsonObject().get("main").getAsString();
		String des = weather_object.get("weather").getAsJsonArray().get(0).getAsJsonObject().get("description")
				.getAsString();

		System.out.println("당신의 위치는" + city + "입니당");
		System.out.println("오늘 날씨는" + main + ", " + des + "입니다.");
	}

}
