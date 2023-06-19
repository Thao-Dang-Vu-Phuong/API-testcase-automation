package com.api.auto.utils_cach_2;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.io.File;

import java.io.IOException;
import java.io.FileWriter;
public class FetchingJson {

	public static void saveToken(String token) {
		JSONObject jsonObject = new JSONObject();
		File file = new File("./json_files/token.json");
		try {
			FileWriter fileWriter = new FileWriter(file);
		
		jsonObject.put("token", token);
		fileWriter.write(jsonObject.toJSONString());
		fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static String getToken() {
		String token = "";
		JSONParser jsonParser = new JSONParser();
		File file = new File("./json_files/token.json");
		try {
			FileReader fileReader = new FileReader(file);
			JSONObject jsonObject = (JSONObject) jsonParser.parse(fileReader);
			token = (String) jsonObject.get("token");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return token;
	}








}
