package dataProvider;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import utility.ExcelReader;

public class CustomDataProvider {

	@DataProvider(name="login")
	public static Object[][] getData() throws IOException
	{
		return	ExcelReader.getDataFromSheet("Login");
	}
	
	
}
