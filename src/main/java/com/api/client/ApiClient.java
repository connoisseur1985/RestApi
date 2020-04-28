package com.api.client;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.api.base.TestBase;

public class ApiClient extends TestBase{

	public ApiClient() throws IOException {
		
		super();
	}

	public void get(String url) throws ClientProtocolException, IOException {
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet);
		
		int status = closeableHttpResponse.getStatusLine().getStatusCode();
		
		System.out.println("Status of response is : "+status);
		
		String response = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
		JSONObject jsonObject = new JSONObject(response);
		
		System.out.println("JSON Object is : "+jsonObject);
		
		Header[] headers = closeableHttpResponse.getAllHeaders();
		
		HashMap<String,String> headersMap = new HashMap<String,String>(); 
		for(Header head : headers) 
		{
			headersMap.put(head.getName(), head.getValue());
		}
		
		System.out.println("All Headers are : "+headersMap);
		
		
	}
	
}
