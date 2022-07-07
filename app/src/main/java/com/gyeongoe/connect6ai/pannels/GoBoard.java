package com.gyeongoe.connect6ai.pannels;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.gyeongoe.connect6ai.MainFrame;
import com.gyeongoe.connect6ai.data.MyData;
import com.gyeongoe.connect6ai.logic.GameLogic;

public class GoBoard extends JPanel {
	Ellipse2D.Double[][] ellipse = new Ellipse2D.Double[20][20];
	Ellipse2D.Double[][] usedEllipse = new Ellipse2D.Double[20][20];
	
	private int[][] gameMatrix = new int[19][19];
	private int[][] infoMatrix = new int[19][19];
	Color currentColor = Color.RED;
	
	private boolean firstClick = false;
	private boolean whiteTurnFirst = false;
	private boolean whiteTurnSecond = false;
	private boolean blackTurnFirst = false;
	private boolean blackTurnSecond = false;
	private boolean isBlockFilled = false;
	private boolean start = false;
	private boolean whiteWin = false;
	private boolean blackWin = false;
	private boolean areWeFirst = false;
	private boolean areWeSecond = false;
	
	private int blockCount = 0;
	private int blockNumber = 4;
	
	private int x = -1;
	private int y = -1;
	
	public GoBoard(){
		MyData.pointsTree.put(19*9+9, null);
		setBounds(5, 5, 600, 600);
		setBackground(new Color(220, 179, 92));
		for(int i=0; i<=18; i++) {
        	for(int j=0; j<=18; j++) {
        		gameMatrix[i][j] = 0;
        	}
		}
		
		infoMatrix[9][9] = setHashKey(9, 9);
		MyData.pointsTree.put(setHashKey(9, 9), null);
		while(MyData.pointsTree.size() < 361) {
			int tempX = (int) (Math.random()*19);
			int tempY = (int) (Math.random()*19);
			
			if(MyData.pointsTree.containsKey(setHashKey(tempX, tempY))) continue;
			infoMatrix[tempX][tempY] = setHashKey(tempX, tempY);
			MyData.pointsTree.put(setHashKey(tempX, tempY), null);
		}
		
		for(int i=0; i<=18; i++) {
        	for(int j=0; j<=18; j++) {
        		System.out.print(infoMatrix[i][j]+" | ");
        	}
        	System.out.println();
		}
		
		
		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(start) {
					for(int i=0; i<=18; i++) {
			        	for(int j=0; j<=18; j++) {
			        		if(isBlockFilled) {
			        			if(usedEllipse[i][j] == null || !usedEllipse[i][j].contains(e.getPoint())) {
				        			if(ellipse[i][j].contains(e.getPoint())) {
				        				usedEllipse[i][j] = new Ellipse2D.Double(i*30+20, j*30+20,20, 20);
				        				try {
				        					AudioInputStream audio = AudioSystem.getAudioInputStream(new File("./assets/MP_Button.wav"));
				        					Clip clip = AudioSystem.getClip();
				        					clip.stop();
				        					clip.open(audio);
				        					clip.start();
				        				
				        				} catch(Exception e1) {
				        					e1.printStackTrace();
				        				}
				        				
					        			if(!firstClick) {
					        				currentColor = Color.BLACK;
					        				MyData newData = new MyData(i, j, currentColor);
					        				MyData.clickedPoint.add(newData);
					        				MyData.influnceMatrix[i][j] = 10;
					        				MyData.newestBlack.push(setHashKey(i,j));
				        					for(int a = 0; a < 11; a++) {
				        						if(a==5) continue;
				        						MyData.influnceMatrix[i][j-5+a] +=1;
				        						MyData.influnceMatrix[i-5+a][j] +=1;
				        						MyData.influnceMatrix[i-5+a][j-5+a] +=1;
				        						MyData.influnceMatrix[i+5-a][j-5+a] +=1;
				        					}
					        				currentColor = Color.WHITE;
					        				if(areWeFirst) {
					        					whiteTurnFirst = true;
					        				}
					        				else {
					        					for(int k = 0; k < 2; k++) {
				        							int p = (int) (Math.random() * 19);
					        						int q = (int) (Math.random() * 19);
					        						MyData data = new MyData(p, q, Color.WHITE);
					        						MyData.clickedPoint.add(data);
				        						}
					        					blackTurnFirst = true;
					        				}
					        				firstClick =true;
					        				gameMatrix[i][j] = 1;
					        				newData.getInformation(gameMatrix);
					        				MyData.pointsTree.put(setHashKey(i, j), newData);
					        			}
					        			else {
					        				if(areWeFirst) { // We are first
					        					if(whiteTurnFirst) {
						        					x = i;
						        					y = j;
						        					gameMatrix[i][j] = 2;
						        					currentColor = Color.WHITE;
						        					//START POINT: working on influnce Matrix!
						        					MyData.influnceMatrix[i][j] = -10;
<<<<<<< HEAD
						        					MyData.newestWhite.push(setHashKey(i,j));
=======
<<<<<<< HEAD
						        					
						        					for(int n=0; n<4; n++) {
						        						for(int a=1; a<=5; a++) {
						        							if(n==0) {
						        								if(MyData.influnceMatrix[i-a][j]!=-10 || MyData.influnceMatrix[i-a][j]!=100) {
						        									MyData.influnceMatrix[i-a][j] +=-1;
							        								MyData.influnceMatrix[i+a][j] +=-1;
						        								}
						        								
							        						}
							        						if(n==1) {
							        							MyData.influnceMatrix[i-a][j+a] +=-1;
							        							MyData.influnceMatrix[i+a][j+a] +=-1;
							        						}
															if(n==2) {
																MyData.influnceMatrix[i][j+a] +=-1;
																MyData.influnceMatrix[i][j-a] +=-1;
															}
															if(n==3) {
																MyData.influnceMatrix[i+a][j+a] +=-1;
																MyData.influnceMatrix[i-a][j-a] +=-1;
															}
						        						}
						        					}
						        					
						        					
						        					
						        					
						        					for(int a=1; a<=5; a++) {
						        						MyData.influnceMatrix[i-a][j] +=-1;
						        						MyData.influnceMatrix[i-a][j+a] +=-1;
						        						MyData.influnceMatrix[i][j+a] +=-1;
						        						MyData.influnceMatrix[i+a][j+a] +=-1;
						        						if(MyData.influnceMatrix[i][j+a]==10 || MyData.influnceMatrix[i][j+a]==100) {
						        							//다음중지 
						        						}
						        						else if(MyData.influnceMatrix[i][j+a]==-10){
						        							MyData.influnceMatrix[i][j+a]= -10;
						        						}
						        						else {
						        							MyData.influnceMatrix[i][j+a]=-1;
						        						}
						        					}
						        					
						        					for(int a=1; a<=5; a++) {
						        						MyData.influnceMatrix[i-a][j-a] +=-1;
						        						MyData.influnceMatrix[i][j-a] +=-1;
						        						MyData.influnceMatrix[i+a][j-a] +=- 1;
						        						MyData.influnceMatrix[i+a][j] +=-1;
						        					}
						        					
						        					
						        					
						        					
=======
>>>>>>> 95cd3ad701d9b702f5725d5cddb5da5201ab3087
//						        					for(int a = 0; a < 11; a++) {
//						        						if(a==5) continue;
//						        						if(!MyData.influnceMatrix[i][j-5+a].equals(100) && !MyData.influnceMatrix[i][j-5+a].equals(10) && !MyData.influnceMatrix[i][j-5+a].equals(-10){
//						        							MyData.influnceMatrix[i][j-5+a] +=-1;
//						        						}
//						        						else if(!MyData.influnceMatrix[i][j-5+a].equals(100) && !MyData.influnceMatrix[i][j-5+a].equals(10) && !MyData.influnceMatrix[i][j-5+a].equals(-10){
//						        							MyData.influnceMatrix[i][j-5+a] +=-1;
//						        						}	
//						        								
//						        								
//						        						MyData.influnceMatrix[i-5+a][j] +=-1;
//						        						MyData.influnceMatrix[i-5+a][j-5+a] +=-1;
//						        						MyData.influnceMatrix[i+5-a][j-5+a] +=-1;
//						        					}
>>>>>>> 5716d12be234baec9b315eae61494190df08f3bd
						        					if(whiteTurnSecond) {
						        						MyData newData = new MyData(i, j, currentColor);
						        						MyData.clickedPoint.add(newData);
						        						newData.getInformation(gameMatrix);
						        						MyData.pointsTree.put(setHashKey(i, j), newData);
						        						whiteTurnFirst=false;
						        						blackTurnFirst = true;
						        						for(int k = 0; k < 2; k++) {
						        							GameLogic nextStep = new GameLogic();
						        							int p = (int) (Math.random() * 19);
							        						int q = (int) (Math.random() * 19);
							        						MyData data = new MyData(p, q, Color.BLACK);
							        						MyData.clickedPoint.add(data);
							        						whiteTurnSecond = false;
						        						}
						        					}
						        					else {
						        						MyData newData = new MyData(i, j, currentColor);
						        						MyData.clickedPoint.add(newData);
						        						newData.getInformation(gameMatrix);
								        				MyData.pointsTree.put(setHashKey(i, j), newData);
						        						whiteTurnSecond = true;
						        					}
						        				}
					        				}
					        				else {	// We are second
					        					x = i;
					        					y = j;
					        					currentColor = Color.BLACK;
					        					gameMatrix[i][j] = 1;
					        					if(blackTurnSecond) {
					        						MyData newData = new MyData(i, j, currentColor);
					        						MyData.clickedPoint.add(newData);
					        						newData.getInformation(gameMatrix);
					        						blackTurnSecond = false;
					        						for(int k = 0; k < 2; k++) {
					        							int p = (int) (Math.random() * 19);
						        						int q = (int) (Math.random() * 19);
						        						MyData data = new MyData(p, q, Color.WHITE);
						        						MyData.clickedPoint.add(data);
					        						}
					        						
					        					}
					        					else {
					        						
					        						MyData newData = new MyData(i, j, currentColor);
					        						MyData.clickedPoint.add(newData);
					        						newData.getInformation(gameMatrix);
					        						blackTurnSecond = true;
					        						whiteTurnSecond = false;
					        						
					        					}
					        				}
					        			}
					        			repaint();
					        			int result = -1;
				        				try {
				        					result = checkFinishGo(x,y);
				        				}catch (Exception e1) {
				        				}
				        				System.out.println(result);
				        				if(result==1) {
				        					try {
					        					AudioInputStream audio = AudioSystem.getAudioInputStream(new File("./assets/MP_5 Sec_Crowd_Cheer.wav"));
					        					Clip clip = AudioSystem.getClip();
					        					clip.stop();
					        					clip.open(audio);
					        					clip.start();
					        				
					        				} catch(Exception e1) {
					        					e1.printStackTrace();
					        				}
				        					blackWin= true;
				        				}
				        				else if (result==2) {
				        					try {
					        					AudioInputStream audio = AudioSystem.getAudioInputStream(new File("./assets/MP_5 Sec_Crowd_Cheer.wav"));
					        					Clip clip = AudioSystem.getClip();
					        					clip.stop();
					        					clip.open(audio);
					        					clip.start();
					        				
					        				} catch(Exception e1) {
					        					e1.printStackTrace();
					        				}
				        					whiteWin= true;
				        				}
					        		}
				        		}
				        		else {
				        		}
			        		}
			        		else {
			        			if(blockCount == blockNumber) {
		        					isBlockFilled = true;
		        					currentColor = Color.BLACK;
		        					
		        					return;
		        				}
			        			if(usedEllipse[i][j] == null || !usedEllipse[i][j].contains(e.getPoint())) {
				        			if(ellipse[i][j].contains(e.getPoint())) {

				        				System.out.println(i+" "+j);
				        				gameMatrix[i][j] = 3;
				        				MyData.influnceMatrix[i][j] = 100;
				        				usedEllipse[i][j] = new Ellipse2D.Double(i*30+20, j*30+20,20, 20);
				        				MyData newData = new MyData(i, j, currentColor);
				        				
				        				MyData.clickedPoint.add(newData);
				        				blockCount++;
				        				
				        				repaint();
//				        				newData.getInformation(gameMatrix);
				        			}
			        			}
			        		}
			        	}
			        }
				}
				else {
					System.out.println("Press start");
				}
				for(int i=0; i<=18; i++) { //print MyData.influnceMatrix[i][j] Output
		        	for(int j=0; j<=18; j++) {
		        		System.out.print(MyData.influnceMatrix[j][i] + " | ");
		        	}
		        	System.out.println();
				}
			}
			

