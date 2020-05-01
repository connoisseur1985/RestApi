package com.api.util;

import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;

public class Utilities {

	
	public static String getValueByJPath(JSONObject responsejson, String jpath){
		Object obj = responsejson;
		for(String s : jpath.split("/")) 
			if(!s.isEmpty()) 
				if(!(s.contains("[") || s.contains("]")))
					obj = ((JSONObject) obj).get(s);
				else if(s.contains("[") || s.contains("]"))
					obj = ((JSONArray) ((JSONObject) obj).get(s.split("\\[")[0])).get(Integer.parseInt(s.split("\\[")[1].replace("]", "")));
		return obj.toString();
	}
	
	public static HashMap<String,String> getHeaderResponse(CloseableHttpResponse closeableHttpResponse) 
	{
		//Headers response
		
		Header[] header = closeableHttpResponse.getAllHeaders();
		
		HashMap<String,String> hashHeaders = new HashMap<String,String>();
		
		for(Header head : header) 
		{
			hashHeaders.put(head.getName(),head.getValue());
		}
		
		return hashHeaders;
	}
}
