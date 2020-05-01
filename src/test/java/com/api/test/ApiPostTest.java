package com.api.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.base.TestBase;
import com.api.client.ApiClient;
import com.api.data.UsersPost;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ApiPostTest extends TestBase{

	String url;
	ApiClient apiClient;
	public ApiPostTest() throws IOException {
		super();
		
	}
	
	@BeforeMethod
	public void setUp() throws IOException 
	{
		url = prop.getProperty("endpointUrl")+prop.getProperty("serviceUrl_Post");
		apiClient = new ApiClient();
	}
	
	@Test
	public void verifyPostCall() throws ClientProtocolException, IOException 
	{
		//Populate headers.
		HashMap<String,String> hashHeaders = new HashMap<String,String>();
		hashHeaders.put("Content-Type","application/JSON");
		hashHeaders.put("user","Ankur");
		
		//Populate JSON Entity.
		ObjectMapper mapper = new ObjectMapper();
		UsersPost userPost = new UsersPost("morpheus","leader");
		
		//Marshalling
		String jsonEntity = mapper.writeValueAsString(userPost);
		mapper.writeValue(new File("C:\\Users\\ASUS\\eclipse-workspace\\RestApiPractice\\src\\main\\java\\com\\api\\data\\postString.json"), userPost);
		
		
		CloseableHttpResponse closeableHttpResponse = apiClient.post(url, jsonEntity, hashHeaders);
		
		//Test Status
		Assert.assertEquals(closeableHttpResponse.getStatusLine().getStatusCode(), 201);
		
		//Test JSON Response
		String jsonEntityResponse = EntityUtils.toString(closeableHttpResponse.getEntity());
		JSONObject jsonObj = new JSONObject(jsonEntityResponse);
		//Unmarshalling 
		UsersPost userObject = mapper.readValue(jsonEntityResponse, UsersPost.class);
		System.out.println(jsonObj);
		System.out.println(userObject.getCreatedAt());
		Assert.assertEquals(userObject.getName(), "morpheus");
	}
	
}
