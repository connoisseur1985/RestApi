package com.api.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.api.base.TestBase;

public class ApiClient extends TestBase{

	public ApiClient() throws IOException {
		
		super();
	}

	//get method without headers
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet);
		
		return closeableHttpResponse;
	}
	
	//get method with headers
	public CloseableHttpResponse get(String url, HashMap<String,String> hashHeaders) throws ClientProtocolException, IOException 
	{
		CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		
		Set<Entry<String,String>> entrySet = hashHeaders.entrySet();
		
		for(Entry<String,String> entry : entrySet) 
		{
			httpGet.addHeader(entry.getKey(),entry.getValue());
		}
		
		CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpGet);
		
		return closeableHttpResponse;
	}
	
	public CloseableHttpResponse post(String url, String jsonEntity, HashMap<String,String> hashHeaders) throws ClientProtocolException, IOException 
	{
		CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		
		//Add Headers to client.
		for(Map.Entry<String, String> entry : hashHeaders.entrySet()) 
		{
			httpPost.addHeader(entry.getKey(),entry.getValue());
		}
		
		//Add JSON Payload to client.
		httpPost.setEntity(new StringEntity(jsonEntity));
		
		
		CloseableHttpResponse closeableHttpResponse =  closeableHttpClient.execute(httpPost);
		return closeableHttpResponse;
		
	}
}
