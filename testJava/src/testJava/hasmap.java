package testJava;

import java.util.HashMap;
import java.util.Map;

public class hasmap {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		HashMap<Integer,String> objMap= new HashMap<Integer,String>();
		objMap.put(1, "Happy");
		objMap.put(2, "Programming");
		objMap.put(3, "Clloection");
		objMap.put(4, "overwhlemed");
				
		for(Map.Entry me : objMap.entrySet())
		{
			System.out.println("Given Key"+me.getKey()+"---Value"+me.getValue());
		}

	}

}
