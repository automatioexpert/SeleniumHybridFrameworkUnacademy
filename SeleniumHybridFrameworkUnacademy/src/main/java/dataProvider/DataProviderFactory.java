package dataProvider;

import utility.ConfigReader;

public class DataProviderFactory 
{
	
	public static ConfigReader getConfig()
	{
			
		ConfigReader reader=new ConfigReader();
		
		return reader;
		
	}

}
