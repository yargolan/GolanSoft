package com.ygsoft.applications;

public class SudukoBoard {
	
	private int[][] board;
	
	public SudukoBoard () {}
	
	
	
	public void generate () {
		
		int[][] myBoard = new int[9][9];
		
		for (int rows = 0; rows < 9; rows++)
		{
			for (int columns = 0; columns < 9; columns++)
			{
				myBoard[rows][columns] = 0;
			}
		}
		
		this.board = myBoard;
	}
	
	
	
	public int[][] getBoard() {
		return board;
	}



	public void print() {
		for (int rows = 0; rows < 9; rows++)
		{
			for (int columns = 0; columns < 9; columns++)
			{
				System.out.print(board[rows][columns]);
				
				if (columns <= 7)
				{
					System.out.print(",");
				}
				else
				{
					System.out.println();
				}
			}
		}
		System.out.println();
	}



	public void resolve() {
		
		if (isResolved (board))
		{
			return;
		}
		else
		{
			for (int i = 0; i < board.length; i++)
			{
				for (int j = 0; j < board.length; j++)
				{
					String currentPosition = String.valueOf(i) + "," + String.valueOf(j);
					System.out.println("Trying to insert a number into " + currentPosition);
					if (board[i][j] == 0)
					{
						board[i][j] = i+1;
					}
				}
			}
		}
	}
	
	
	
	private boolean isResolved (int[][] checkMe) {
		
		int[] checkBoard = {0,0,0,0,0,0,0,0,0};
		
		// Go over the candidate board and fill the check board.
		for (int i = 0; i < checkMe.length; i++)
		{
			for (int j = 0; j < checkMe.length; j++)
			{
				int currentDigit = checkMe[i][j];
				
				if (currentDigit == 0)
				{
					return false;
				}
				
				checkBoard[currentDigit]++;
			}
		}
		
		for (int i = 0; i < checkBoard.length; i++)
		{
			if (checkBoard[i] != 9)
			{
				return false;
			}
		}
		
		return true;
	}
}
















