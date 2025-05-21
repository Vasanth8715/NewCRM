package com.comcast.HMS.generic.fileUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class FileUtility {

		public String getDataFromPropertiesFile(String key) throws Throwable
		{
			FileInputStream fis = new FileInputStream("./data/vTigercommonData.property");
			Properties p = new Properties();
			p.load(fis);
			
			String data = p.getProperty(key);
			
			
			return data;
	}

}
