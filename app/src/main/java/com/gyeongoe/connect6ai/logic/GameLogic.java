package com.gyeongoe.connect6ai.logic;

public class GameLogic {
	// For Line information Blank 0, Black 1, White 2, Ban 3 4^0 to 4^11
	// For Game Logic Blank null, Black 10, White 10, Effect range of black +1, Effect range of White -1, Ban 100
	// Horizontal 0, Vertical 1, Left-Right Diagonal 2, Right-Left Diagonal 3
	
	private int [][]info = new int[3][6];
	private int point[][] = new int[3][6];
	private int x,y;

	for(int i=0; i<3; i++) {
		for(int j=0; j<6; j++) {
			if(i==0) {
				info[i][j] = point[x-j][y-j];
			}
			else if(i==1) {
				info[i][j] = point[x][y-j];
			}
			else if(i==2) {
				info[i][j] = point[x+j][y-j];
			}
			else if(i==3) {
				info[i][j] = point[x+j][y];
			}
		}
	}
}
