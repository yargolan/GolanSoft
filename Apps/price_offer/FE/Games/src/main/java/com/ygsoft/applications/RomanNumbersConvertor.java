package com.ygsoft.applications;


public class RomanNumbersConvertor {
	
	
	static String romanNumber = "MCMXLVIII";
	
	
	public static void main(String[] args) {
		
		convert_1();
		
		convert_2();
	}
	
	
	
	private static void convert_1 () {
		
		RomanNumbersDictionary rnd = new RomanNumbersDictionary();
		
		int decimalNumber = 0, index = 0;
		
		while (index + 1 < romanNumber.length())
		{
			int currentNumberValue = rnd.valueOf(String.valueOf(romanNumber.charAt(index)));
			int nextNumberValue    = rnd.valueOf(String.valueOf(romanNumber.charAt(index+1)));
			
			if (nextNumberValue > currentNumberValue)
			{
				decimalNumber += (nextNumberValue - currentNumberValue);
				index++;
			}
			else
			{
				decimalNumber += currentNumberValue;
			}
			
			index++;
		}
		
		if (index != romanNumber.length());
		{
			decimalNumber += rnd.valueOf(String.valueOf(romanNumber.charAt(index)));
		}
		
		
		System.out.println("The decimal number is : " + decimalNumber);
	}
	
	
	
	
	
	private static void convert_2 () {
		
		RomanNumbersDictionary rnd = new RomanNumbersDictionary();
		
		int decimalNumber = 0, currentNumberValue = 0;
		
		String[] numberParts = romanNumber.split("");
		
		int index = 0;
		
		while (index + 1 < numberParts.length)
		{
			String currentNumber = numberParts[index];
			currentNumberValue = rnd.valueOf(currentNumber);
			
			int nextNumberValue = rnd.valueOf(numberParts[index+1]);
			
			if (nextNumberValue > currentNumberValue)
			{
				decimalNumber += (nextNumberValue - currentNumberValue);
				index++;
			}
			else
			{
				decimalNumber += currentNumberValue;
			}
			
			index++;
		}
		
		
		if (index != numberParts.length)
		{
			decimalNumber += rnd.valueOf(numberParts[index]);
		}
		
		
		System.out.println("The decimal number is : " + decimalNumber);
	}
}
