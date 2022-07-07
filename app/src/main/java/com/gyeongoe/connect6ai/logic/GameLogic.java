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
		//1 - �츮���� 4�� �̻��� �� ã�� - ��밡 �츮�� ���� ���µ� 3���̻��� ���� �ʿ��� ��� 
		//2 - ��븻�� �̱� �� �ִ� 4���� ���ӵ� ��ƽ�� ã�� ���� 
			//2-1 �츮�� ���� 4���� �� �� �ִ� ��� 
			//2-2 �츮�� ���� 3���� �� �� �ִ� ��� 
			//2-3 �츮�� ���� 2���� �� �� �ִ� ���
		//3 - �츮�� ���� ������ 4,5���� ���� �� �ִ� ��� 
		//4 - ����� 3�̳� 2�� �����鼭 �츮�� 3, 2�� �� �� �ִ� �ڸ� ���� 
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
		
//		//��븦 ���� ������ ����ġ�� ���� ���� ���� ���� ����
//			//����ġ�� ���� ���� ��쿡�� ���� ����ġ�� ������ ��� ���� ������� ��� �� �Ŀ� �α� 
//		//������ �� ������ ����ġ�� ���� ���� ���� ���� ����
//			//����ġ�� ���� ���� ��쿡�� ���� ����ġ�� ������ ��� ���� ������� ��� �� �Ŀ� �α� 
//		
//		//���� �ΰ��� �ξ�� ������ ��� ������ �ּ� �ι����� Ȯ���ؾ�
//		int count = 0; //����� �ٵϵ��� ���� 
//		int blank = 0; //���� ��ƽ�� ī��Ʈ�� ���� - �� ī��Ʈ�� 3�̻��� �Ǹ� ���� ��ƽ���� �Ѿ 
//		int dollColor; //���� ���� ������ ����
//		int dollCount = 0;//���� ���� ������ ���� ������ ī��Ʈ 
//		
//		while(count<=2) {
//			for() {//�־��� ��� ���鿡 ���ؼ� ��ƽ ����
//				for(int i=0; i<4; i++) {
//					dollColor = [][5]//�߰����� ���� ���� �о���� 
//					if(/*�츮�� ���� ��*/){//4���� ������ �ΰ��� ����� ������ �¸� ������ ���� 4���� ���Ӱ� �翷�� ��� �ո� 4���� ������ �ּ� ���� 
//						//���� �ִ��� ��밡 �� 2���� ���� ��� �����ϴ� ���� ���� 
//
//						//0~5�������� ���� ���� ���� 4��, ���� 2�� 
//						//6~11�������� ���� ���� ���� 4��, ���� 2�� 
//						
//						for(int j=0; j<6; j++) {//0~5
//							if(���̴�) {
//								if(/*���� ���� �츮�� ��*/dollColor == �츮���� �� ){
//									if(blank<=2 && dollCount == 4) {
//										//4�̻��� ���� �� ���� 
//										//����ġ�� ���� ���� ���� ���� 
//									}
//									else {
//										dollCount++;
//									}
//								}	
//								else if(/*���� ���� ����� ��*/dollColor != �츮���� ��){
//									break;
//								}
//							}
//							else {//�����̴� 
//								blank++;
//								if(blank>2) break;
//							}
//						}
//						for(int j=6; j<11; j++) {//6~11
//							if(���̴�) {
//								if(/*���� ���� �츮�� ��*/dollColor == �츮�� ){
//									if(blank<=2 && dollCount == 4) {
//										//4�̻��� ���� �� ���� 
//									}
//									else {
//										dollCount++;
//									}
//								}	
//								else if(/*���� ���� ����� ��*/dollColor != �츮�� )){
//									break;
//								}
//							}
//							else {//�����̴�
//								blank++;
//								if(blank>2) break;
//							}
//						}
//					}
//					else if() {//����� ���� 
//						for(int j=0; j<6; j++) {//0~5
//							if(���̴�) {
//								if(/*���� ���� �츮�� ��*/dollColor == ����� �� ){
//									if(blank<=2 && dollCount == 4) {
//										//4�̻��� ���� �� ���� 
//										//4�� �� ���ִ� ���  1 2 1, 1 1 2, 2 1 1, 2 2, 3 1, 1 3, 4
//										//���� �ִ��� ��밡 �� 2���� ���� ��� �����ϴ� ���� ���� 
//										//0~5�������� ���� ���� ���� 4��, ���� 2�� 
//										//6~1�������� ���� ���� ���� 4��, ���� 2�� 
//									}
//									else {
//										dollCount++;
//									}
//								}	
//								else if(/*���� ���� ����� ��*/dollColor != ����� �� ){
//									break;
//								}
//							}
//							else {//�����̴� 
//								blank++;
//								if(blank>2) break;
//							}
//						}
//						for(int j=6; j<11; j++) {//6~11
//							if(���̴�) {
//								if(/*���� ���� �츮�� ��*/dollColor == ����� �� ){
//									if(blank<=2 && dollCount == 4) {
//										//4�̻��� ���� �� ���� 
//										//4�� �� ���ִ� ���  1 2 1, 1 1 2, 2 1 1, 2 2, 3 1, 1 3, 4
//										//���� �ִ��� ��밡 �� 2���� ���� ��� �����ϴ� ���� ���� 
//										//0~5�������� ���� ���� ���� 4��, ���� 2�� 
//										//6~1�������� ���� ���� ���� 4��, ���� 2�� 
//									}
//									else {
//										dollCount++;
//									}
//								}	
//								else if(/*���� ���� ����� ��*/dollColor != ����� �� ){
//									break;
//								}
//							}
//							else {//�����̴�
//								blank++;
//								if(blank>2) break;
//							}
//						}
//					}
//					else if(/*��ƽ ���� ���� 2�Ǵ� 3*/) {//�ΰ��� �ξ 4���� ����� 
//						//����ġ�� ���� ���� ���� �� 
//						if(/*��ƽ�ȿ��� 3�� ���ӵǾ� ������*/) {
//							
//						}
//					}
//					else if(/*��븦 �����鼭 �츮�� �ּ��� ����*/) {
//						//�������� �ʴ� 3��
//						//�翷�� �շ� �ִ� 2���� �ν� 
//						//������ �����ִ� 2��, ������ �ո� 2��
//						//���ʸ� �����ִ� 3�� 
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