			@Override
			public void mousePressed(MouseEvent e) {
				if(start) {
					for(int i=0; i<=18; i++) {
			        	for(int j=0; j<=18; j++) {
			        		if(isBlockFilled) {
			        			if(usedEllipse[i][j] == null || !usedEllipse[i][j].contains(e.getPoint())) {
				        			if(ellipse[i][j].contains(e.getPoint())) {
				        				if(whiteTurnFirst) {
				        					x = i;
				        					y = j;
				        				}
				        				if(whiteTurnFirst){
				        					x = i;
				        					y = j;
				        				}
				        			}
			        			}
			        		}
			        	}
					}
				}       			
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
	}
	
	@Override
	public void paintComponent(Graphics g){
	    super.paintComponent(g);
	    Graphics2D g2d = (Graphics2D) g.create();
	    Graphics2D g2Point = (Graphics2D) g.create();
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        for(int i=0; i<=18; i++) {
        	for(int j=0; j<=18; j++) {
        		ellipse[i][j] = new Ellipse2D.Double(i*30+20, j*30+20,20, 20);
        	}
            g2d.drawLine(i*30+30, 30, i*30+30, 570);
            g2d.drawLine(30, i*30+30, 570, i*30+30);
        }
        for(int i = 120; i <= 480; i += 180) {
        	for(int j = 120; j <= 480; j += 180) {
        		g2d.drawOval(i-2, j-2, 4, 4);
        		g2d.fillOval(i-3, j-3, 6, 6);
        	}
        }
        if(!MyData.clickedPoint.isEmpty()) {
        	for(MyData d: MyData.clickedPoint) {
        		g2Point.setColor(d.getColor());
        		g2Point.fillOval(d.getX()*30 +20, d.getY()*30 +20, 20, 20);
        	}
        	
        }
	}
	
