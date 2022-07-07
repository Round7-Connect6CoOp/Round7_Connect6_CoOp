package com.gyeongoe.connect6ai.logic;

import java.awt.Point;
import java.awt.datatransfer.SystemFlavorMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import com.gyeongoe.connect6ai.data.MyData;

public class GameLogic {
	// For Line information Blank 0, Black 1, White 2, Ban 3 4^0 to 4^11
	// For Game Logic Blank null, Black 10, White 10, Effect range of black +1, Effect range of White -1, Ban 100
	// Horizontal 0, Vertical 1, Left-Right Diagonal 2, Right-Left Diagonal 3
	
	public static boolean areWeFirst = false;
	public static boolean areWeSecond = false;
	
	private Stack <Integer> tempBlackStack = new Stack <Integer>();
	private Stack <Integer> tempWhiteStack = new Stack <Integer>();
	
	int x1, y1, x2, y2;
	public ArrayList<Integer> minArray = new ArrayList<Integer>();
	public ArrayList<Integer> maxArray = new ArrayList<Integer>();
	
	private boolean win = false;
	ArrayList<Integer> answer = new ArrayList<Integer>();
	
	public GameLogic(int x1, int y1, int x2, int y2){
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}
		//1 - 우리말이 4개 이상인 곳 찾기 - 상대가 우리의 말을 막는데 3개이상의 돌이 필요한 경우 
		//2 - 상대말이 이길 수 있는 4개가 연속된 스틱을 찾아 막기 
			//2-1 우리의 말이 4개가 될 수 있는 경우 
			//2-2 우리의 말이 3개가 될 수 있는 경우 
			//2-3 우리의 말이 2개가 될 수 있는 경우
		//3 - 우리의 말을 연속해 4,5개를 만들 수 있는 경우 
		//4 - 상대의 3이나 2를 막으면서 우리가 3, 2가 될 수 있는 자리 선택 
	public ArrayList<Integer> makeTheBestDecision(){
		System.out.println(areWeFirst);
		if(areWeFirst) {
			for(int i =0; i<4; i++) {
				int key = 0;
				try {
					key =MyData.newestBlack.pop();
					tempBlackStack.push(key);
					int infos[][] = MyData.pointsTree.get(key).getInfo();
					int blank = 0;
					int black = 1;
					for(int a=0; a<4; a++) {
						for(int b = 0; b < 11;b++) {
							if(b == 5) {
								if((blank + black == 6) &&(blank >=2)) {
									win = true;
									 MyData.pointsTree.get(key).getVectorKey();
									return answer;
								}
								else {
									answer.clear();
									blank = 0;
									black = 1;
								}
							}
							if(infos[a][b] == 0) {
								blank++;
								answer.add(MyData.pointsTree.get(key).getVectorKey()[a][b]);
							}
							if(infos[a][b] == 1) {
								black++;
							}
						}
					}
				} catch(Exception e) {
				}
				
			}
			answer.clear();
			while(!tempBlackStack.empty()) {
				MyData.newestBlack.push(tempBlackStack.pop());
			} // First condition/ Can we win?
			
			for(int i =0; i<4; i++) {
				int key = 0;
				try {
					key =MyData.newestWhite.pop();
					tempWhiteStack.push(key);
					int infos[][] = MyData.pointsTree.get(key).getInfo();
					int blank = 0;
					int white = 1;
					for(int a=0; a<4; a++) {
						for(int b = 0; b < 11;b++) {
							if(b == 5) {
								if(blank <= 2 && white >=4) {
									win = true;
									 MyData.pointsTree.get(key).getVectorKey();
									return answer;
								}
								else {
									answer.clear();
									blank = 1;
									white = 0;
								}
							}
							if(infos[a][b] == 0) {
								blank++;
								answer.add(MyData.pointsTree.get(key).getVectorKey()[a][b]);
							}
							if(infos[a][b] == 2) {
								white++;
							}
						}
					}
				} catch(Exception e) {
				}
				
			}
			while(!tempWhiteStack.empty()) {
				MyData.newestWhite.push(tempWhiteStack.pop());
			} // Second Condition :lose?
			
			for(int i =0; i<3; i++) {
				int key = 0;
				try {
					key =MyData.newestBlack.pop();
					tempBlackStack.push(key);
					int infos[][] = MyData.pointsTree.get(key).getInfo();
					int blank = 0;
					int black = 1;
					for(int a=0; a<4; a++) {
						for(int b = 0; b < 11;b++) {
							if(b == 5) {
								if((blank + black == 6) &&(blank >=2)) {
									win = true;
									 MyData.pointsTree.get(key).getVectorKey();
									return answer;
								}
								else {
									answer.clear();
									blank = 0;
									black = 1;
								}
							}
							if(infos[a][b] == 0) {
								blank++;
								answer.add(MyData.pointsTree.get(key).getVectorKey()[a][b]);
							}
							if(infos[a][b] == 1) {
								black++;
							}
						}
					}
				} catch(Exception e) {
				}
				
			}
			answer.clear();
			while(!tempBlackStack.empty()) {
				MyData.newestBlack.push(tempBlackStack.pop());
			} 
			
			for(int i =0; i<3; i++) {
				int key = 0;
				try {
					key =MyData.newestWhite.pop();
					tempWhiteStack.push(key);
					int infos[][] = MyData.pointsTree.get(key).getInfo();
					int blank = 0;
					int white = 1;
					for(int a=0; a<4; a++) {
						for(int b = 0; b < 11;b++) {
							if(b == 5) {
								if(blank <= 2 && white >=4) {
									win = true;
									 MyData.pointsTree.get(key).getVectorKey();
									return answer;
								}
								else {
									answer.clear();
									blank = 1;
									white = 0;
								}
							}
							if(infos[a][b] == 0) {
								blank++;
								answer.add(MyData.pointsTree.get(key).getVectorKey()[a][b]);
							}
							if(infos[a][b] == 2) {
								white++;
							}
						}
					}
				} catch(Exception e) {
				}
				
			}
			while(!tempWhiteStack.empty()) {
				MyData.newestWhite.push(tempWhiteStack.pop());
			} // Second Condition :lose?
			
		}
		
		else {		// We are second
			for(int i =0; i<4; i++) {
				int key = 0;
				try {
					key =MyData.newestWhite.pop();
					tempWhiteStack.push(key);
				} catch(Exception e) {
				}
				int infos[][] = MyData.pointsTree.get(key).getInfo();
				int blank = 0;
				int white = 1;
				for(int a=0; a<4; a++) {
					for(int b = 0; b < 11;b++) {
						if(b == 5) {
							if(blank <= 2 && white >=4) {
								win = true;
								 MyData.pointsTree.get(key).getVectorKey();
								return answer;
							}
							else {
								answer.clear();
								blank = 1;
								white = 0;
							}
						}
						if(infos[a][b] == 0) {
							blank++;
							answer.add(MyData.pointsTree.get(key).getVectorKey()[a][b]);
						}
						if(infos[a][b] == 2) {
							white++;
						}
					}
					
				}
			}
			while(!tempWhiteStack.empty()) {
				MyData.newestWhite.push(tempWhiteStack.pop());
			} // First condition/ Can we win?
			
			for(int i =0; i<4; i++) {
				int key = 0;
				try {
					key =MyData.newestBlack.pop();
					tempBlackStack.push(key);
				} catch(Exception e) {
				}
				int infos[][] = MyData.pointsTree.get(key).getInfo();
				int blank = 0;
				int black = 1;
				for(int a=0; a<4; a++) {
					for(int b = 0; b < 11;b++) {
						if(b == 5) {
							if(blank <= 2 && black >=4) {
								win = true;
								 MyData.pointsTree.get(key).getVectorKey();
								return answer;
							}
							else {
								answer.clear();
								blank = 0;
								black = 1;
							}
						}
						if(infos[a][b] == 0) {
							blank++;
							answer.add(MyData.pointsTree.get(key).getVectorKey()[a][b]);
						}
						if(infos[a][b] == 1) {
							black++;
						}
					}
					
				}
			}
			while(!tempBlackStack.empty()) {
				MyData.newestBlack.push(tempBlackStack.pop());
			} // Second Condition :lose?
		}
		return answer;
	}
	
