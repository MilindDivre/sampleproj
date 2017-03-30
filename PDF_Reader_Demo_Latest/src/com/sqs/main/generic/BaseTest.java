package com.sqs.main.generic;

import java.io.FileInputStream;
import java.util.Hashtable;
import java.util.Properties;

public class BaseTest {

	private Properties config;
	private String testDataFilePath;
	private Hashtable<String, String> dataPoolHashTable;

	/**
	 * @Method     : public void initializeConfiguration()
	 * @Description: initialize Configuration file at start of the test suite
	 * */
	public void initializeConfiguration()
	{
		String configPath = "/src/com/sqs/test/resources/config.properties";
		this.config = new Properties();
		try 
		{
			this.config.load(new FileInputStream(System.getProperty("user.dir") + configPath ));
		} 
		catch ( Exception exception) 
		{
			exception.printStackTrace();
		}
	}

	/**
	 * @Method     : public String getConfiguration( String property )
	 * @Description: Returns respective configuration value for given property.
	 * */
	public String getConfiguration( String property )
	{
		return this.config.getProperty( property ).trim();
	}

	/**
	 * @Method     : public Object[][] loadDataProvider( String testCaseID, String testDataFile )
	 * @Description: Returns Array of Hashtable object set as testData for Data provider.
	 * */
	public Object[][] loadDataProvider( String testCaseID, String testDataFile )
	{
		ExcelIterator objExcelIterator = new ExcelIterator();

		this.testDataFilePath = System.getProperty("user.dir") + this.getConfiguration("testData.path") +"/"+ testDataFile + ".xlsx";
		Object[][] dataPool = objExcelIterator.loadTestData( testCaseID, testDataFilePath );
		return dataPool;
	}

	/**
	 * @Method     : public String getAbsolutePDFFilePath( String fileName )
	 * @Description: Returns Absolute path for the entered file name.
	 * */
	public String getAbsolutePDFFilePath( String fileName )
	{
		return System.getProperty("user.dir") + this.getConfiguration("pdf.directory") + "/" + fileName + ".pdf";
	}
	
	/**
	 * @Method     : public void setTestData( Hashtable<String, String> dataSetValue )
	 * @Description: Set test data set to parent class.
	 * */
	public void setTestData( Hashtable<String, String> dataSetValue )
	{
		this.dataPoolHashTable = dataSetValue;
	}

	/**
	 * @Method     : public String dpString( String columnHeader )
	 * @Description: Get test data value against specific column header key.
	 * */
	public String dpString( String columnHeader )
	{
		try
		{
			if(dataPoolHashTable.get(columnHeader) == null)
				return "";
			else
				return dataPoolHashTable.get(columnHeader);
		}
		catch ( Exception exception ) 
		{
			throw new RuntimeException( exception );
		}
	}
}
