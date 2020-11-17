package apiTest;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

public class tttt {
	public static void main(String[] args) throws Exception {
		
		HttpResponse<String> response = Unirest.get("http://apis.data.go.kr/1741000/EmergencyAssemblyArea_Earthquake/getAreaList?serviceKey=jNvv%2FhQQ0GtPgbWU5%2Fx3oYY9ozWAdBNQZWovHiGsG1ay%2BcUw0AvcXcdD0EvdfIq7TuMZ0Wf7YLwxjQSYXUu1Nw%3D%3D&type=json&flag=N&pageNo=10&numOfRows=1000")
				  .asString();
		//System.out.println("response :" + response);
		//System.out.println("response.getBody() :" + response.getBody());
		
		JsonParser parser = new JsonParser();
		JsonObject result_object = (JsonObject) parser.parse(response.getBody());
		
		//System.out.println(result_object.get("EarthquakeOutdoorsShelter").getAsJsonArray().get(1).getAsJsonObject().get("row").getAsJsonArray().get(0).getAsJsonObject().get("vt_acmdfclty_nm").getAsString());
		//System.out.println(result_object.get("EarthquakeOutdoorsShelter").getAsJsonArray().get(1).getAsJsonObject().get("row").getAsJsonArray().get(1).getAsJsonObject().get("vt_acmdfclty_nm").getAsString());
		//System.out.println(result_object.get("EarthquakeOutdoorsShelter").getAsJsonArray().get(1).getAsJsonObject().get("row").getAsJsonArray().get(2).getAsJsonObject().get("vt_acmdfclty_nm").getAsString());
		
		String vt_acmdfclty_nm = result_object.get("EarthquakeOutdoorsShelter").getAsJsonArray().get(1).getAsJsonObject().get("row").getAsJsonArray().get(0).getAsJsonObject().get("vt_acmdfclty_nm").getAsString();
		
		System.out.println(vt_acmdfclty_nm);

		// 메이븐 추가
//		<!-- api test -->
//		<!-- https://mvnrepository.com/artifact/com.mashape.unirest/unirest-java -->
//		<dependency>
//			<groupId>com.mashape.unirest</groupId>
//			<artifactId>unirest-java</artifactId>
//			<version>1.4.9</version>
//		</dependency>
//
//		<!-- https://mvnrepository.com/artifact/com.google.code.gson/gson -->
//		<dependency>
//			<groupId>com.google.code.gson</groupId>
//			<artifactId>gson</artifactId>
//			<version>2.8.5</version>
//		</dependency>
//		<!-- api test -->
	}

}