	public int getXFromHashKey(int key) {
		return key/19;
	}
	
	public int getYFromHashKey(int key) {
		return key%19;
	}
		
//		//상대를 막을 때에는 가중치가 작은 곳에 놓는 것이 유리
//			//가중치의 값이 같을 경우에는 같은 가중치를 가지는 모든 돌의 영향력을 계산 한 후에 두기 
//		//공격을 할 때에는 가중치가 높은 곳에 놓는 것이 유리
//			//가중치의 값이 같을 경우에는 같은 가중치를 가지는 모든 돌의 영향력을 계산 한 후에 두기 
//		
//		//돌은 두개를 두어야 함으로 모든 조건을 최소 두번씩은 확인해야
//		int count = 0; //현재둔 바둑돌의 갯수 
//		int blank = 0; //현재 스틱의 카운트를 저장 - 이 카운트가 3이상이 되면 다음 스틱으로 넘어감 
//		int dollColor; //현재 돌의 색상을 저장
//		int dollCount = 0;//현재 같은 색상의 돌의 갯수를 카운트 
//		
//		while(count<=2) {
//			for() {//주어진 모든 돌들에 대해서 스틱 돌기
//				for(int i=0; i<4; i++) {
//					dollColor = [][5]//중간값의 돌의 색상 읽어오기 
//					if(/*우리의 돌일 때*/){//4개의 연속을 두개를 만들면 무조건 승리 한쪽이 막힌 4개의 연속과 양옆이 모두 뚫린 4개의 연속이 최소 조건 
//						//막혀 있더라도 상대가 방어에 2개의 돌을 모두 쓰게하는 것이 유리 
//
//						//0~5까지에서 같은 색의 돌이 4개, 공백 2개 
//						//6~11까지에서 같은 색의 돌이 4개, 공백 2개 
//						
//						for(int j=0; j<6; j++) {//0~5
//							if(돌이다) {
//								if(/*돌의 색이 우리일 때*/dollColor == 우리돌의 색 ){
//									if(blank<=2 && dollCount == 4) {
//										//4이상인 곳에 돌 놓기 
//										//가중치가 가장 높은 곳에 놓기 
//									}
//									else {
//										dollCount++;
//									}
//								}	
//								else if(/*돌의 색이 상대일 때*/dollColor != 우리돌의 색){
//									break;
//								}
//							}
//							else {//공백이다 
//								blank++;
//								if(blank>2) break;
//							}
//						}
//						for(int j=6; j<11; j++) {//6~11
//							if(돌이다) {
//								if(/*돌의 색이 우리일 때*/dollColor == 우리돌 ){
//									if(blank<=2 && dollCount == 4) {
//										//4이상인 곳에 돌 놓기 
//									}
//									else {
//										dollCount++;
//									}
//								}	
//								else if(/*돌의 색이 상대일 때*/dollColor != 우리돌 )){
//									break;
//								}
//							}
//							else {//공백이다
//								blank++;
//								if(blank>2) break;
//							}
//						}
//					}
//					else if() {//상대의 돌일 
//						for(int j=0; j<6; j++) {//0~5
//							if(돌이다) {
//								if(/*돌의 색이 우리일 때*/dollColor == 상대의 돌 ){
//									if(blank<=2 && dollCount == 4) {
//										//4이상인 곳에 돌 놓기 
//										//4가 될 수있는 경우  1 2 1, 1 1 2, 2 1 1, 2 2, 3 1, 1 3, 4
//										//막혀 있더라도 상대가 방어에 2개의 돌을 모두 쓰게하는 것이 유리 
//										//0~5까지에서 같은 색의 돌이 4개, 공백 2개 
//										//6~1까지에서 같은 색의 돌이 4개, 공백 2개 
//									}
//									else {
//										dollCount++;
//									}
//								}	
//								else if(/*돌의 색이 상대일 때*/dollColor != 상대의 돌 ){
//									break;
//								}
//							}
//							else {//공백이다 
//								blank++;
//								if(blank>2) break;
//							}
//						}
//						for(int j=6; j<11; j++) {//6~11
//							if(돌이다) {
//								if(/*돌의 색이 우리일 때*/dollColor == 상대의 돌 ){
//									if(blank<=2 && dollCount == 4) {
//										//4이상인 곳에 돌 놓기 
//										//4가 될 수있는 경우  1 2 1, 1 1 2, 2 1 1, 2 2, 3 1, 1 3, 4
//										//막혀 있더라도 상대가 방어에 2개의 돌을 모두 쓰게하는 것이 유리 
//										//0~5까지에서 같은 색의 돌이 4개, 공백 2개 
//										//6~1까지에서 같은 색의 돌이 4개, 공백 2개 
//									}
//									else {
//										dollCount++;
//									}
//								}	
//								else if(/*돌의 색이 상대일 때*/dollColor != 상대의 돌 ){
//									break;
//								}
//							}
//							else {//공백이다
//								blank++;
//								if(blank>2) break;
//							}
//						}
//					}
//					else if(/*스틱 안의 값이 2또는 3*/) {//두개를 두어서 4개를 만들기 
//						//가중치가 가장 높은 돌이 돼 
//						if(/*스틱안에서 3이 연속되어 있을때*/) {
//							
//						}
//					}
//					else if(/*상대를 막으면서 우리의 최선수 놓기*/) {
//						//막혀있지 않는 3개
//						//양옆이 뚫려 있는 2개의 두쌍 
//						//한쪽이 막혀있는 2개, 양쪽이 뚫린 2개
//						//한쪽만 막혀있는 3개 
//					}
//				}
//			}
//		}
//		
//		public ArrayList<Integer> findMin() {
//			int min = 100;
//			for(int i=0; i<=18; i++) {
//	        	for(int j=0; j<=18; j++) {
//	        		if(0 > MyData.influnceMatrix[i][j] && MyData.influnceMatrix[i][j]>= -8) {
//	        			if(min > Math.abs(MyData.influnceMatrix[i][j])) {
//	        				min = Math.abs(MyData.influnceMatrix[i][j]);
//	        			}
//	        		}
//	        	}
//	        }
//			for(int i=0; i<=18; i++) {
//				for(int j=0; j<=18; j++) {
//					if(0 > MyData.influnceMatrix[i][j] && MyData.influnceMatrix[i][j]>= -100) {
//						if(min == Math.abs(MyData.influnceMatrix[i][j])) {
//							minArray.add(19*i+j);
//						}
//					}
//				}
//			}
//			return minArray;
//		}
//		
//		ArrayList<Integer>  findMax() {
//			int max=0;
//			for(int i=0; i<=18; i++) {
//	        	for(int j=0; j<=18; j++) {
//	        		if(max < MyData.influnceMatrix[i][j] && MyData.influnceMatrix[i][j] <= 100) {
//	        			max = MyData.influnceMatrix[i][j];
//	        		}
//	        	}
//	        }
//			for(int i=0; i<=18; i++) {
//				for(int j=0; j<=18; j++) {
//					if(max == MyData.influnceMatrix[i][j]) {
//						maxArray.add(19*i+j);
//					}
//				}
//			}
//			return maxArray;
//		}
}