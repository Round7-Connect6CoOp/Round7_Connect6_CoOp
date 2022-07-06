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
//	public static PriorityQueue< HashMap<Integer, Integer>> maxHeap = new PriorityQueue< HashMap<Integer, Integer>>((Collection<? extends HashMap<Integer, Integer>>) Comparator.reverseOrder());

	
	//Hash function 19*x + y
	public static TreeMap<Integer,Integer> pointsTree = new TreeMap<Integer,Integer>();
	
	public static int count = 0;
	
	public MyData(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
		int key = 19*x + y;
		if(color.equals(Color.RED)) {
			pointsTree.put(key, 3);
		}
		else if(color.equals(Color.BLACK)) {
			pointsTree.put(key, 1);
		}
		else if(color.equals(Color.WHITE)) {
			pointsTree.put(key, 2);
		}
		else {
			pointsTree.put(key, 0);
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
	
}
