package com.sqs.main.generic;

import java.io.IOException;

import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.parser.PdfTextExtractor;

public class PDFReader 
{
	private PdfReader pdfReader;

	/**
	 * @Method     : public PDFReader( String pdfFilePath )
	 * @Description: Constructor to initialize PDFReader class */
	public PDFReader( String pdfFilePath ) throws IOException
	{
		this.pdfReader = new PdfReader( pdfFilePath );
	}

	/**
	 * @Method     : public int getNumberOfPages()
	 * @Description: Get total number of pages PDF have */
	public int getNumberOfPages()
	{
		return this.pdfReader.getNumberOfPages();
	}

	/**
	 * @Method     : public String[] getPDFPageContent( int pageNumber )
	 * @Description: Get page content as per the following parameter entry
	 *				 0     :  Get All pdf content
	 *				 x > 0 :  Content on specific page ( if knows page count )
	 **/
	public String[] getPDFPageContent( int pageNumber ) throws IOException
	{
		String[] pdfContent = null;
		int pdfPages;

		pdfPages = pdfReader.getNumberOfPages();
		PdfTextExtractor pdfTextExtractor = new PdfTextExtractor( pdfReader );

		if( pageNumber == 0 )
		{
			String content = "";
			for (int index = 1; index <= pdfPages ; index++) 
				content = content + pdfTextExtractor.getTextFromPage(index);  
			pdfContent = content.split("\n");
		}
		else if( pageNumber != 0 && pdfPages >= pageNumber )
			pdfContent = pdfTextExtractor.getTextFromPage(pageNumber).split("\n");

		return pdfContent;
	}

	/**
	 * @Method     : public boolean verifyContentInPDF( String content, int pageNumber ) 
	 * @Description: Verify page content as per the following parameter entry
	 *				 0     :  Search in All pdf text content
	 *				 x > 0 :  Search on specific page ( if knows page count )
	 **/
	public boolean verifyContentInPDF( String content, int pageNumber ) throws IOException
	{
		String[] pageLines = this.getPDFPageContent( pageNumber );

		if(pageLines.length > 0)
		{
			for (String line : pageLines) 
			{
				line = line.replace("  ", " ");
				System.out.println(">>>> " + line);
				if( line.contains( content ))
					return true;
			}
		}
		return false;
	}
}
