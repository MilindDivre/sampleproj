package testJava;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class testTestNG {
	 @BeforeSuite
	   public void beforeSuite() {
	      System.out.println("in beforeSuite in new");
	   }

	   @AfterSuite
	   public void afterSuite() {
	      System.out.println("in afterSuite in new");
	   }
	
	@Test
	public void test1()
	{
		System.out.println("Executing test1");
	}

	@Test
	public void test2()
	{
		System.out.println("Executing test2");
	}

	@Test
	public void test3()
	{
		System.out.println("Executing test3");
	}

	@Test
	public void test4()
	{
		System.out.println("Executing test4");
	}

	@Test
	public void test5()
	{
		System.out.println("Executing test5");
	}

}
