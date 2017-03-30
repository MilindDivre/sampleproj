package com.sqs.main.generic;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFBoxReader 
{
	private PDDocument pdDocument;
	private File pdfFile;
	private PDFTextStripper pdfTextStripper;

	public PDFBoxReader( String pdfPath ) throws IOException
	{
		this.pdfFile = new File( pdfPath );
		this.pdDocument = PDDocument.load( this.pdfFile );
	}

	public PDFBoxReader( String pdfPath, String password ) throws IOException
	{
		this.pdfFile = new File( pdfPath );
		this.pdDocument = PDDocument.load( this.pdfFile, password );
	}


	public int getTotalNumberOfPages()
	{
		return this.pdDocument.getNumberOfPages();
	}

	public String getContentFromSpecificPage( int pageNumber ) throws IOException
	{
		String content = null;
		int totalNoOfPages = this.getTotalNumberOfPages();
		if( pageNumber > totalNoOfPages )
			throw new RuntimeException("Document has maximum " + totalNoOfPages + "Pages.");

		this.pdfTextStripper = new PDFTextStripper();
		if( pageNumber != 0 )
		{
			this.pdfTextStripper.setStartPage( pageNumber );
			this.pdfTextStripper.setEndPage( pageNumber );
		}
		content = this.pdfTextStripper.getText( pdDocument );
		return content;
	}

	public boolean verifyContentInPDF( String content, int pageNumber ) throws IOException
	{
		String pdfContent = this.getContentFromSpecificPage( pageNumber );
		System.out.println( pdfContent );
		if( pdfContent.contains( content ) )
			return true;	
		return false;
	}

	public boolean verifyContentLineByLineInPDF( String content, int pageNumber ) throws IOException
	{
		String[] pageLines = this.getContentFromSpecificPage( pageNumber ).split("\n");

		if(pageLines.length > 0)
		{
			for (String line : pageLines) 
			{
				System.out.println(">>>> " + line);
				if( line.contains( content ))
					return true;
			}
		}
		return false;
	}
	
	public void closePDF() throws IOException
	{
		this.pdDocument.close();
	}
}


