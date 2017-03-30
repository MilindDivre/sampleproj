package testJava;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * this class is used  test java concepts 
 * @author divrem
 *date:10 th march
 */
public class teststring {
	int i = 50;
	final int speed;
	/**this method is used to test by ref conept in java*/
	public void testFunc(int b)
	{
		b = b + i;
		System.out.println("in fuvnr"+b);
		
	}
	
	{
		speed =150;
	}
	/*teststring()
	{
		speed=100;
	}*/
	
	public void changevalueFinal()
	{
		System.out.println("Speed final variable"+speed);
	}

	public static void main(String[] args) {
		teststring obj = new teststring();
		int b = 70;
		obj.testFunc(b);
		obj.changevalueFinal();
		
		System.out.println("at enbd"+b);
		
		// TODO Auto-generated method stub
 /*String s = "Hello";
 
 System.out.println(s +"-->"+s.hashCode());
 
 s= "world";
 
 System.out.println(s+"---->"+s.hashCode());
 List list = new ArrayList();  
	list.add("sa");
	System.out.println(list.get(0));
	list.add(45);
	System.out.println(list.get(1));
	list.add(45);
	list.add(35);
	list.add(25);
	list.add(15);
	list.add(55);
	Iterator<String> itr = list.iterator();
	try
	{
		while (itr.hasNext())
		{
			System.out.println("values fetched using iterator"+itr.next());
		}
	}catch(ClassCastException ex)
	{
		System.out.println("Error while iterating string elements"+ex.getMessage());
	}
	
	*/

		
	}
	
 

}
