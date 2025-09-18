package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name="logindata")
	public Object[][] allUserLogindata() throws IOException 
	{
		ExcelUtility excl = new ExcelUtility(".//testData//ATA.xlsx");

		int rowCount = excl.getRowCount("Sheet1");
		int cellCount = excl.getCellCount("Sheet1", 1);
		
		Object logindata[][]= new String[rowCount][cellCount];
		
		for(int i=1; i<=rowCount; i++) 
		{
			for(int j=0; j<cellCount; j++) 
			{
				logindata[i-1][j] = excl.getCellData("Sheet1", i, j);
			}
		}
		return logindata;

	}
	
	
}
