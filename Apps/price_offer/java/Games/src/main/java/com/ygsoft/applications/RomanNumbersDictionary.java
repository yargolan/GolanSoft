package com.ygsoft.applications;


public class RomanNumbersDictionary {
	
	protected RomanNumbersDictionary() {}
	
	
	protected static final int I =    1;
	protected static final int V =    5;
	protected static final int X =   10;
	protected static final int L =   50;
	protected static final int C =  100;
	protected static final int D =  500;
	protected static final int M = 1000;
	
	
	
	protected int valueOf (String RomanNumber) {
		
		int decimalValue = 0;
		
		if (RomanNumber.compareTo("I") == 0) { decimalValue = I;}
		if (RomanNumber.compareTo("V") == 0) { decimalValue = V;}
		if (RomanNumber.compareTo("X") == 0) { decimalValue = X;}
		if (RomanNumber.compareTo("L") == 0) { decimalValue = L;}
		if (RomanNumber.compareTo("C") == 0) { decimalValue = C;}
		if (RomanNumber.compareTo("D") == 0) { decimalValue = D;}
		if (RomanNumber.compareTo("M") == 0) { decimalValue = M;}
		
		return decimalValue;
	}
}

