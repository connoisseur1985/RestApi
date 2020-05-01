package com.api.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.w3c.dom.html.HTMLObjectElement;

import com.api.base.TestBase;
import com.api.client.ApiClient;
import com.api.util.Utilities;
public class ApiGetTest extends TestBase{

	
	String uri;
	ApiClient apiClient;
	CloseableHttpResponse closeableHttpResponse;
	String jsonString;
	
	JSONObject jsonResponse;
	
	public ApiGetTest() throws IOException {
		super();
		
	}
	
	@BeforeMethod
	public void setUp() throws IOException 
	{
		uri = prop.getProperty("endpointUrl")+prop.getProperty("serviceUrl");
		apiClient = new ApiClient();
		closeableHttpResponse = apiClient.get(uri);
		
		//JSONObjects and JSONArrays response.
		
		jsonString = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
		//jsonResponse = new JSONObject(jsonString);
		
	}
	
	@AfterMethod
	public void tearDown() 
	{
		
	}
	@BeforeTest
	public void startTesting() 
	{
		System.out.println("Start Testing");
		
	}
	@AfterClass
	public void CloseTesting() 
	{
		//softAssert.assertAll();
	}
	
	@Test(enabled=false)
	public void verifyStatus() throws ClientProtocolException, IOException 
                           	{
		
		Assert.assertEquals(closeableHttpResponse.getStatusLine().getStatusCode(),200);
		
	}
	@Test(enabled=false)
	public void verifyJsonObjects() throws ParseException, IOException 
	{
		SoftAssert softAssert = new SoftAssert();
				
	
		
		String pathValue = Utilities.getValueByJPath(jsonResponse, "/page");
		
		softAssert.assertEquals(pathValue, "2", "Path Value is not Correct");
		
	
		softAssert.assertAll();
		
}
	
	@Test(priority=0,enabled=false)
	public void verifyJsonArrayValues() 
	{
		
		String string_id = Utilities.getValueByJPath(jsonResponse, "/data[0]/id");
		String email = Utilities.getValueByJPath(jsonResponse, "/data[0]/email");
		String first_name = Utilities.getValueByJPath(jsonResponse, "/data[1]/first_name");
		String last_name = Utilities.getValueByJPath(jsonResponse, "/data[0]/last_name");
		String avatar = Utilities.getValueByJPath(jsonResponse, "/data[0]/avatar");
		
		System.out.println(string_id);
		System.out.println("First Name of second data is : "+first_name);
		Assert.assertEquals(string_id, "7","Id value does not match");
		
	}
	
	@Test
	public void verifyHeaderValues() 
	{
		HashMap<String,String> hashHeaders = Utilities.getHeaderResponse(closeableHttpResponse);
		
		System.out.println("Header HashMap Values are : "+hashHeaders);
		
		Assert.assertEquals(hashHeaders.get("Connection"),"keep-alive");
	}
	
	@Test
	public void verifyApiWithHeaders() throws ClientProtocolException, IOException 
	{
		HashMap<String,String> hashHeaders = new HashMap<String,String>();
		hashHeaders.put("Content-Type", "application/json");
		hashHeaders.put("user", "abcdef");
		hashHeaders.put("pass","passabcdef");
		
		closeableHttpResponse = apiClient.get(uri, hashHeaders);
		
		Header[] header = closeableHttpResponse.getAllHeaders();
		HashMap<String,String > hashHeaders1 = new HashMap<String,String>();
		for(Header head : header) 
		{
			hashHeaders1.put(head.getName(),head.getValue());
		}
		
		System.out.println(hashHeaders1.get("Content-Type"));
		}
	}
	
