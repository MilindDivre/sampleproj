package com.sqs.test.java.pdfVerification;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class PdfReader1 {
	static WebDriver driver;
	
		    public static void main(String args[]) {
	       
		    	System.setProperty("Webdriver.chrome.driver", "c:/chromedriver.exe");
		    	driver= new ChromeDriver();
		    	try {
		    	    String text = getText(new File("C:\\testPDF\\CINV-US.pdf"));
		    	    //System.out.println("Text in PDF: " + text);
		    	    String previousBalance =  getWebElementText("//span[contains(text(),'Previous Balance')]");
		    	    System.out.println(text);
		    	    compareText(text,previousBalance);
		    	} catch (IOException e) {
		    	    e.printStackTrace();
		    	}
		    	//driver.quit();
	    }
		    static String getText(File pdfFile) throws IOException {
		        PDDocument doc = PDDocument.load(pdfFile);
		        PDFTextStripper stp = new PDFTextStripper();
		        stp.setEndPage(1);
		        stp.setStartPage(1);
		        //return new PDFTextStripper().getText(doc);
		        return stp.getText(doc);
		    }
	
		    static String getWebElementText(String xpath)
		    {
		    	driver.get("C:\\testPDF\\insurance.htm");
		    	String value = driver.findElement(By.xpath(xpath)).getText();
		    	return value;
		    }
		    
		    static void compareText(String pdftext, String toCompare)
		    {
		    	
		    	String[] spiltpdfText = pdftext.split("\\n");
		    	
		    	int flag = 0;
		    	for (int i = 0; i < (spiltpdfText.length)-1; i++)
				{	
		    		
		    		if (spiltpdfText[i].contains(toCompare))
					{
						flag=1;
						break;
					}
		    		
		    		else 
		    			flag=0;
					
				}
		    	if (flag==1)
		    	
		    		System.out.println("Match found :: " + toCompare);
		    	
		    	else
		    		System.out.println("Matching string not found");
		    	
		    	
		   
		    }
}