	public int checkFinishGo(int x, int y) {

		if(checkHorizontal(x, y, gameMatrix[x][y]) >= 6) {
			return gameMatrix[x][y];
		}
		else if(checkVertical(x, y, gameMatrix[x][y]) >= 6) {
			return gameMatrix[x][y];
		}
		else if(checkLRDiagonal(x, y, gameMatrix[x][y]) >= 6) {
			return gameMatrix[x][y];
		}
		else if(checkRLDiagonal(x, y, gameMatrix[x][y]) >= 6) {
			return gameMatrix[x][y];
		}

		return 0;
	}

	private int checkHorizontal(int row, int col, int rock) {
		try {
			if (gameMatrix[row][col] != rock)
				return 0;
			else {
				return checkLeft(row - 1, col, rock) + 1 + checkRight(row + 1, col, rock);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}
	
	private int checkLeft(int row, int col, int rock) {
		try {
			if (gameMatrix[row][col] != rock)
				return 0;
			else {
				return 1 + checkLeft(row - 1, col, rock);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}
	
	private int checkRight(int row, int col, int rock) {
		try {
			if (gameMatrix[row][col] != rock)
				return 0;
			else {
				return 1 + checkRight(row + 1, col, rock);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}
	
	private int checkVertical(int row, int col, int rock) {
		try {
			if (gameMatrix[row][col] != rock)
				return 0;
			else {
				return checkUp(row, col - 1, rock) + 1 + checkDown(row, col + 1, rock);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}
	
	private int checkUp(int row, int col, int rock) {
		try {
			if (gameMatrix[row][col] != rock)
				return 0;
			else {
				return 1 + checkUp(row, col - 1, rock);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}
	
	private int checkDown(int row, int col, int rock) {
		try {
			if (gameMatrix[row][col] != rock)
				return 0;
			else {
				return 1 + checkDown(row, col + 1, rock);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}
	
	private int checkLRDiagonal(int row, int col, int rock) {
		try {
			if (gameMatrix[row][col] != rock)
				return 0;
			else {
				return checkLRDiagonalUp(row - 1, col - 1, rock) + 1 + checkLRDiagonalDown(row + 1, col + 1, rock);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}
	
	private int checkLRDiagonalUp(int row, int col, int rock) {
		try {
			if (gameMatrix[row][col] != rock)
				return 0;
			else {
				return 1 + checkLRDiagonalUp(row - 1, col - 1, rock);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}
	
	private int checkLRDiagonalDown(int row, int col, int rock) {
		try {
			if (gameMatrix[row][col] != rock)
				return 0;
			else {
				return 1 + checkLRDiagonalDown(row + 1, col + 1, rock);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}

	private int checkRLDiagonal(int row, int col, int rock) {
		try {
			if (gameMatrix[row][col] != rock)
				return 0;
			else {
				return checkRLDiagonalUp(row + 1, col - 1, rock) + 1 + checkRLDiagonalDown(row - 1, col + 1, rock);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}
	
	private int checkRLDiagonalUp(int row, int col, int rock) {
		try {
			if (gameMatrix[row][col] != rock)
				return 0;
			else {
				return 1 + checkRLDiagonalUp(row + 1, col - 1, rock);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}
	
	private int checkRLDiagonalDown(int row, int col, int rock) {
		try {
			if (gameMatrix[row][col] != rock)
				return 0;
			else {
				return 1 + checkRLDiagonalDown(row - 1, col + 1, rock);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return 0;
		}
	}

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public int getBlockNumber() {
		return blockNumber;
	}

	public void setBlockNumber(int blockNumber) {
		this.blockNumber = blockNumber;
	}

	public Ellipse2D.Double[][] getEllipse() {
		return ellipse;
	}

	public void setEllipse(Ellipse2D.Double[][] ellipse) {
		this.ellipse = ellipse;
	}

	public Ellipse2D.Double[][] getUsedEllipse() {
		return usedEllipse;
	}

	public void setUsedEllipse(Ellipse2D.Double[][] usedEllipse) {
		this.usedEllipse = usedEllipse;
	}

	public int[][] getGameMatrix() {
		return gameMatrix;
	}

	public boolean isAreWeFirst() {
		return areWeFirst;
	}

	public void setAreWeFirst(boolean areWeFirst) {
		this.areWeFirst = areWeFirst;
	}

	public boolean isAreWeSecond() {
		return areWeSecond;
	}

	public void setAreWeSecond(boolean areWeSecond) {
		this.areWeSecond = areWeSecond;
	}

	public void setGameMatrix(int[][] gameMatrix) {
		this.gameMatrix = gameMatrix;
	}

	public Color getCurrentColor() {
		return currentColor;
	}

	public void setCurrentColor(Color currentColor) {
		this.currentColor = currentColor;
	}

	public boolean isFirstClick() {
		return firstClick;
	}

	public void setFirstClick(boolean firstClick) {
		this.firstClick = firstClick;
	}

	public boolean isWhiteTurnFirst() {
		return whiteTurnFirst;
	}

	public void setWhiteTurnFirst(boolean whiteTurnFirst) {
		this.whiteTurnFirst = whiteTurnFirst;
	}

	public boolean isWhiteTurnSecond() {
		return whiteTurnSecond;
	}

	public void setWhiteTurnSecond(boolean whiteTurnSecond) {
		this.whiteTurnSecond = whiteTurnSecond;
	}

	public boolean isBlackTurnFirst() {
		return blackTurnFirst;
	}

	public void setBlackTurnFirst(boolean blackTurnFirst) {
		this.blackTurnFirst = blackTurnFirst;
	}

	public boolean isBlackTurnSecond() {
		return blackTurnSecond;
	}

	public void setBlackTurnSecond(boolean blackTurnSecond) {
		this.blackTurnSecond = blackTurnSecond;
	}

	public boolean isBlockFilled() {
		return isBlockFilled;
	}

	public void setBlockFilled(boolean isBlockFilled) {
		this.isBlockFilled = isBlockFilled;
	}

	public int getBlockCount() {
		return blockCount;
	}

	public void setBlockCount(int blockCount) {
		this.blockCount = blockCount;
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

	public boolean isWhiteWin() {
		return whiteWin;
	}

	public void setWhiteWin(boolean whiteWin) {
		this.whiteWin = whiteWin;
	}

	public boolean isBlackWin() {
		return blackWin;
	}

	public void setBlackWin(boolean blackWin) {
		this.blackWin = blackWin;
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
