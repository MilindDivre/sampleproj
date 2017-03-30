package testWin;

import org.testng.Assert;
import org.testng.Reporter;

import ru.yandex.qatools.allure.annotations.Step;

public class Utilities 
{

	@Step("{0}")
	public void logReporter(String step, boolean resultLog)
	{
		if(resultLog)
		{
			Reporter.log("Step >> " + step);
			Assert.assertTrue(true);
		}
		else  
		{
			Reporter.log("Step >> " + step);
			Assert.assertTrue(false);
		}	
	}

}
