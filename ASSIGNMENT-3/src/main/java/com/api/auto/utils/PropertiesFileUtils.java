package com.api.auto.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.util.Properties;

public class PropertiesFileUtils {

	public static String getProperties (String key) {
		String result = "";
		File file = new File("./configuration/configs.properties");
		try {
			FileReader fileReader = new FileReader(file);
			Properties prop = new Properties();
			prop.load(fileReader);
			result = prop.getProperty(key);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("File not found!");
		}
		return result;
	}
	
	
	public static void saveToken(String tokenValue) {
		Properties prop = new Properties();
		File tokenfile = new File("./configuration/token.properties");
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(tokenfile);
		
		prop.setProperty("tokenKey", tokenValue);
		prop.store(fileWriter, "save token to property file");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static String getToken() {
		String result = "";
		File file = new File("./configuration/token.properties");
		try {
			FileReader fileReader = new FileReader(file);
			Properties prop = new Properties();
			prop.load(fileReader);
			result = prop.getProperty("tokenKey");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("File not found!");
		}
		return result;
	}
}
