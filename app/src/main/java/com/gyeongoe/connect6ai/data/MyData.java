package com.gyeongoe.connect6ai.data;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.util.*;

public class MyData {
	private int x;
	private int y;
	private Color color;
	
	public static ArrayList<MyData> clickedPoint = new ArrayList<MyData>();
	
	public static HashMap<Point, Integer> lineInformation = new HashMap<Point, Integer>();
	
	// This heap wiill be used for deciding where to set the stone (It's a max heap!)
	public static PriorityQueue<Integer> maxHeap = new PriorityQueue< Integer>(Comparator.reverseOrder());

	//Hash function 19*x + y
	public static TreeMap<Integer,MyData> pointsTree = new TreeMap<Integer,MyData>();
	
	//Hash function for 6 items: When black won: 5592405; When white won: 11184810
	
	public static int count = 0;
	private int[][] info = new int[4][11];
	
	public MyData(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
		
		// Horizontal 0, Vertical 1, Left-Right Diagonal 2, Right-Left Diagonal 3
		for(int i=0; i<4; i++) {
			info[i][5] = setHashKey(x,y);
			try {
				for(int j=0; j<11; j++) {
					if(j == 5) {
						continue;
					}
					if(i==0) { // horizontal
						info[i][j] = setHashKey(x-5+j,y);
					}
					else if(i==1) { //vertical
						info[i][j] = setHashKey(x,y-5+j);
					}
					else if(i==2) { // Left-Right
						info[i][j] = setHashKey(x-5+j,y-5+j);
					}
					else if(i==3) { // Right-Left
						info[i][j] = setHashKey(x+5-j,y-5+j);
					}
				}
			} catch(Exception e) {
				
			}
			
		}
		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public int setHashKey(int x, int y ) {
		return 19*x+y;
	}
	
	public int getXFromHashKey(int key) {
		return key/19;
	}
	
	public int getYFromHashKey(int key) {
		return key%19;
	}
	
}
