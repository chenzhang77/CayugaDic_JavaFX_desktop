package main;

abstract class B {
	
	abstract int add(int a, int b);
	
}

class Adder extends B {

	@Override
	int add(int a, int b) {
		// TODO Auto-generated method stub
		return a+b;
	}
	
}


public class Solution {
	
	public static void main(String[] args) {
		
		Adder ad = new Adder();
		
		System.out.println(ad.add(3, 4));
	}


}
