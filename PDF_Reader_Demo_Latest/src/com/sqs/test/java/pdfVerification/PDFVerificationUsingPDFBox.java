package com.sqs.test.java.pdfVerification;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sqs.main.generic.BaseTest;
import com.sqs.main.generic.PDFBoxReader;

/**
 * @TestScript : PDFVerification
 * @Description: Verify specific text content is present on the pdf or not */
public class PDFVerificationUsingPDFBox extends BaseTest
{
	private PDFBoxReader pdfBoxReader;

	/** Test Data Provider Initialization */
	@DataProvider(name="TestDataProvider")
	public Object[][] getDataProvider( ) 
	{
		initializeConfiguration();
		String dataFile = "PDF_Verification";
		Object[][] testData = loadDataProvider( "PDFVerification", dataFile );
		return testData;
	}

	/** Test case entry point */
	@Test( dataProvider="TestDataProvider" )
	public void pdfVerification( String strRun, Hashtable<String, String> dataSetValue ) throws IOException 
	{
		this.setTestData( dataSetValue );
		try
		{
			String pdfFilePath = getAbsolutePDFFilePath( dpString("PDF_File") ) ;

			this.pdfBoxReader = new PDFBoxReader( pdfFilePath );	

			int checkCount = Integer.parseInt( dpString("CheckCount") );

			for( int index = 1 ; index <= checkCount ; index ++ )
			{
				String preText = dpString( "PreText" + index );
				String contentToVerify;

				if( preText.equals("") )
					contentToVerify = dpString( "Value" + index );
				else
					contentToVerify = preText + " " + dpString( "Value" + index );

				int pageNumber = Integer.parseInt( dpString("PageNumber") );
				boolean result =  this.pdfBoxReader.verifyContentInPDF( contentToVerify, pageNumber );

				String resultLog = result ? "Content : " + contentToVerify + " found in PDF, on Page Number : " + pageNumber : "Failed to find content : " + contentToVerify + " in PDF, on Page Number : " + pageNumber;
				Reporter.log( resultLog );
				Assert.assertTrue( result, resultLog );
			}

		}
		finally
		{
			this.pdfBoxReader.closePDF();
		}
	}
}
