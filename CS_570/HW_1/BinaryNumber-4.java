//Name: Rachi Rana
//CWID: 10455300

//package lesson1;

import java.util.Arrays;
import java.lang.*;
import java.util.*;

public class BinaryNumber {
	
	private int data[]; //Field array define
	private boolean overflow; //To check overflow value define
			
	public BinaryNumber(int length) { //Creating a binary number of length, consisting only of zeros
		data = new int[length];
				
		//Initializing the data array with 0s
		for(int i=0; i<length; i++) {
			data[i]=0;
			}	
	}
			
	
	public BinaryNumber(String str){ //Creating a binary number given a string
		data = new int[str.length()];
				
		for(int i=0; i<str.length(); i++) {
			data[i] = java.lang.Character.getNumericValue(str.charAt(i));
		}	
		System.out.println("From string:"+ str);
		for(int i=0; i<str.length(); i++) {
				System.out.println(data[i]);
			
		}
	}
			
			
	public int getLength(){ //Determine the length of a binary number
		return data.length;
	}
			
			
	public int getDigit(int index){ //Obtain a digit of a binary number given an index
		if(index>data.length){
					
		System.out.println("Warning! index out of bounds");
		return 0;
		}
		else{
			return data[index];
		}
	}	
	
	public void shiftR(int amount){ //Shifting all digits in a binary number any number of places to the right
		if(amount < 0) {
			System.out.println("Enter positive value");
			System.exit(1);
		}

		BinaryNumber reallocate = new BinaryNumber(data.length + amount);
			for(int i = amount; i < reallocate.getLength(); i++) {
			reallocate.data[i] = data[i - amount];
			}
			
			this.data = reallocate.data;
			System.out.println("Number after shift is: " + this.toString());
	}
			

	public void add(BinaryNumber aBinaryNumber){ //Add abinary number to binary number 
		if(aBinaryNumber.getLength() != data.length){
			System.out.println("Binary numbers length do not coincide");
		} 
		else{
			System.out.print("Addition of " + toString() + " and " + aBinaryNumber + "= ");
			int carry = 0;
			int sum[] = new int[data.length];
			for (int i = 0; i < data.length; i++){
			int cB = carry + data[i] + aBinaryNumber.getDigit(i);
			
			if (cB == 0){ //cB is carried bit
				sum[i] = 0;
				carry = 0;}
						
			if (cB == 1){
				sum[i] = 1;
				carry = 0;}
						
			if (cB == 2){
				sum[i] = 0;
				carry = 1;}
						
			if (cB == 3){
				sum[i] = 1;
				carry = 1;}
						
		}
		
		data = sum;

		if (carry == 1){
			overflow = true;
		}
					
		System.out.println(toString());
		}
	}

			
	public String toString(){ //Transforming a binary number to a String
		String string = "";
		        
		for (int i = 0; i < data.length; i++){
			string += data[i];
		}

		if (overflow == true){ //Overflow is true check
			return "Overflow";
		} 
		else{
		    return string;
		}
	}
			
	
	public int toDecimal(){//Transforming a binary number to its decimal notation
		int decimal = 0;
		
		for(int i = 0; i< data.length; i++){
			decimal= (int)(decimal + data[i] * Math.pow(2, i));
		}
		return decimal;
	}
			
			
	public void clearOverflow(){//Clears the overflow flag
		overflow = false;
		System.out.println("Overflow False ");
	}

	
	/*public static void main(String[] args){
		
		BinaryNumber b3 = new BinaryNumber(7);
		
		BinaryNumber b0 = new BinaryNumber("10110");
		BinaryNumber b1 = new BinaryNumber("11101");
		BinaryNumber b2 = new BinaryNumber("11100");
		BinaryNumber b4 = new BinaryNumber("1011");
        
		//b1.toString();
		//System.out.println();
		
		System.out.println(b3.toString());
		
		System.out.println(b2 + " Length : " + b2.getLength());
        System.out.println("Digit at given index  : " + b1.getDigit(3));
		System.out.println(b4 + " Decimal Value  :  " + b4.toDecimal());
		
		b1.shiftR(3);
        b0.add(b2);
		b0.add(b1);

	}*/
	
}


