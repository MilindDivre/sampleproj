package testAllure;


import static org.testng.AssertJUnit.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.Test;

import ru.yandex.qatools.allure.annotations.Step;


public class allurelogs
{
	@Step("Test URL12")
	@Test
	public void testURL()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		 WebDriver fdriver = new ChromeDriver();
		 fdriver.get("http://www.roomrhino.com");
		 fdriver.findElement(By.id("keyword")).sendKeys("abc");
		 //assertTrue(2 == 2);
		assertTrue("Result not equals to 4", 2 * 2 == 4);
		 
	}
	
}
