package javaBasics;

public class client {

	public static void main(String[] args) {
		
		Vehicle neenaObj= new Vehicle("Car", 50);
		Vehicle aswathyObj= new Vehicle("Bus", 40);
		
		System.out.println("Speed of "+neenaObj.type +" is "+ neenaObj.getSpeed());
		System.out.println("Speed of "+aswathyObj.type +" is "+ aswathyObj.getSpeed());
	}

}
