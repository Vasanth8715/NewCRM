package com.comcast.HMS.generic.fileUtility;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonUtility 
{
	public String getDataFromJSONFile(String key) throws Throwable
	{
		FileReader f = new FileReader("./configAppData/appCommonData.json");
		
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(f);
			
		// Step 2: Convert java object into json object using down casting.
		JSONObject map = (JSONObject)obj;
		
		// Step 3: get the value from json file using key 
		String data = (String) map.get(key);
		
		return data;
	}
}
