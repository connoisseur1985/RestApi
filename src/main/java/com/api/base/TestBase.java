package com.api.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {

	
	public FileInputStream fin;
	public Properties prop;
	
	public TestBase() throws IOException {
		
		
		fin = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\api\\config\\config.properties");
		prop = new Properties();
		prop.load(fin);
		
	}

	
	
}
