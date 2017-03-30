package testWin;

import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;

public class OutlookWinum {

	@Title("Outllok Winium")
	@Test
	public void test() throws IOException{
		DesktopOptions options= new DesktopOptions();
		Utilities objUtilities = new Utilities();
		//options.setApplicationPath("C:\\WINDOWS\\system32\\notepad.exe");
		options.setApplicationPath("C:\\Program Files\\Microsoft Office\\Office15\\OUTLOOK.EXE");
		try{
			WiniumDriver driver=new WiniumDriver(new URL("http://localhost:9999"),options);
			objUtilities.logReporter("Initiated Winium Driver", true);
			//driver.findElementByClassName("Edit").sendKeys("This is sample test");
			
			System.out.println("Start Finding element");

			driver.findElementByName("New Email").click();
			//Enter data in to
			Thread.sleep(5000);
			objUtilities.logReporter("Sucessfully clicked on new mail button", true);
			driver.findElementByName("To").click();
			driver.findElementByName("To").sendKeys("Milind.divre@sqs.com");
			objUtilities.logReporter("Entered To ", true);
			//Enter data in CC
			driver.findElementByName("Cc").sendKeys("Milind.divre@sqs.com");
			//Enter data in Subject
			// Thread.sleep(5000);
			//driver.findElementByName("Subject").sendKeys("This is a automated mail");
			//Click on Send Button
			driver.findElementByName("Send").click();
			objUtilities.logReporter("Clicked on Send Button", true);
			//Click on Send Anyway button
			driver.findElementByName("Send Anyway").click();

			// driver.close();	  
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

}
