import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import javax.swing.text.Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;

public class testwinium {
	
	
	     protected static String defaultLogFile = "c:\\msglog.txt";
	    
	         public static void write(String s) throws IOException {
	         write(defaultLogFile, s);
	     }
	    
	         public static void write(String f, String s) throws IOException {
	         TimeZone tz = TimeZone.getTimeZone("EST"); // or PST, MID, etc ...
	         Date now = new Date();
	         DateFormat df = new SimpleDateFormat ("yyyy.mm.dd hh:mm:ss ");
	         df.setTimeZone(tz);
	         String currentTime = df.format(now);
	        
	         FileWriter aWriter = new FileWriter(f, true);
	         aWriter.write(currentTime + " " + s + "\n");
	         aWriter.flush();
	         aWriter.close();
	     }
	         
	         public static void timeout()
	         {
	        	 
	        	 
	        	 
	         }
	 


	public static void main(String[] args) 
	{
		 File file = new File(defaultLogFile);
         
         // creates the file
         file.delete();
		// TODO Auto-generated method stub
		DesktopOptions options= new DesktopOptions();
		Utilities objUtilities = new Utilities();
		//options.setApplicationPath("C:\\WINDOWS\\system32\\notepad.exe");
		long startTime = System.currentTimeMillis();
		options.setApplicationPath("C:\\Program Files\\Microsoft Office\\Office15\\OUTLOOK.EXE");
		try{
			
			WiniumDriver driver=new WiniumDriver(new URL("http://localhost:9999"),options);
			
			System.out.println("Start Finding element");
			new WebDriverWait(driver, 10).until(ExpectedConditions.
			presenceOfElementLocated(By.name("New Email")));
			long endTime = System.currentTimeMillis();
			// Measure total time
			long totalTime = endTime - startTime;
			write("Outlook Load Time: " + totalTime + "milliseconds\n");
			
			driver.findElementByName("New Email").click();
			//Enter data in to
			//Thread.sleep(5000);
			startTime = System.currentTimeMillis();
			new WebDriverWait(driver, 10).until(ExpectedConditions.
					presenceOfElementLocated(By.name("To")));
			endTime = System.currentTimeMillis();
			// Measure total time
			totalTime = endTime - startTime;
			
			write("New mail Load Time: " + totalTime + "milliseconds\n");
			//objUtilities.logReporter("Sucessfully clicked on new mail button", true);
			driver.findElementByName("To").click();
			driver.findElementByName("To").sendKeys("Milind.divre@sqs.com");
			//objUtilities.logReporter("Entered To ", true);
			//Enter data in CC
			driver.findElementByName("Cc").sendKeys("Milind.divre@sqs.com");
			//Enter data in Subject
			// Thread.sleep(5000);
			//driver.findElementByName("Subject").sendKeys("This is a automated mail");
			//Click on Send Button
			driver.findElementByName("Send").click();
			//objUtilities.logReporter("Clicked on Send Button", true);
			//Click on Send Anyway button
			//write("Send Anyway Load Time: " + totalTime + "milliseconds\n");
			driver.findElementByName("Send Anyway").click();

			// driver.close();	  
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}


	}


