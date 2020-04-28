package com.api.test;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.base.TestBase;
import com.api.client.ApiClient;

public class RestApiTest extends TestBase{

	
	String uri;
	ApiClient apiClient;
	
	public RestApiTest() throws IOException {
		super();
		
	}
	
	@BeforeMethod
	public void setUp() throws IOException 
	{
		uri = prop.getProperty("endpointUrl")+prop.getProperty("serviceUrl");
		apiClient = new ApiClient();
		
	}
	@Test
	public void callGet() throws ClientProtocolException, IOException 
	{
		apiClient.get(uri);
	}

}
