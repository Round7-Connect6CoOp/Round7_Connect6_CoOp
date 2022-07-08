package com.gyeongoe.connect6ai.logic;

import java.awt.Color;

import com.gyeongoe.connect6ai.data.MyData;

public class CheckMatrix {	
	
	public static int setHashKey(int x, int y ) {
		return 19*x+y;
	}
	public void checkMatrix(int i, int j) { 	// White
		System.out.println("I am i n check matrix");
		boolean isItOnLeft = false;
		boolean isItOnRight = false;
		boolean isItOnTop = false;
		boolean isItOnBottom = false;
		boolean isItOnLeftTop = false;
		boolean isItOnRightBottom = false;
		boolean isItOnRightTop = false;
		boolean isItOnLeftBottom = false;
		int[] distance = new int[8] ;
		try {
			for(int n = 0; n<8; n++) {
				for(int a = 1; a<=5;  a++) {
					if(n==0) {
						if(MyData.influnceMatrix[i][j-a]==100) {
							System.out.println("I found black");
							distance[n] = a;
							isItOnLeft = true;
							break;
						}
						else if(MyData.influnceMatrix[i][j-a]==1000) {
							break;
						}
						else if(MyData.influnceMatrix[i][j-a]==-100) {
							continue;
						}
						else {
							MyData.influnceMatrix[i][j-a] -= 1;
						}
					}
					else if(n==1) {
						if(MyData.influnceMatrix[i][j+a]==100) {
							System.out.println("I found black");
							distance[n] = a;
							isItOnRight = true;
							break;
						}
						else if(MyData.influnceMatrix[i][j+a]==1000) {
							break;
						}
						else {
							MyData.influnceMatrix[i][j+a]+=(-1);
						}
					}
					else if(n==2) {
						if(MyData.influnceMatrix[i-a][j]==100) {
							distance[n] = a;
							isItOnTop = true;
							break;
						}
						else if(MyData.influnceMatrix[i-a][j]==1000) {
							break;
						}
						else if(MyData.influnceMatrix[i-a][j]==-100) {
							continue;
						}
						else {
							MyData.influnceMatrix[i-a][j] -= 1;
						}
					}
					else if(n==3) {
						if(MyData.influnceMatrix[i+a][j]==100) {
							distance[n] = a;
							isItOnBottom = true;
							break;
						}
						else if(MyData.influnceMatrix[i+a][j]==1000) {
							break;
						}
						else if(MyData.influnceMatrix[i+a][j]==-100) {
							continue;
						}
						else {
							MyData.influnceMatrix[i+a][j]+=(-1);
						}
					}
					else if(n==4) {
						if(MyData.influnceMatrix[i-a][j-a]==100) {
							distance[n] = a;
							isItOnLeftTop = true;
							break;
						}
						else if(MyData.influnceMatrix[i-a][j-a]==1000) {
							break;
						}
						else if(MyData.influnceMatrix[i-a][j-a]==-100) {
							continue;
						}
						else {
							MyData.influnceMatrix[i-a][j-a] -= 1;
						}
					}
					else if(n==5) {
						if(MyData.influnceMatrix[i+a][j+a]==100) {
							distance[n] = a;
							System.out.println("ddddddddd ");
							isItOnRightBottom = true;
							break;
						}
						else if(MyData.influnceMatrix[i+a][j+a]==1000) {
							break;
						}
						else if(MyData.influnceMatrix[i+a][j+a]==-100) {
							continue;
						}
						else {
							MyData.influnceMatrix[i+a][j+a]+=(-1);
						}
					}
					else if(n==6) {
						if(MyData.influnceMatrix[i+a][j-a]==100) {
							distance[n] = a;
							isItOnRightTop = true;
							break;
						}
						else if(MyData.influnceMatrix[i+a][j-a]==1000) {
							break;
						}
						else if(MyData.influnceMatrix[i][j-a]==-100) {
							continue;
						}
						else {
							MyData.influnceMatrix[i+a][j-a] -= 1;
						}
					}
					else if(n==7) {
						if(MyData.influnceMatrix[i-a][j+a]==100) {
							distance[n] = a;
							isItOnLeftBottom = true;
							break;
						}
						else if(MyData.influnceMatrix[i-a][j+a]==1000) {
							break;
						}
						else if(MyData.influnceMatrix[i][j-a]==-100) {
							continue;
						}
						else {
							MyData.influnceMatrix[i-a][j+a]+=(-1);
						}
					}
				}
			}
		} catch (Exception e) {
			
		}
		
		//END POINT: Matrix scan
		
		for(int n = 0; n < 4; n++) {
			if(n == 0) {
				if((isItOnLeft || isItOnRight) && !(isItOnLeft && isItOnRight)) {
					if(isItOnLeft) {
						System.out.println("It's on left");
						for(int m = 1; m <= 5-distance[0]; m++) {
							if(MyData.influnceMatrix[i][j+m] == -100) continue;
							if(MyData.influnceMatrix[i][j+m] >= 0) {
								MyData.influnceMatrix[i][j+m] = -1;
							}
							for(int a = 0; a<4; a++) {
								int blackCount = 0;
								int whiteCount = 0;
        						for(int b = 0; b<11;  b++) {
        							if(b == 5) continue;
        							if(a == 0) {
        								int c1 = b+5-distance[0];
        								if(c1 >= 11) break;
        								Color c = null;
        								try {
        									c = MyData.pointsTree.get(setHashKey(i,j+m-5+c1)).getColor();
        									if(c.equals(Color.BLACK)) {
    											if(whiteCount == 0) {
    												blackCount++;
    											}
    											else {
    												break;
    											}
    										}
    										else if(c.equals(Color.WHITE)) {
    											if(blackCount == 0) {
    												whiteCount++;
    											}
    											else {
    												break;
    											}
    										}
    										else if(c.equals(Color.RED)) {
    											break;
    										}
        								} catch(Exception e1) {
        								}
	        						}
        							if(a == 1) {
        								Color c = null;
        								try {
        									c = MyData.pointsTree.get(setHashKey(i+m-5+b,j)).getColor();
        									if(b< 5) {
    	        								if(c.equals(Color.BLACK)) {
    												if(whiteCount != 0) {
    													whiteCount = 0;
    												}
    												blackCount++;
    	        								}
    											else if(c.equals(Color.WHITE)) {
    												if(blackCount != 0) {
    													blackCount = 0;
    												}
    												whiteCount++;
    											}
    	        							}
    	        							else {
    	        								if(c.equals(Color.BLACK)) {
    												if(whiteCount == 0) {
    													blackCount++;
    												}
    												else {
    													break;
    												}
    											}
    											else if(c.equals(Color.WHITE)) {
    												if(blackCount == 0) {
    													whiteCount++;
    												}
    												else {
    													break;
    												}
    											}
    											else if(c.equals(Color.RED)) {
    												break;
    											}
    	        							}
        								} catch(Exception e1) {
        									
        								}
	        						}
        							if(a == 2) {
        								Color c = null;
        								try {
        									c = MyData.pointsTree.get(setHashKey(i+m-5+b,j+m-5+b)).getColor();
        									if(b< 5) {
    	        								if(c.equals(Color.BLACK)) {
    												if(whiteCount != 0) {
    													whiteCount = 0;
    												}
    												blackCount++;
    	        								}
    											else if(c.equals(Color.WHITE)) {
    												if(blackCount != 0) {
    													blackCount = 0;
    												}
    												whiteCount++;
    											}
    	        							}
    	        							else {
    	        								if(c.equals(Color.BLACK)) {
    												if(whiteCount == 0) {
    													blackCount++;
    												}
    												else {
    													break;
    												}
    											}
    											else if(c.equals(Color.WHITE)) {
    												if(blackCount == 0) {
    													whiteCount++;
    												}
    												else {
    													break;
    												}
    											}
    											else if(c.equals(Color.RED)) {
    												break;
    											}
    	        							}
        								} catch(Exception e1) {
        									
        								}
	        						}
        							if(a == 3) {
        								Color c = null;
        								try {
        									c = MyData.pointsTree.get(setHashKey(i+m+5-b,j+m-5+b)).getColor();
        									if(b< 5) {
    	        								if(c.equals(Color.BLACK)) {
    												if(whiteCount != 0) {
    													whiteCount = 0;
    												}
    												blackCount++;
    	        								}
    											else if(c.equals(Color.WHITE)) {
    												if(blackCount != 0) {
    													blackCount = 0;
    												}
    												whiteCount++;
    											}
    	        							}
    	        							else {
    	        								if(c.equals(Color.BLACK)) {
    												if(whiteCount == 0) {
    													blackCount++;
    												}
    												else {
    													break;
    												}
    											}
    											else if(c.equals(Color.WHITE)) {
    												if(blackCount == 0) {
    													whiteCount++;
    												}
    												else {
    													break;
    												}
    											}
    											else if(c.equals(Color.RED)) {
    												break;
    											}
    	        							}
        								} catch(Exception e1) {
        									
        								}
	        						}
    							}
        						MyData.influnceMatrix[i][j+m] += blackCount - whiteCount;
							}
							
							
						}
					}
					else { // isItOnRight
						System.out.println("It's on right");
						for(int m = 1; m <= 5-distance[1]; m++) {
							if(MyData.influnceMatrix[i][j-m] == -100) continue;
							if(MyData.influnceMatrix[i][j-m] >= 0) {
								MyData.influnceMatrix[i][j-m] = -1;
							}
							for(int a = 0; a<4; a++) {
								int blackCount = 0;
								int whiteCount = 0;
        						for(int b = 0; b<11;  b++) {
        							if(b == 5) continue;
        							if(a == 0) {
        								if(b >=5-distance[1]) break;
        								Color c = null;
        								try {
        									c = MyData.pointsTree.get(setHashKey(i,j-m-5+b)).getColor();
        									if(c.equals(Color.BLACK)) {
    											if(whiteCount == 0) {
    												blackCount++;
    											}
    											else {
    												break;
    											}
    										}
    										else if(c.equals(Color.WHITE)) {
    											if(blackCount == 0) {
    												whiteCount++;
    											}
    											else {
    												break;
    											}
    										}
    										else if(c.equals(Color.RED)) {
    											break;
    										}
        								} catch(Exception e1) {
        								}
	        						}
        							if(a == 1) {
        								Color c = null;
        								try {
        									c = MyData.pointsTree.get(setHashKey(i-m-5+b,j)).getColor();
        									if(b< 5) {
    	        								if(c.equals(Color.BLACK)) {
    												if(whiteCount != 0) {
    													whiteCount = 0;
    												}
    												blackCount++;
    	        								}
    											else if(c.equals(Color.WHITE)) {
    												if(blackCount != 0) {
    													blackCount = 0;
    												}
    												whiteCount++;
    											}
    	        							}
    	        							else {
    	        								if(c.equals(Color.BLACK)) {
    												if(whiteCount == 0) {
    													blackCount++;
    												}
    												else {
    													break;
    												}
    											}
    											else if(c.equals(Color.WHITE)) {
    												if(blackCount == 0) {
    													whiteCount++;
    												}
    												else {
    													break;
    												}
    											}
    											else if(c.equals(Color.RED)) {
    												break;
    											}
    	        							}
        								} catch(Exception e1) {
        									
        								}
	        						}
        							if(a == 2) {
        								Color c = null;
        								try {
        									c = MyData.pointsTree.get(setHashKey(i-m-5+b,j-m-5+b)).getColor();
        									if(b< 5) {
    	        								if(c.equals(Color.BLACK)) {
    												if(whiteCount != 0) {
    													whiteCount = 0;
    												}
    												blackCount++;
    	        								}
    											else if(c.equals(Color.WHITE)) {
    												if(blackCount != 0) {
    													blackCount = 0;
    												}
    												whiteCount++;
    											}
    	        							}
    	        							else {
    	        								if(c.equals(Color.BLACK)) {
    												if(whiteCount == 0) {
    													blackCount++;
    												}
    												else {
    													break;
    												}
    											}
    											else if(c.equals(Color.WHITE)) {
    												if(blackCount == 0) {
    													whiteCount++;
    												}
    												else {
    													break;
    												}
    											}
    											else if(c.equals(Color.RED)) {
    												break;
    											}
    	        							}
        								} catch(Exception e1) {
        									
        								}
        								
	        							
	        						}
        							if(a == 3) {
        								Color c = null;
        								try {
        									c = MyData.pointsTree.get(setHashKey(i-m+5-b,j-m-5+b)).getColor();
        									if(b< 5) {
    	        								if(c.equals(Color.BLACK)) {
    												if(whiteCount != 0) {
    													whiteCount = 0;
    												}
    												blackCount++;
    	        								}
    											else if(c.equals(Color.WHITE)) {
    												if(blackCount != 0) {
    													blackCount = 0;
    												}
    												whiteCount++;
    											}
    	        							}
    	        							else {
    	        								if(c.equals(Color.BLACK)) {
    												if(whiteCount == 0) {
    													blackCount++;
    												}
    												else {
    													break;
    												}
    											}
    											else if(c.equals(Color.WHITE)) {
    												if(blackCount == 0) {
    													whiteCount++;
    												}
    												else {
    													break;
    												}
    											}
    											else if(c.equals(Color.RED)) {
    												break;
    											}
    	        							}
        								} catch(Exception e1) {
        									
        								}
	        						}
    							}
        						MyData.influnceMatrix[i][j-m] += blackCount - whiteCount;
							}
							
							
						}
					}
				}
				
				
			}
			
			if(n == 1) { 
				if((isItOnTop || isItOnBottom) && !(isItOnTop && isItOnBottom)) {
					if(isItOnTop) { //isItOnTop
						System.out.println("It's on Top");
						for(int m = 1; m <= 5-distance[2]; m++) {
							if(MyData.influnceMatrix[i+m][j] == -100) continue;
							if(MyData.influnceMatrix[i+m][j] >= 0) {
								MyData.influnceMatrix[i+m][j] = -1;
							}
							for(int a = 0; a<4; a++) {
								int blackCount = 0;
								int whiteCount = 0;
        						for(int b = 0; b<11;  b++) {
        							if(b == 5) continue;
        							if(a == 0) {
        								Color c = null;
        								try {
        									c = MyData.pointsTree.get(setHashKey(i, j+m-5+b)).getColor();
        									if(b< 5) {
    	        								if(c.equals(Color.BLACK)) {
    												if(whiteCount != 0) {
    													whiteCount = 0;
    												}
    												blackCount++;
    	        								}
    											else if(c.equals(Color.WHITE)) {
    												if(blackCount != 0) {
    													blackCount = 0;
    												}
    												whiteCount++;
    											}
    	        							}
    	        							else {
    	        								if(c.equals(Color.BLACK)) {
    												if(whiteCount == 0) {
    													blackCount++;
    												}
    												else {
    													break;
    												}
    											}
    											else if(c.equals(Color.WHITE)) {
    												if(blackCount == 0) {
    													whiteCount++;
    												}
    												else {
    													break;
    												}
    											}
    											else if(c.equals(Color.RED)) {
    												break;
    											}
    	        							}
        								} catch(Exception e1) {
        								}
	        						}
        							if(a == 1) {
        								int c1 = b+5-distance[2];
        								if(c1 >= 11) break;
        								Color c = null;
        								try {
        									c = MyData.pointsTree.get(setHashKey(i+m-5+c1,j)).getColor();
        									if(c.equals(Color.BLACK)) {
    											if(whiteCount == 0) {
    												blackCount++;
    											}
    											else {
    												break;
    											}
    										}
    										else if(c.equals(Color.WHITE)) {
    											if(blackCount == 0) {
    												whiteCount++;
    											}
    											else {
    												break;
    											}
    										}
    										else if(c.equals(Color.RED)) {
    											break;
    										}
        								} catch(Exception e1) {
        									
        								}
	        						}
        							if(a == 2) {
        								Color c = null;
        								try {
        									c = MyData.pointsTree.get(setHashKey(i+m-5+b,j+m-5+b)).getColor();
        									if(b< 5) {
    	        								if(c.equals(Color.BLACK)) {
    												if(whiteCount != 0) {
    													whiteCount = 0;
    												}
    												blackCount++;
    	        								}
    											else if(c.equals(Color.WHITE)) {
    												if(blackCount != 0) {
    													blackCount = 0;
    												}
    												whiteCount++;
    											}
    	        							}
    	        							else {
    	        								if(c.equals(Color.BLACK)) {
    												if(whiteCount == 0) {
    													blackCount++;
    												}
    												else {
    													break;
    												}
    											}
    											else if(c.equals(Color.WHITE)) {
    												if(blackCount == 0) {
    													whiteCount++;
    												}
    												else {
    													break;
    												}
    											}
    											else if(c.equals(Color.RED)) {
    												break;
    											}
    	        							}
        								} catch(Exception e1) {
        									
        								}
	        						}
        							if(a == 3) {
        								Color c = null;
        								try {
        									c = MyData.pointsTree.get(setHashKey(i+m+5-b,j+m-5+b)).getColor();
        									if(b< 5) {
    	        								if(c.equals(Color.BLACK)) {
    												if(whiteCount != 0) {
    													whiteCount = 0;
    												}
    												blackCount++;
    	        								}
    											else if(c.equals(Color.WHITE)) {
    												if(blackCount != 0) {
    													blackCount = 0;
    												}
    												whiteCount++;
    											}
    	        							}
    	        							else {
    	        								if(c.equals(Color.BLACK)) {
    												if(whiteCount == 0) {
    													blackCount++;
    												}
    												else {
    													break;
    												}
    											}
    											else if(c.equals(Color.WHITE)) {
    												if(blackCount == 0) {
    													whiteCount++;
    												}
    												else {
    													break;
    												}
    											}
    											else if(c.equals(Color.RED)) {
    												break;
    											}
    	        							}
        								} catch(Exception e1) {
        									
        								}
	        						}
    							}
        						MyData.influnceMatrix[i][j+m] += blackCount - whiteCount;
							}
							
							
						}
					}
					else { //isItOnBottom
						System.out.println("It's on Bottom");
						for(int m = 1; m <= 5-distance[3]; m++) {
							if(MyData.influnceMatrix[i-m][j] == -100) continue;
							if(MyData.influnceMatrix[i-m][j] >= 0) {
								MyData.influnceMatrix[i-m][j] = -1;
							}
							for(int a = 0; a<4; a++) {
								int blackCount = 0;
								int whiteCount = 0;
        						for(int b = 0; b<11;  b++) {
        							if(b == 5) continue;
        							if(a == 0) {
        								Color c = null;
        								try {
        									c = MyData.pointsTree.get(setHashKey(i,j-m-5+b)).getColor();
        									if(c.equals(Color.BLACK)) {
    											if(whiteCount == 0) {
    												blackCount++;
    											}
    											else {
    												break;
    											}
    										}
    										else if(c.equals(Color.WHITE)) {
    											if(blackCount == 0) {
    												whiteCount++;
    											}
    											else {
    												break;
    											}
    										}
    										else if(c.equals(Color.RED)) {
    											break;
    										}
        								} catch(Exception e1) {
        								}
	        						}
        							if(a == 1) {
        								if(b >=5-distance[3]) break;
        								Color c = null;
        								try {
        									c = MyData.pointsTree.get(setHashKey(i-m-5+b,j)).getColor();
        									if(b< 5) {
    	        								if(c.equals(Color.BLACK)) {
    												if(whiteCount != 0) {
    													whiteCount = 0;
    												}
    												blackCount++;
    	        								}
    											else if(c.equals(Color.WHITE)) {
    												if(blackCount != 0) {
    													blackCount = 0;
    												}
    												whiteCount++;
    											}
    	        							}
    	        							else {
    	        								if(c.equals(Color.BLACK)) {
    												if(whiteCount == 0) {
    													blackCount++;
    												}
    												else {
    													break;
    												}
    											}
    											else if(c.equals(Color.WHITE)) {
    												if(blackCount == 0) {
    													whiteCount++;
    												}
    												else {
    													break;
    												}
    											}
    											else if(c.equals(Color.RED)) {
    												break;
    											}
    	        							}
        								} catch(Exception e1) {
        									
        								}
	        						}
        							if(a == 2) {
        								Color c = null;
        								try {
        									c = MyData.pointsTree.get(setHashKey(i-m-5+b,j-m-5+b)).getColor();
        									if(b< 5) {
    	        								if(c.equals(Color.BLACK)) {
    												if(whiteCount != 0) {
    													whiteCount = 0;
    												}
    												blackCount++;
    	        								}
    											else if(c.equals(Color.WHITE)) {
    												if(blackCount != 0) {
    													blackCount = 0;
    												}
    												whiteCount++;
    											}
    	        							}
    	        							else {
    	        								if(c.equals(Color.BLACK)) {
    												if(whiteCount == 0) {
    													blackCount++;
    												}
    												else {
    													break;
    												}
    											}
    											else if(c.equals(Color.WHITE)) {
    												if(blackCount == 0) {
    													whiteCount++;
    												}
    												else {
    													break;
    												}
    											}
    											else if(c.equals(Color.RED)) {
    												break;
    											}
    	        							}
        								} catch(Exception e1) {
        								}
	        						}
        							if(a == 3) {
        								Color c = null;
        								try {
        									c = MyData.pointsTree.get(setHashKey(i-m+5-b,j-m-5+b)).getColor();
        									if(b< 5) {
    	        								if(c.equals(Color.BLACK)) {
    												if(whiteCount != 0) {
    													whiteCount = 0;
    												}
    												blackCount++;
    	        								}
    											else if(c.equals(Color.WHITE)) {
    												if(blackCount != 0) {
    													blackCount = 0;
    												}
    												whiteCount++;
    											}
    	        							}
    	        							else {
    	        								if(c.equals(Color.BLACK)) {
    												if(whiteCount == 0) {
    													blackCount++;
    												}
    												else {
    													break;
    												}
    											}
    											else if(c.equals(Color.WHITE)) {
    												if(blackCount == 0) {
    													whiteCount++;
    												}
    												else {
    													break;
    												}
    											}
    											else if(c.equals(Color.RED)) {
    												break;
    											}
    	        							}
        								} catch(Exception e1) {
        									
        								}
	        						}
    							}
        						MyData.influnceMatrix[i-m][j] += blackCount - whiteCount;
							}
						}
					}
				}
				
				if(n == 2) { 
					if((isItOnLeftTop || isItOnRightBottom) && !(isItOnLeftTop && isItOnRightBottom)) {
						if(isItOnLeftTop) { //isItOnLeftTop
							System.out.println("It's on Left-Top");
							for(int m = 1; m <= 5-distance[4]; m++) {
								if(MyData.influnceMatrix[i+m][j+m] == -100) continue;
								if(MyData.influnceMatrix[i+m][j+m] >= 0) {
									MyData.influnceMatrix[i+m][j+m] = -1;
								}
								for(int a = 0; a<4; a++) {
									int blackCount = 0;
									int whiteCount = 0;
	        						for(int b = 0; b<11;  b++) {
	        							if(b == 5) continue;
	        							if(a == 0) {
	        								Color c = null;
	        								try {
	        									c = MyData.pointsTree.get(setHashKey(i, j+m-5+b)).getColor();
	        									if(b< 5) {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount != 0) {
	    													whiteCount = 0;
	    												}
	    												blackCount++;
	    	        								}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount != 0) {
	    													blackCount = 0;
	    												}
	    												whiteCount++;
	    											}
	    	        							}
	    	        							else {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount == 0) {
	    													blackCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount == 0) {
	    													whiteCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.RED)) {
	    												break;
	    											}
	    	        							}
	        								} catch(Exception e1) {
	        								}
		        						}
	        							if(a == 1) {
	        								Color c = null;
	        								try {
	        									c = MyData.pointsTree.get(setHashKey(i+m-5+b,j)).getColor();
	        									if(b< 5) {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount != 0) {
	    													whiteCount = 0;
	    												}
	    												blackCount++;
	    	        								}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount != 0) {
	    													blackCount = 0;
	    												}
	    												whiteCount++;
	    											}
	    	        							}
	    	        							else {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount == 0) {
	    													blackCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount == 0) {
	    													whiteCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.RED)) {
	    												break;
	    											}
	    	        							}
	        								} catch(Exception e1) {
	        									
	        								}
		        						}
	        							if(a == 2) {
	        								int c1 = b+5-distance[4];
	        								if(c1 >= 11) break;
	        								Color c = null;
	        								try {
	        									c = MyData.pointsTree.get(setHashKey(i+m-5+b,j+m-5+b)).getColor();
	        									if(b< 5) {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount != 0) {
	    													whiteCount = 0;
	    												}
	    												blackCount++;
	    	        								}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount != 0) {
	    													blackCount = 0;
	    												}
	    												whiteCount++;
	    											}
	    	        							}
	    	        							else {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount == 0) {
	    													blackCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount == 0) {
	    													whiteCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.RED)) {
	    												break;
	    											}
	    	        							}
	        								} catch(Exception e1) {
	        								}
		        						}
	        							if(a == 3) {
	        								Color c = null;
	        								try {
	        									c = MyData.pointsTree.get(setHashKey(i+m+5-b,j+m-5+b)).getColor();
	        									if(b< 5) {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount != 0) {
	    													whiteCount = 0;
	    												}
	    												blackCount++;
	    	        								}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount != 0) {
	    													blackCount = 0;
	    												}
	    												whiteCount++;
	    											}
	    	        							}
	    	        							else {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount == 0) {
	    													blackCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount == 0) {
	    													whiteCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.RED)) {
	    												break;
	    											}
	    	        							}
	        								} catch(Exception e1) {
	        									
	        								}
		        						}
	    							}
	        						MyData.influnceMatrix[i+m][j+m] += blackCount - whiteCount;
								}
								
								
							}
						}
						else { //isItOnRightBottom
							System.out.println("It's on RightBottom");
							for(int m = 1; m <= 5-distance[5]; m++) {
								if(MyData.influnceMatrix[i-m][j-m] == -100) continue;
								if(MyData.influnceMatrix[i-m][j-m] >= 0) {
									MyData.influnceMatrix[i-m][j-m] = -1;
								}
								for(int a = 0; a<4; a++) {
									int blackCount = 0;
									int whiteCount = 0;
	        						for(int b = 0; b<11;  b++) {
	        							if(b == 5) continue;
	        							if(a == 0) {
	        								Color c = null;
	        								try {
	        									if(b< 5) {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount != 0) {
	    													whiteCount = 0;
	    												}
	    												blackCount++;
	    	        								}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount != 0) {
	    													blackCount = 0;
	    												}
	    												whiteCount++;
	    											}
	    	        							}
	    	        							else {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount == 0) {
	    													blackCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount == 0) {
	    													whiteCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.RED)) {
	    												break;
	    											}
	    	        							}
	        								} catch(Exception e1) {
	        								}
		        						}
	        							if(a == 1) {
	        								
	        								Color c = null;
	        								try {
	        									c = MyData.pointsTree.get(setHashKey(i-m-5+b,j)).getColor();
	        									if(b< 5) {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount != 0) {
	    													whiteCount = 0;
	    												}
	    												blackCount++;
	    	        								}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount != 0) {
	    													blackCount = 0;
	    												}
	    												whiteCount++;
	    											}
	    	        							}
	    	        							else {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount == 0) {
	    													blackCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount == 0) {
	    													whiteCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.RED)) {
	    												break;
	    											}
	    	        							}
	        								} catch(Exception e1) {
	        									
	        								}
		        						}
	        							if(a == 2) {
	        								if(b >=5-distance[5]) break;
	        								Color c = null;
	        								try {
	        									c = MyData.pointsTree.get(setHashKey(i-m-5+b,j-m-5+b)).getColor();
	        									if(b< 5) {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount != 0) {
	    													whiteCount = 0;
	    												}
	    												blackCount++;
	    	        								}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount != 0) {
	    													blackCount = 0;
	    												}
	    												whiteCount++;
	    											}
	    	        							}
	    	        							else {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount == 0) {
	    													blackCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount == 0) {
	    													whiteCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.RED)) {
	    												break;
	    											}
	    	        							}
	        								} catch(Exception e1) {
	        									
	        								}
	        								
		        							
		        						}
	        							if(a == 3) {
	        								Color c = null;
	        								try {
	        									c = MyData.pointsTree.get(setHashKey(i-m+5-b,j-m-5+b)).getColor();
	        									if(b< 5) {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount != 0) {
	    													whiteCount = 0;
	    												}
	    												blackCount++;
	    	        								}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount != 0) {
	    													blackCount = 0;
	    												}
	    												whiteCount++;
	    											}
	    	        							}
	    	        							else {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount == 0) {
	    													blackCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount == 0) {
	    													whiteCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.RED)) {
	    												break;
	    											}
	    	        							}
	        								} catch(Exception e1) {
	        									
	        								}
		        						}
	    							}
	        						MyData.influnceMatrix[i-m][j-m] += blackCount - whiteCount;
								}
							}
						}
					}
				}
				if(n == 3) { 
					if((isItOnLeftBottom || isItOnRightTop) && !(isItOnLeftBottom && isItOnRightTop)) {
						if(isItOnLeftBottom) { //isItOnLeftBottom
							System.out.println("It's on Left-Bottom");
							for(int m = 1; m <= 5-distance[6]; m++) {
								if(MyData.influnceMatrix[i-m][j+m] == -100) continue;
								if(MyData.influnceMatrix[i-m][j+m] >= 0) {
									MyData.influnceMatrix[i-m][j+m] = -1;
								}
								for(int a = 0; a<4; a++) {
									int blackCount = 0;
									int whiteCount = 0;
	        						for(int b = 0; b<11;  b++) {
	        							if(b == 5) continue;
	        							if(a == 0) {
	        								Color c = null;
	        								try {
	        									c = MyData.pointsTree.get(setHashKey(i, j+m-5+b)).getColor();
	        									if(b< 5) {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount != 0) {
	    													whiteCount = 0;
	    												}
	    												blackCount++;
	    	        								}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount != 0) {
	    													blackCount = 0;
	    												}
	    												whiteCount++;
	    											}
	    	        							}
	    	        							else {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount == 0) {
	    													blackCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount == 0) {
	    													whiteCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.RED)) {
	    												break;
	    											}
	    	        							}
	        								} catch(Exception e1) {
	        								}
		        						}
	        							if(a == 1) {
	        								Color c = null;
	        								try {
	        									c = MyData.pointsTree.get(setHashKey(i+m-5+b,j)).getColor();
	        									if(b< 5) {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount != 0) {
	    													whiteCount = 0;
	    												}
	    												blackCount++;
	    	        								}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount != 0) {
	    													blackCount = 0;
	    												}
	    												whiteCount++;
	    											}
	    	        							}
	    	        							else {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount == 0) {
	    													blackCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount == 0) {
	    													whiteCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.RED)) {
	    												break;
	    											}
	    	        							}
	        								} catch(Exception e1) {
	        									
	        								}
		        						}
	        							if(a == 2) {
	        								
	        								Color c = null;
	        								try {
	        									c = MyData.pointsTree.get(setHashKey(i+m-5+b,j+m-5+b)).getColor();
	        									if(b< 5) {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount != 0) {
	    													whiteCount = 0;
	    												}
	    												blackCount++;
	    	        								}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount != 0) {
	    													blackCount = 0;
	    												}
	    												whiteCount++;
	    											}
	    	        							}
	    	        							else {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount == 0) {
	    													blackCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount == 0) {
	    													whiteCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.RED)) {
	    												break;
	    											}
	    	        							}
	        								} catch(Exception e1) {
	        								}
		        						}
	        							if(a == 3) {
	        								Color c = null;
	        								try {
	        									c = MyData.pointsTree.get(setHashKey(i+m+5-b,j+m-5+b)).getColor();
	        									int c1 = b+5-distance[4];
		        								if(c1 >= 11) break;
	        									if(b< 5) {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount != 0) {
	    													whiteCount = 0;
	    												}
	    												blackCount++;
	    	        								}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount != 0) {
	    													blackCount = 0;
	    												}
	    												whiteCount++;
	    											}
	    	        							}
	    	        							else {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount == 0) {
	    													blackCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount == 0) {
	    													whiteCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.RED)) {
	    												break;
	    											}
	    	        							}
	        								} catch(Exception e1) {
	        									
	        								}
		        						}
	    							}
	        						MyData.influnceMatrix[i+m][j+m] += blackCount - whiteCount;
								}
								
								
							}
						}
						else { //isItOnRightBottom
							System.out.println("It's on RightBottom");
							for(int m = 1; m <= 5-distance[1]; m++) {
								if(MyData.influnceMatrix[i-m][j-m] == -100) continue;
								if(MyData.influnceMatrix[i-m][j-m] >= 0) {
									MyData.influnceMatrix[i-m][j-m] = -1;
								}
								for(int a = 0; a<4; a++) {
									int blackCount = 0;
									int whiteCount = 0;
	        						for(int b = 0; b<11;  b++) {
	        							if(b == 5) continue;
	        							if(a == 0) {
	        								Color c = null;
	        								try {
	        									if(b< 5) {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount != 0) {
	    													whiteCount = 0;
	    												}
	    												blackCount++;
	    	        								}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount != 0) {
	    													blackCount = 0;
	    												}
	    												whiteCount++;
	    											}
	    	        							}
	    	        							else {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount == 0) {
	    													blackCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount == 0) {
	    													whiteCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.RED)) {
	    												break;
	    											}
	    	        							}
	        								} catch(Exception e1) {
	        								}
		        						}
	        							if(a == 1) {
	        								
	        								Color c = null;
	        								try {
	        									c = MyData.pointsTree.get(setHashKey(i-m-5+b,j)).getColor();
	        									if(b< 5) {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount != 0) {
	    													whiteCount = 0;
	    												}
	    												blackCount++;
	    	        								}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount != 0) {
	    													blackCount = 0;
	    												}
	    												whiteCount++;
	    											}
	    	        							}
	    	        							else {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount == 0) {
	    													blackCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount == 0) {
	    													whiteCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.RED)) {
	    												break;
	    											}
	    	        							}
	        								} catch(Exception e1) {
	        									
	        								}
		        						}
	        							if(a == 2) {
	        								if(b >=5-distance[5]) break;
	        								Color c = null;
	        								try {
	        									c = MyData.pointsTree.get(setHashKey(i-m-5+b,j-m-5+b)).getColor();
	        									if(b< 5) {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount != 0) {
	    													whiteCount = 0;
	    												}
	    												blackCount++;
	    	        								}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount != 0) {
	    													blackCount = 0;
	    												}
	    												whiteCount++;
	    											}
	    	        							}
	    	        							else {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount == 0) {
	    													blackCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount == 0) {
	    													whiteCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.RED)) {
	    												break;
	    											}
	    	        							}
	        								} catch(Exception e1) {
	        									
	        								}
	        								
		        							
		        						}
	        							if(a == 3) {
	        								Color c = null;
	        								try {
	        									c = MyData.pointsTree.get(setHashKey(i-m+5-b,j-m-5+b)).getColor();
	        									if(b< 5) {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount != 0) {
	    													whiteCount = 0;
	    												}
	    												blackCount++;
	    	        								}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount != 0) {
	    													blackCount = 0;
	    												}
	    												whiteCount++;
	    											}
	    	        							}
	    	        							else {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount == 0) {
	    													blackCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount == 0) {
	    													whiteCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.RED)) {
	    												break;
	    											}
	    	        							}
	        								} catch(Exception e1) {
	        									
	        								}
		        						}
	    							}
	        						MyData.influnceMatrix[i-m][j-m] += blackCount - whiteCount;
								}
							}
						}
					}
				}
				try {
					for(int p=0; p<19; p++) { //print MyData.influnceMatrix[i][j] Output
			        	for(int q=0; q<19; q++) {
			        		if(95 <= MyData.influnceMatrix[q][p] && MyData.influnceMatrix[q][p] <= 105  ) {
			        			MyData.influnceMatrix[q][p] = 100;
			        		}
			        		else if(-95 >= MyData.influnceMatrix[q][p] && MyData.influnceMatrix[q][p] >= -105  ) {
			        			MyData.influnceMatrix[q][p] = -100;
			        		}
			        		else if(995 <= MyData.influnceMatrix[q][p] && MyData.influnceMatrix[q][p] <= 1005  ) {
			        			MyData.influnceMatrix[q][p] = 1000;
			        		}
			        		System.out.print(MyData.influnceMatrix[q][p] + "|\t");
//			        		System.out.print(MyData.pointsTree.get(180).getInfo()[i][j] + " | ");
			        	}
			        	System.out.println();
					}
				} catch(Exception e1) {
					
				}
				
			}
			
		}
	}
	
	public void checkMatrixBlack(int i, int j) { 	// White
		System.out.println("I am i n check matrix");
		boolean isItOnLeft = false;
		boolean isItOnRight = false;
		boolean isItOnTop = false;
		boolean isItOnBottom = false;
		boolean isItOnLeftTop = false;
		boolean isItOnRightBottom = false;
		boolean isItOnRightTop = false;
		boolean isItOnLeftBottom = false;
		int[] distance = new int[8] ;
		try {
			for(int n = 0; n<8; n++) {
				for(int a = 1; a<=5;  a++) {
					if(n==0) {
						if(MyData.influnceMatrix[i][j-a]==-100) {
							distance[n] = a;
							isItOnLeft = true;
							break;
						}
						else if(MyData.influnceMatrix[i][j-a]==1000) {
							break;
						}
						else if(MyData.influnceMatrix[i][j-a]==100) {
							continue;
						}
						else {
							MyData.influnceMatrix[i][j-a] += 1;
						}
					}
					else if(n==1) {
						if(MyData.influnceMatrix[i][j+a]==-100) {
							distance[n] = a;
							isItOnRight = true;
							break;
						}
						else if(MyData.influnceMatrix[i][j+a]==1000) {
							break;
						}
						else {
							MyData.influnceMatrix[i][j+a]+=(1);
						}
					}
					else if(n==2) {
						if(MyData.influnceMatrix[i-a][j]==-100) {
							distance[n] = a;
							isItOnTop = true;
							break;
						}
						else if(MyData.influnceMatrix[i-a][j]==1000) {
							break;
						}
						else if(MyData.influnceMatrix[i-a][j]==100) {
							continue;
						}
						else {
							MyData.influnceMatrix[i-a][j] += 1;
						}
					}
					else if(n==3) {
						if(MyData.influnceMatrix[i+a][j]==-100) {
							distance[n] = a;
							isItOnBottom = true;
							break;
						}
						else if(MyData.influnceMatrix[i+a][j]==1000) {
							break;
						}
						else if(MyData.influnceMatrix[i+a][j]==100) {
							continue;
						}
						else {
							MyData.influnceMatrix[i+a][j]+=(1);
						}
					}
					else if(n==4) {
						if(MyData.influnceMatrix[i-a][j-a]==-100) {
							distance[n] = a;
							isItOnLeftTop = true;
							break;
						}
						else if(MyData.influnceMatrix[i-a][j-a]==1000) {
							break;
						}
						else if(MyData.influnceMatrix[i-a][j-a]==100) {
							continue;
						}
						else {
							MyData.influnceMatrix[i-a][j-a] += 1;
						}
					}
					else if(n==5) {
						if(MyData.influnceMatrix[i+a][j+a]== -100) {
							distance[n] = a;
							isItOnRightBottom = true;
							break;
						}
						else if(MyData.influnceMatrix[i+a][j+a]==1000) {
							break;
						}
						else if(MyData.influnceMatrix[i+a][j+a]==100) {
							continue;
						}
						else {
							MyData.influnceMatrix[i+a][j+a]+=(1);
						}
					}
					else if(n==6) {
						if(MyData.influnceMatrix[i+a][j-a]==-100) {
							distance[n] = a;
							isItOnRightTop = true;
							break;
						}
						else if(MyData.influnceMatrix[i+a][j-a]==1000) {
							break;
						}
						else if(MyData.influnceMatrix[i][j-a]==100) {
							continue;
						}
						else {
							MyData.influnceMatrix[i+a][j-a] += 1;
						}
					}
					else if(n==7) {
						if(MyData.influnceMatrix[i-a][j+a]==-100) {
							distance[n] = a;
							isItOnLeftBottom = true;
							break;
						}
						else if(MyData.influnceMatrix[i-a][j+a]==1000) {
							break;
						}
						else if(MyData.influnceMatrix[i][j-a]==100) {
							continue;
						}
						else {
							MyData.influnceMatrix[i-a][j+a]+=(1);
						}
					}
				}
			}
		} catch (Exception e) {
			
		}
		
		//END POINT: Matrix scan
		
		for(int n = 0; n < 4; n++) {
			if(n == 0) {
				if((isItOnLeft || isItOnRight) && !(isItOnLeft && isItOnRight)) {
					if(isItOnLeft) {
						for(int m = 1; m <= 5-distance[0]; m++) {
							if(MyData.influnceMatrix[i][j+m] == 100) continue;
							if(MyData.influnceMatrix[i][j+m] <= 0) {
								MyData.influnceMatrix[i][j+m] = 1;
							}
							for(int a = 0; a<4; a++) {
								int blackCount = 0;
								int whiteCount = 0;
        						for(int b = 0; b<11;  b++) {
        							if(b == 5) continue;
        							if(a == 0) {
        								int c1 = b+5-distance[0];
        								if(c1 >= 11) break;
        								Color c = null;
        								try {
        									c = MyData.pointsTree.get(setHashKey(i,j+m-5+c1)).getColor();
        									if(c.equals(Color.BLACK)) {
    											if(whiteCount == 0) {
    												blackCount++;
    											}
    											else {
    												break;
    											}
    										}
    										else if(c.equals(Color.WHITE)) {
    											if(blackCount == 0) {
    												whiteCount++;
    											}
    											else {
    												break;
    											}
    										}
    										else if(c.equals(Color.RED)) {
    											break;
    										}
        								} catch(Exception e1) {
        								}
	        						}
        							if(a == 1) {
        								Color c = null;
        								try {
        									c = MyData.pointsTree.get(setHashKey(i+m-5+b,j)).getColor();
        									if(b< 5) {
    	        								if(c.equals(Color.BLACK)) {
    												if(whiteCount != 0) {
    													whiteCount = 0;
    												}
    												blackCount++;
    	        								}
    											else if(c.equals(Color.WHITE)) {
    												if(blackCount != 0) {
    													blackCount = 0;
    												}
    												whiteCount++;
    											}
    	        							}
    	        							else {
    	        								if(c.equals(Color.BLACK)) {
    												if(whiteCount == 0) {
    													blackCount++;
    												}
    												else {
    													break;
    												}
    											}
    											else if(c.equals(Color.WHITE)) {
    												if(blackCount == 0) {
    													whiteCount++;
    												}
    												else {
    													break;
    												}
    											}
    											else if(c.equals(Color.RED)) {
    												break;
    											}
    	        							}
        								} catch(Exception e1) {
        									
        								}
	        						}
        							if(a == 2) {
        								Color c = null;
        								try {
        									c = MyData.pointsTree.get(setHashKey(i+m-5+b,j+m-5+b)).getColor();
        									if(b< 5) {
    	        								if(c.equals(Color.BLACK)) {
    												if(whiteCount != 0) {
    													whiteCount = 0;
    												}
    												blackCount++;
    	        								}
    											else if(c.equals(Color.WHITE)) {
    												if(blackCount != 0) {
    													blackCount = 0;
    												}
    												whiteCount++;
    											}
    	        							}
    	        							else {
    	        								if(c.equals(Color.BLACK)) {
    												if(whiteCount == 0) {
    													blackCount++;
    												}
    												else {
    													break;
    												}
    											}
    											else if(c.equals(Color.WHITE)) {
    												if(blackCount == 0) {
    													whiteCount++;
    												}
    												else {
    													break;
    												}
    											}
    											else if(c.equals(Color.RED)) {
    												break;
    											}
    	        							}
        								} catch(Exception e1) {
        									
        								}
	        						}
        							if(a == 3) {
        								Color c = null;
        								try {
        									c = MyData.pointsTree.get(setHashKey(i+m+5-b,j+m-5+b)).getColor();
        									if(b< 5) {
    	        								if(c.equals(Color.BLACK)) {
    												if(whiteCount != 0) {
    													whiteCount = 0;
    												}
    												blackCount++;
    	        								}
    											else if(c.equals(Color.WHITE)) {
    												if(blackCount != 0) {
    													blackCount = 0;
    												}
    												whiteCount++;
    											}
    	        							}
    	        							else {
    	        								if(c.equals(Color.BLACK)) {
    												if(whiteCount == 0) {
    													blackCount++;
    												}
    												else {
    													break;
    												}
    											}
    											else if(c.equals(Color.WHITE)) {
    												if(blackCount == 0) {
    													whiteCount++;
    												}
    												else {
    													break;
    												}
    											}
    											else if(c.equals(Color.RED)) {
    												break;
    											}
    	        							}
        								} catch(Exception e1) {
        									
        								}
	        						}
    							}
        						MyData.influnceMatrix[i][j+m] += blackCount - whiteCount;
							}
							
							
						}
					}
					else { // isItOnRight
						System.out.println("It's on right");
						for(int m = 1; m <= 5-distance[1]; m++) {
							if(MyData.influnceMatrix[i][j-m] == -100) continue;
							if(MyData.influnceMatrix[i][j-m] >= 0) {
								MyData.influnceMatrix[i][j-m] = -1;
							}
							for(int a = 0; a<4; a++) {
								int blackCount = 0;
								int whiteCount = 0;
        						for(int b = 0; b<11;  b++) {
        							if(b == 5) continue;
        							if(a == 0) {
        								if(b >=5-distance[1]) break;
        								Color c = null;
        								try {
        									c = MyData.pointsTree.get(setHashKey(i,j-m-5+b)).getColor();
        									if(c.equals(Color.BLACK)) {
    											if(whiteCount == 0) {
    												blackCount++;
    											}
    											else {
    												break;
    											}
    										}
    										else if(c.equals(Color.WHITE)) {
    											if(blackCount == 0) {
    												whiteCount++;
    											}
    											else {
    												break;
    											}
    										}
    										else if(c.equals(Color.RED)) {
    											break;
    										}
        								} catch(Exception e1) {
        								}
	        						}
        							if(a == 1) {
        								Color c = null;
        								try {
        									c = MyData.pointsTree.get(setHashKey(i-m-5+b,j)).getColor();
        									if(b< 5) {
    	        								if(c.equals(Color.BLACK)) {
    												if(whiteCount != 0) {
    													whiteCount = 0;
    												}
    												blackCount++;
    	        								}
    											else if(c.equals(Color.WHITE)) {
    												if(blackCount != 0) {
    													blackCount = 0;
    												}
    												whiteCount++;
    											}
    	        							}
    	        							else {
    	        								if(c.equals(Color.BLACK)) {
    												if(whiteCount == 0) {
    													blackCount++;
    												}
    												else {
    													break;
    												}
    											}
    											else if(c.equals(Color.WHITE)) {
    												if(blackCount == 0) {
    													whiteCount++;
    												}
    												else {
    													break;
    												}
    											}
    											else if(c.equals(Color.RED)) {
    												break;
    											}
    	        							}
        								} catch(Exception e1) {
        									
        								}
	        						}
        							if(a == 2) {
        								Color c = null;
        								try {
        									c = MyData.pointsTree.get(setHashKey(i-m-5+b,j-m-5+b)).getColor();
        									if(b< 5) {
    	        								if(c.equals(Color.BLACK)) {
    												if(whiteCount != 0) {
    													whiteCount = 0;
    												}
    												blackCount++;
    	        								}
    											else if(c.equals(Color.WHITE)) {
    												if(blackCount != 0) {
    													blackCount = 0;
    												}
    												whiteCount++;
    											}
    	        							}
    	        							else {
    	        								if(c.equals(Color.BLACK)) {
    												if(whiteCount == 0) {
    													blackCount++;
    												}
    												else {
    													break;
    												}
    											}
    											else if(c.equals(Color.WHITE)) {
    												if(blackCount == 0) {
    													whiteCount++;
    												}
    												else {
    													break;
    												}
    											}
    											else if(c.equals(Color.RED)) {
    												break;
    											}
    	        							}
        								} catch(Exception e1) {
        									
        								}
        								
	        							
	        						}
        							if(a == 3) {
        								Color c = null;
        								try {
        									c = MyData.pointsTree.get(setHashKey(i-m+5-b,j-m-5+b)).getColor();
        									if(b< 5) {
    	        								if(c.equals(Color.BLACK)) {
    												if(whiteCount != 0) {
    													whiteCount = 0;
    												}
    												blackCount++;
    	        								}
    											else if(c.equals(Color.WHITE)) {
    												if(blackCount != 0) {
    													blackCount = 0;
    												}
    												whiteCount++;
    											}
    	        							}
    	        							else {
    	        								if(c.equals(Color.BLACK)) {
    												if(whiteCount == 0) {
    													blackCount++;
    												}
    												else {
    													break;
    												}
    											}
    											else if(c.equals(Color.WHITE)) {
    												if(blackCount == 0) {
    													whiteCount++;
    												}
    												else {
    													break;
    												}
    											}
    											else if(c.equals(Color.RED)) {
    												break;
    											}
    	        							}
        								} catch(Exception e1) {
        									
        								}
	        						}
    							}
        						MyData.influnceMatrix[i][j-m] += blackCount - whiteCount;
							}
							
							
						}
					}
				}
				
				
			}
			
			if(n == 1) { 
				if((isItOnTop || isItOnBottom) && !(isItOnTop && isItOnBottom)) {
					if(isItOnTop) { //isItOnTop
						System.out.println("It's on Top");
						for(int m = 1; m <= 5-distance[2]; m++) {
							if(MyData.influnceMatrix[i+m][j] == 100) continue;
							if(MyData.influnceMatrix[i+m][j] <= 0) {
								MyData.influnceMatrix[i+m][j] = 1;
							}
							for(int a = 0; a<4; a++) {
								int blackCount = 0;
								int whiteCount = 0;
        						for(int b = 0; b<11;  b++) {
        							if(b == 5) continue;
        							if(a == 0) {
        								Color c = null;
        								try {
        									c = MyData.pointsTree.get(setHashKey(i, j+m-5+b)).getColor();
        									if(b< 5) {
    	        								if(c.equals(Color.BLACK)) {
    												if(whiteCount != 0) {
    													whiteCount = 0;
    												}
    												blackCount++;
    	        								}
    											else if(c.equals(Color.WHITE)) {
    												if(blackCount != 0) {
    													blackCount = 0;
    												}
    												whiteCount++;
    											}
    	        							}
    	        							else {
    	        								if(c.equals(Color.BLACK)) {
    												if(whiteCount == 0) {
    													blackCount++;
    												}
    												else {
    													break;
    												}
    											}
    											else if(c.equals(Color.WHITE)) {
    												if(blackCount == 0) {
    													whiteCount++;
    												}
    												else {
    													break;
    												}
    											}
    											else if(c.equals(Color.RED)) {
    												break;
    											}
    	        							}
        								} catch(Exception e1) {
        								}
	        						}
        							if(a == 1) {
        								int c1 = b+5-distance[2];
        								if(c1 >= 11) break;
        								Color c = null;
        								try {
        									c = MyData.pointsTree.get(setHashKey(i+m-5+c1,j)).getColor();
        									if(c.equals(Color.BLACK)) {
    											if(whiteCount == 0) {
    												blackCount++;
    											}
    											else {
    												break;
    											}
    										}
    										else if(c.equals(Color.WHITE)) {
    											if(blackCount == 0) {
    												whiteCount++;
    											}
    											else {
    												break;
    											}
    										}
    										else if(c.equals(Color.RED)) {
    											break;
    										}
        								} catch(Exception e1) {
        									
        								}
	        						}
        							if(a == 2) {
        								Color c = null;
        								try {
        									c = MyData.pointsTree.get(setHashKey(i+m-5+b,j+m-5+b)).getColor();
        									if(b< 5) {
    	        								if(c.equals(Color.BLACK)) {
    												if(whiteCount != 0) {
    													whiteCount = 0;
    												}
    												blackCount++;
    	        								}
    											else if(c.equals(Color.WHITE)) {
    												if(blackCount != 0) {
    													blackCount = 0;
    												}
    												whiteCount++;
    											}
    	        							}
    	        							else {
    	        								if(c.equals(Color.BLACK)) {
    												if(whiteCount == 0) {
    													blackCount++;
    												}
    												else {
    													break;
    												}
    											}
    											else if(c.equals(Color.WHITE)) {
    												if(blackCount == 0) {
    													whiteCount++;
    												}
    												else {
    													break;
    												}
    											}
    											else if(c.equals(Color.RED)) {
    												break;
    											}
    	        							}
        								} catch(Exception e1) {
        									
        								}
	        						}
        							if(a == 3) {
        								Color c = null;
        								try {
        									c = MyData.pointsTree.get(setHashKey(i+m+5-b,j+m-5+b)).getColor();
        									if(b< 5) {
    	        								if(c.equals(Color.BLACK)) {
    												if(whiteCount != 0) {
    													whiteCount = 0;
    												}
    												blackCount++;
    	        								}
    											else if(c.equals(Color.WHITE)) {
    												if(blackCount != 0) {
    													blackCount = 0;
    												}
    												whiteCount++;
    											}
    	        							}
    	        							else {
    	        								if(c.equals(Color.BLACK)) {
    												if(whiteCount == 0) {
    													blackCount++;
    												}
    												else {
    													break;
    												}
    											}
    											else if(c.equals(Color.WHITE)) {
    												if(blackCount == 0) {
    													whiteCount++;
    												}
    												else {
    													break;
    												}
    											}
    											else if(c.equals(Color.RED)) {
    												break;
    											}
    	        							}
        								} catch(Exception e1) {
        									
        								}
	        						}
    							}
        						MyData.influnceMatrix[i][j+m] += blackCount - whiteCount;
							}
							
							
						}
					}
					else { //isItOnBottom
						System.out.println("It's on Bottom");
						for(int m = 1; m <= 5-distance[3]; m++) {
							if(MyData.influnceMatrix[i-m][j] == 100) continue;
							if(MyData.influnceMatrix[i-m][j] <= 0) {
								MyData.influnceMatrix[i-m][j] = 1;
							}
							for(int a = 0; a<4; a++) {
								int blackCount = 0;
								int whiteCount = 0;
        						for(int b = 0; b<11;  b++) {
        							if(b == 5) continue;
        							if(a == 0) {
        								Color c = null;
        								try {
        									c = MyData.pointsTree.get(setHashKey(i,j-m-5+b)).getColor();
        									if(c.equals(Color.BLACK)) {
    											if(whiteCount == 0) {
    												blackCount++;
    											}
    											else {
    												break;
    											}
    										}
    										else if(c.equals(Color.WHITE)) {
    											if(blackCount == 0) {
    												whiteCount++;
    											}
    											else {
    												break;
    											}
    										}
    										else if(c.equals(Color.RED)) {
    											break;
    										}
        								} catch(Exception e1) {
        								}
	        						}
        							if(a == 1) {
        								if(b >=5-distance[3]) break;
        								Color c = null;
        								try {
        									c = MyData.pointsTree.get(setHashKey(i-m-5+b,j)).getColor();
        									if(b< 5) {
    	        								if(c.equals(Color.BLACK)) {
    												if(whiteCount != 0) {
    													whiteCount = 0;
    												}
    												blackCount++;
    	        								}
    											else if(c.equals(Color.WHITE)) {
    												if(blackCount != 0) {
    													blackCount = 0;
    												}
    												whiteCount++;
    											}
    	        							}
    	        							else {
    	        								if(c.equals(Color.BLACK)) {
    												if(whiteCount == 0) {
    													blackCount++;
    												}
    												else {
    													break;
    												}
    											}
    											else if(c.equals(Color.WHITE)) {
    												if(blackCount == 0) {
    													whiteCount++;
    												}
    												else {
    													break;
    												}
    											}
    											else if(c.equals(Color.RED)) {
    												break;
    											}
    	        							}
        								} catch(Exception e1) {
        									
        								}
	        						}
        							if(a == 2) {
        								Color c = null;
        								try {
        									c = MyData.pointsTree.get(setHashKey(i-m-5+b,j-m-5+b)).getColor();
        									if(b< 5) {
    	        								if(c.equals(Color.BLACK)) {
    												if(whiteCount != 0) {
    													whiteCount = 0;
    												}
    												blackCount++;
    	        								}
    											else if(c.equals(Color.WHITE)) {
    												if(blackCount != 0) {
    													blackCount = 0;
    												}
    												whiteCount++;
    											}
    	        							}
    	        							else {
    	        								if(c.equals(Color.BLACK)) {
    												if(whiteCount == 0) {
    													blackCount++;
    												}
    												else {
    													break;
    												}
    											}
    											else if(c.equals(Color.WHITE)) {
    												if(blackCount == 0) {
    													whiteCount++;
    												}
    												else {
    													break;
    												}
    											}
    											else if(c.equals(Color.RED)) {
    												break;
    											}
    	        							}
        								} catch(Exception e1) {
        								}
	        						}
        							if(a == 3) {
        								Color c = null;
        								try {
        									c = MyData.pointsTree.get(setHashKey(i-m+5-b,j-m-5+b)).getColor();
        									if(b< 5) {
    	        								if(c.equals(Color.BLACK)) {
    												if(whiteCount != 0) {
    													whiteCount = 0;
    												}
    												blackCount++;
    	        								}
    											else if(c.equals(Color.WHITE)) {
    												if(blackCount != 0) {
    													blackCount = 0;
    												}
    												whiteCount++;
    											}
    	        							}
    	        							else {
    	        								if(c.equals(Color.BLACK)) {
    												if(whiteCount == 0) {
    													blackCount++;
    												}
    												else {
    													break;
    												}
    											}
    											else if(c.equals(Color.WHITE)) {
    												if(blackCount == 0) {
    													whiteCount++;
    												}
    												else {
    													break;
    												}
    											}
    											else if(c.equals(Color.RED)) {
    												break;
    											}
    	        							}
        								} catch(Exception e1) {
        									
        								}
	        						}
    							}
        						MyData.influnceMatrix[i-m][j] += blackCount - whiteCount;
							}
						}
					}
				}
				
				if(n == 2) { 
					if((isItOnLeftTop || isItOnRightBottom) && !(isItOnLeftTop && isItOnRightBottom)) {
						if(isItOnLeftTop) { //isItOnLeftTop
							System.out.println("It's on Left-Top");
							for(int m = 1; m <= 5-distance[4]; m++) {
								if(MyData.influnceMatrix[i+m][j+m] == 100) continue;
								if(MyData.influnceMatrix[i+m][j+m] <= 0) {
									MyData.influnceMatrix[i+m][j+m] = 1;
								}
								for(int a = 0; a<4; a++) {
									int blackCount = 0;
									int whiteCount = 0;
	        						for(int b = 0; b<11;  b++) {
	        							if(b == 5) continue;
	        							if(a == 0) {
	        								Color c = null;
	        								try {
	        									c = MyData.pointsTree.get(setHashKey(i, j+m-5+b)).getColor();
	        									if(b< 5) {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount != 0) {
	    													whiteCount = 0;
	    												}
	    												blackCount++;
	    	        								}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount != 0) {
	    													blackCount = 0;
	    												}
	    												whiteCount++;
	    											}
	    	        							}
	    	        							else {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount == 0) {
	    													blackCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount == 0) {
	    													whiteCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.RED)) {
	    												break;
	    											}
	    	        							}
	        								} catch(Exception e1) {
	        								}
		        						}
	        							if(a == 1) {
	        								Color c = null;
	        								try {
	        									c = MyData.pointsTree.get(setHashKey(i+m-5+b,j)).getColor();
	        									if(b< 5) {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount != 0) {
	    													whiteCount = 0;
	    												}
	    												blackCount++;
	    	        								}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount != 0) {
	    													blackCount = 0;
	    												}
	    												whiteCount++;
	    											}
	    	        							}
	    	        							else {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount == 0) {
	    													blackCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount == 0) {
	    													whiteCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.RED)) {
	    												break;
	    											}
	    	        							}
	        								} catch(Exception e1) {
	        									
	        								}
		        						}
	        							if(a == 2) {
	        								int c1 = b+5-distance[4];
	        								if(c1 >= 11) break;
	        								Color c = null;
	        								try {
	        									c = MyData.pointsTree.get(setHashKey(i+m-5+b,j+m-5+b)).getColor();
	        									if(b< 5) {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount != 0) {
	    													whiteCount = 0;
	    												}
	    												blackCount++;
	    	        								}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount != 0) {
	    													blackCount = 0;
	    												}
	    												whiteCount++;
	    											}
	    	        							}
	    	        							else {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount == 0) {
	    													blackCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount == 0) {
	    													whiteCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.RED)) {
	    												break;
	    											}
	    	        							}
	        								} catch(Exception e1) {
	        								}
		        						}
	        							if(a == 3) {
	        								Color c = null;
	        								try {
	        									c = MyData.pointsTree.get(setHashKey(i+m+5-b,j+m-5+b)).getColor();
	        									if(b< 5) {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount != 0) {
	    													whiteCount = 0;
	    												}
	    												blackCount++;
	    	        								}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount != 0) {
	    													blackCount = 0;
	    												}
	    												whiteCount++;
	    											}
	    	        							}
	    	        							else {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount == 0) {
	    													blackCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount == 0) {
	    													whiteCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.RED)) {
	    												break;
	    											}
	    	        							}
	        								} catch(Exception e1) {
	        									
	        								}
		        						}
	    							}
	        						MyData.influnceMatrix[i+m][j+m] += blackCount - whiteCount;
								}
								
								
							}
						}
						else { //isItOnRightBottom
							System.out.println("It's on RightBottom");
							for(int m = 1; m <= 5-distance[5]; m++) {
								if(MyData.influnceMatrix[i-m][j-m] == 100) continue;
								if(MyData.influnceMatrix[i-m][j-m] <= 0) {
									MyData.influnceMatrix[i-m][j-m] = 1;
								}
								for(int a = 0; a<4; a++) {
									int blackCount = 0;
									int whiteCount = 0;
	        						for(int b = 0; b<11;  b++) {
	        							if(b == 5) continue;
	        							if(a == 0) {
	        								Color c = null;
	        								try {
	        									if(b< 5) {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount != 0) {
	    													whiteCount = 0;
	    												}
	    												blackCount++;
	    	        								}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount != 0) {
	    													blackCount = 0;
	    												}
	    												whiteCount++;
	    											}
	    	        							}
	    	        							else {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount == 0) {
	    													blackCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount == 0) {
	    													whiteCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.RED)) {
	    												break;
	    											}
	    	        							}
	        								} catch(Exception e1) {
	        								}
		        						}
	        							if(a == 1) {
	        								
	        								Color c = null;
	        								try {
	        									c = MyData.pointsTree.get(setHashKey(i-m-5+b,j)).getColor();
	        									if(b< 5) {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount != 0) {
	    													whiteCount = 0;
	    												}
	    												blackCount++;
	    	        								}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount != 0) {
	    													blackCount = 0;
	    												}
	    												whiteCount++;
	    											}
	    	        							}
	    	        							else {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount == 0) {
	    													blackCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount == 0) {
	    													whiteCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.RED)) {
	    												break;
	    											}
	    	        							}
	        								} catch(Exception e1) {
	        									
	        								}
		        						}
	        							if(a == 2) {
	        								if(b >=5-distance[5]) break;
	        								Color c = null;
	        								try {
	        									c = MyData.pointsTree.get(setHashKey(i-m-5+b,j-m-5+b)).getColor();
	        									if(b< 5) {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount != 0) {
	    													whiteCount = 0;
	    												}
	    												blackCount++;
	    	        								}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount != 0) {
	    													blackCount = 0;
	    												}
	    												whiteCount++;
	    											}
	    	        							}
	    	        							else {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount == 0) {
	    													blackCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount == 0) {
	    													whiteCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.RED)) {
	    												break;
	    											}
	    	        							}
	        								} catch(Exception e1) {
	        									
	        								}
	        								
		        							
		        						}
	        							if(a == 3) {
	        								Color c = null;
	        								try {
	        									c = MyData.pointsTree.get(setHashKey(i-m+5-b,j-m-5+b)).getColor();
	        									if(b< 5) {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount != 0) {
	    													whiteCount = 0;
	    												}
	    												blackCount++;
	    	        								}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount != 0) {
	    													blackCount = 0;
	    												}
	    												whiteCount++;
	    											}
	    	        							}
	    	        							else {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount == 0) {
	    													blackCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount == 0) {
	    													whiteCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.RED)) {
	    												break;
	    											}
	    	        							}
	        								} catch(Exception e1) {
	        									
	        								}
		        						}
	    							}
	        						MyData.influnceMatrix[i-m][j-m] += blackCount - whiteCount;
								}
							}
						}
					}
				}
				if(n == 3) { 
					if((isItOnLeftBottom || isItOnRightTop) && !(isItOnLeftBottom && isItOnRightTop)) {
						if(isItOnLeftBottom) { //isItOnLeftBottom
							System.out.println("It's on Left-Bottom");
							for(int m = 1; m <= 5-distance[6]; m++) {
								if(MyData.influnceMatrix[i-m][j+m] == 100) continue;
								if(MyData.influnceMatrix[i-m][j+m] <= 0) {
									MyData.influnceMatrix[i-m][j+m] = 1;
								}
								for(int a = 0; a<4; a++) {
									int blackCount = 0;
									int whiteCount = 0;
	        						for(int b = 0; b<11;  b++) {
	        							if(b == 5) continue;
	        							if(a == 0) {
	        								Color c = null;
	        								try {
	        									c = MyData.pointsTree.get(setHashKey(i, j+m-5+b)).getColor();
	        									if(b< 5) {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount != 0) {
	    													whiteCount = 0;
	    												}
	    												blackCount++;
	    	        								}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount != 0) {
	    													blackCount = 0;
	    												}
	    												whiteCount++;
	    											}
	    	        							}
	    	        							else {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount == 0) {
	    													blackCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount == 0) {
	    													whiteCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.RED)) {
	    												break;
	    											}
	    	        							}
	        								} catch(Exception e1) {
	        								}
		        						}
	        							if(a == 1) {
	        								Color c = null;
	        								try {
	        									c = MyData.pointsTree.get(setHashKey(i+m-5+b,j)).getColor();
	        									if(b< 5) {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount != 0) {
	    													whiteCount = 0;
	    												}
	    												blackCount++;
	    	        								}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount != 0) {
	    													blackCount = 0;
	    												}
	    												whiteCount++;
	    											}
	    	        							}
	    	        							else {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount == 0) {
	    													blackCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount == 0) {
	    													whiteCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.RED)) {
	    												break;
	    											}
	    	        							}
	        								} catch(Exception e1) {
	        									
	        								}
		        						}
	        							if(a == 2) {
	        								
	        								Color c = null;
	        								try {
	        									c = MyData.pointsTree.get(setHashKey(i+m-5+b,j+m-5+b)).getColor();
	        									if(b< 5) {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount != 0) {
	    													whiteCount = 0;
	    												}
	    												blackCount++;
	    	        								}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount != 0) {
	    													blackCount = 0;
	    												}
	    												whiteCount++;
	    											}
	    	        							}
	    	        							else {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount == 0) {
	    													blackCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount == 0) {
	    													whiteCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.RED)) {
	    												break;
	    											}
	    	        							}
	        								} catch(Exception e1) {
	        								}
		        						}
	        							if(a == 3) {
	        								Color c = null;
	        								try {
	        									c = MyData.pointsTree.get(setHashKey(i+m+5-b,j+m-5+b)).getColor();
	        									int c1 = b+5-distance[4];
		        								if(c1 >= 11) break;
	        									if(b< 5) {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount != 0) {
	    													whiteCount = 0;
	    												}
	    												blackCount++;
	    	        								}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount != 0) {
	    													blackCount = 0;
	    												}
	    												whiteCount++;
	    											}
	    	        							}
	    	        							else {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount == 0) {
	    													blackCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount == 0) {
	    													whiteCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.RED)) {
	    												break;
	    											}
	    	        							}
	        								} catch(Exception e1) {
	        									
	        								}
		        						}
	    							}
	        						MyData.influnceMatrix[i+m][j+m] += blackCount - whiteCount;
								}
								
								
							}
						}
						else { //isItOnRightBottom
							for(int m = 1; m <= 5-distance[1]; m++) {
								if(MyData.influnceMatrix[i-m][j-m] == 100) continue;
								if(MyData.influnceMatrix[i-m][j-m] <= 0) {
									MyData.influnceMatrix[i-m][j-m] = 1;
								}
								for(int a = 0; a<4; a++) {
									int blackCount = 0;
									int whiteCount = 0;
	        						for(int b = 0; b<11;  b++) {
	        							if(b == 5) continue;
	        							if(a == 0) {
	        								Color c = null;
	        								try {
	        									if(b< 5) {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount != 0) {
	    													whiteCount = 0;
	    												}
	    												blackCount++;
	    	        								}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount != 0) {
	    													blackCount = 0;
	    												}
	    												whiteCount++;
	    											}
	    	        							}
	    	        							else {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount == 0) {
	    													blackCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount == 0) {
	    													whiteCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.RED)) {
	    												break;
	    											}
	    	        							}
	        								} catch(Exception e1) {
	        								}
		        						}
	        							if(a == 1) {
	        								
	        								Color c = null;
	        								try {
	        									c = MyData.pointsTree.get(setHashKey(i-m-5+b,j)).getColor();
	        									if(b< 5) {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount != 0) {
	    													whiteCount = 0;
	    												}
	    												blackCount++;
	    	        								}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount != 0) {
	    													blackCount = 0;
	    												}
	    												whiteCount++;
	    											}
	    	        							}
	    	        							else {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount == 0) {
	    													blackCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount == 0) {
	    													whiteCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.RED)) {
	    												break;
	    											}
	    	        							}
	        								} catch(Exception e1) {
	        									
	        								}
		        						}
	        							if(a == 2) {
	        								if(b >=5-distance[5]) break;
	        								Color c = null;
	        								try {
	        									c = MyData.pointsTree.get(setHashKey(i-m-5+b,j-m-5+b)).getColor();
	        									if(b< 5) {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount != 0) {
	    													whiteCount = 0;
	    												}
	    												blackCount++;
	    	        								}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount != 0) {
	    													blackCount = 0;
	    												}
	    												whiteCount++;
	    											}
	    	        							}
	    	        							else {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount == 0) {
	    													blackCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount == 0) {
	    													whiteCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.RED)) {
	    												break;
	    											}
	    	        							}
	        								} catch(Exception e1) {
	        									
	        								}
	        								
		        							
		        						}
	        							if(a == 3) {
	        								Color c = null;
	        								try {
	        									c = MyData.pointsTree.get(setHashKey(i-m+5-b,j-m-5+b)).getColor();
	        									if(b< 5) {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount != 0) {
	    													whiteCount = 0;
	    												}
	    												blackCount++;
	    	        								}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount != 0) {
	    													blackCount = 0;
	    												}
	    												whiteCount++;
	    											}
	    	        							}
	    	        							else {
	    	        								if(c.equals(Color.BLACK)) {
	    												if(whiteCount == 0) {
	    													blackCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.WHITE)) {
	    												if(blackCount == 0) {
	    													whiteCount++;
	    												}
	    												else {
	    													break;
	    												}
	    											}
	    											else if(c.equals(Color.RED)) {
	    												break;
	    											}
	    	        							}
	        								} catch(Exception e1) {
	        									
	        								}
		        						}
	    							}
	        						MyData.influnceMatrix[i-m][j-m] += blackCount - whiteCount;
								}
							}
						}
					}
				}
				try {
					for(int p=0; p<19; p++) { //print MyData.influnceMatrix[i][j] Output
			        	for(int q=0; q<19; q++) {
			        		if(95 <= MyData.influnceMatrix[q][p] && MyData.influnceMatrix[q][p] <= 105  ) {
			        			MyData.influnceMatrix[q][p] = 100;
			        		}
			        		else if(-95 >= MyData.influnceMatrix[q][p] && MyData.influnceMatrix[q][p] >= -105  ) {
			        			MyData.influnceMatrix[q][p] = -100;
			        		}
			        		else if(995 <= MyData.influnceMatrix[q][p] && MyData.influnceMatrix[q][p] <= 1005  ) {
			        			MyData.influnceMatrix[q][p] = 1000;
			        		}
			        		System.out.print(MyData.influnceMatrix[q][p] + "|\t");
//			        		System.out.print(MyData.pointsTree.get(180).getInfo()[i][j] + " | ");
			        	}
			        	System.out.println();
					}
				} catch(Exception e1) {
					
				}
				
			}
			
		}
	}
}


