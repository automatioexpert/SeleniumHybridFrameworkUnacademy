package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	Properties pro;
	
	public ConfigReader()
	{
		try 
		{
			pro=new Properties();
			pro.load(new FileInputStream(new File(System.getProperty("user.dir")+"/Config/config.properties")));
		}
		catch(IOException e)
		{
			System.out.println("Exception "+e.getMessage());
		}
		
	}
	
	public String getProperty(String key)
	{
		return pro.getProperty(key);
	}
	
	
	
	
}
