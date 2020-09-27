package com.example.Connect4;

import java.util.UUID;

public class Connect4GamePlay {

	static char[][] grid = new char[6][7];
	int[] rowIndex;
	int[] columnIndex;

	static int turn;
	static char player ='Y';
	static boolean winner = false;

	public UUID generateUUID() {
		return UUID.randomUUID();
	}

	public static void reset() {

		//initialize array
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[0].length; col++) {
				grid[row][col] = 'E';
			}
		}

		turn = 1;
		player = 'Y';
		winner = false;
	}

	public static int playAturn(int play) {

		//play a turn
		if(turn>42 || winner ==true) {
			return -1;
		}

			boolean validPlay;
				//validate play
			validPlay = validate(play,grid);

				if(!validPlay) {
					return -1;
				}

			//drop the checker
			for (int row = grid.length-1; row >= 0; row--){
				if(grid[row][play] == 'E'){
					grid[row][play] = player;
					break;
				}
			}

			//determine if there is a winner
			winner = isWinner(player,grid);

			if(winner) {
				if(turn %2 ==0) {
					return 2;
				}
				return 3;
			}

			//switch players
			if (player == 'R'){
				player = 'Y';
			}else{
				player = 'R';
			}

			turn++;



		return 1;
	}


	public static boolean validate(int column, char[][] grid){
		//valid column?
		if (column < 0 || column > grid[0].length){
			return false;
		}

		//full column?
		if (grid[0][column] == 'Y' || grid[0][column] == 'R'){
			return false;
		}

		return true;
	}

	public static boolean isWinner(char player, char[][] grid){
		//check for 4 across
		for(int row = 0; row<grid.length; row++){
			for (int col = 0;col < grid[0].length - 3;col++){
				if (grid[row][col] == player   &&
						grid[row][col+1] == player &&
						grid[row][col+2] == player &&
						grid[row][col+3] == player){
					return true;
				}
			}
		}
		//check for 4 up and down
		for(int row = 0; row < grid.length - 3; row++){
			for(int col = 0; col < grid[0].length; col++){
				if (grid[row][col] == player   &&
						grid[row+1][col] == player &&
						grid[row+2][col] == player &&
						grid[row+3][col] == player){
					return true;
				}
			}
		}
		//check upward diagonal
		for(int row = 3; row < grid.length; row++){
			for(int col = 0; col < grid[0].length - 3; col++){
				if (grid[row][col] == player   &&
						grid[row-1][col+1] == player &&
						grid[row-2][col+2] == player &&
						grid[row-3][col+3] == player){
					return true;
				}
			}
		}
		//check downward diagonal
		for(int row = 0; row < grid.length - 3; row++){
			for(int col = 0; col < grid[0].length - 3; col++){
				if (grid[row][col] == player   &&
						grid[row+1][col+1] == player &&
						grid[row+2][col+2] == player &&
						grid[row+3][col+3] == player){
					return true;
				}
			}
		}
		return false;
	}
}
