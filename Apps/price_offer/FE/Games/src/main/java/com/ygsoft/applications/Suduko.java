package com.ygsoft.applications;

public class Suduko {

	public static void main(String[] args) {
		
		SudukoBoard board = new SudukoBoard ();
		
		// Generate a board for me.
		board.generate();
		
		// Print it.
		board.print();
		
		// resolve it.
		board.resolve();
		
		// print it as a resolved board.
		board.print();
	}

}
