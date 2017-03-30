package scrolldown;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class testScroll {

	public static void main(String[] args) throws InterruptedException 
	{
		// TODO Auto-generated method stub
		WebDriver fdriver = new FirefoxDriver();
		System.out.println("Hello Google...");
		//fdriver.get("http://manos.malihu.gr/repository/custom-scrollbar/demo/examples/complete_examples.html");
		fdriver.manage().window().maximize();
		fdriver.get("http://192.168.192.213:7003/awc/#showGateway");
		//JavascriptExecutor jse = (JavascriptExecutor) fdriver;
		//Thread.sleep(5000);
	    //jse.executeScript("window.scrollBy(0,250)", "");
	    //WebElement element = fdriver.findElement(By.xpath("//*[@id='mCSB_1_dragger_vertical']"));
	    //Function call to Highlight the element
	    //fnHighlightMe(fdriver,element);
	    //Thread.sleep(5000);
		//fdriver.quit();
		fdriver.findElement(By.xpath("//*[@id='gwt-debug-textBox_userName']")).sendKeys("user1");

		fdriver.findElement(By.xpath("//*[@id='gwt-debug-textBox_password']")).sendKeys("user1");
		
		fdriver.findElement(By.xpath("//button[text()='Sign in']")).click();
		Thread.sleep(10000);
		fdriver.findElement(By.xpath("//*[@class='tile-content']/div[@class='document_light icon']")).click();
		Thread.sleep(10000);
	
		
		//fdriver.findElement(By.xpath("//*[@class='tile-content']/div[@class='document_light icon']")).click();
		
		List<WebElement> links = fdriver.findElements(By.xpath("//*[@id='gwt-debug-modelObjectListView']//*[@class='aw-widgets-cellListWidgetContainer']//*[@class='aw-widgets-cellListWidget']/child::li"));
		for (int i = 1; i < links.size(); i++)
		{
		    System.out.println(links.get(i).getText());
		}
		
	}
	
	
}
