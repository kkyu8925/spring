package apiTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class weatherapi {

	public static void main(String[] args) throws UnirestException {
		HttpResponse<String> response = Unirest.get(
				"http://apis.data.go.kr/1360000/VilageFcstInfoService/getUltraSrtFcst?serviceKey=ErAsOKr9XXxDlVbGwGEARx5X%2BO5sTL0Ark1N59jOP6KusH%2BMLk2L05SbnYSPSVjt0ln%2FWPZb2YQ6DLqMAG22eg%3D%3D&numOfRows=100&pageNo=1&base_date=20201013&base_time=1930&nx=55&ny=127&dataType=JSON&category=SKY")
				.header("cache-control", "no-cache").header("postman-token", "a1418027-1fcb-6194-cd9e-f9fbedaeaefd")
				.asString();

		JsonParser parser = new JsonParser();
		JsonObject result_object = (JsonObject) parser.parse(response.getBody());

		JsonArray jArray = (JsonArray) result_object.get("response").getAsJsonObject().get("body").getAsJsonObject()
				.get("items").getAsJsonObject().get("item").getAsJsonArray();

		System.out.println(jArray.size());

		Iterator<JsonElement> JI = jArray.iterator();
		while (JI.hasNext()) {
			System.out.println(JI.next());
		}
	}

}
