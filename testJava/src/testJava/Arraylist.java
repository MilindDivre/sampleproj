package testJava;

import java.util.ArrayList;
import java.util.Collections;

public class Arraylist {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		ArrayList<String> obj =new ArrayList<String>();
		obj.add("Hello");
		obj.add("world");
		obj.add("welcome");
		obj.add("to");
		obj.add("Collections");
		
		System.out.println("curently the elements in arrylist" + obj);
		
		obj.add(1,"Milind");
		obj.add(4,"Divre");
		
		System.out.println("curently the elements in arrylist" + obj);
		
		obj.remove("Milind");
		System.out.println("curently the elements in arrylist" + obj);
		
		obj.remove("World");
		System.out.println("curently the elements in arrylist" + obj);
		
		String str= obj.get(2);
		System.out.println("curently the elements in arrylist" + str);
		
		ArrayList<String> obj1 =new ArrayList<String>(obj.subList(1, 3));
		System.out.println("curently the elements in arrylist" + obj1);
		
		ArrayList<String> obj3 =new ArrayList<String>();
		obj3.add("Hello");
		obj3.add("world3");
		obj3.add("welcome3");
		obj3.add("to3");
		obj3.add("Collections3");
		
		ArrayList<String> obj4 =new ArrayList<String>();
		obj4.addAll(obj);
		obj4.addAll(obj3);
		System.out.println("curently the elements in arrylist obj4" + obj4);
		
		ArrayList<String> obj5 =new ArrayList<String>();
		for(String temp : obj)
		obj5.add(obj3.contains(temp) ? "Yes":"No" );
		System.out.println("In Obj:"+obj);
		System.out.println("In Obj3:"+obj3);
		System.out.println(obj5);
		
		Collections.sort(obj4);
		System.out.println("after sorting"+obj4);
		
		
		
		
		
	}

}
