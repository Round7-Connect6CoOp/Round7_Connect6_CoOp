package com.gyeongoe.connect6ai.pannels;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import com.gyeongoe.connect6ai.MainFrame;
import com.gyeongoe.connect6ai.buttons.RoundButton;

public class ControlPanel extends JPanel {
	private JTextField numberOfDisabledPointTextField = new JTextField();
	private int numberOfDisabledPoint;
	private RoundButton startButton = new RoundButton("Start", Color.GRAY);
	private RoundButton reDoButton = new RoundButton("Undo");
	private RoundButton restartButton = new RoundButton("New Game");
	private JToggleButton firstButton = new JToggleButton("Fir");
	private JToggleButton secondButton = new JToggleButton("Sec");
	private ButtonGroup group;
	private Container container;
	private Boolean firstCheck = false;
	private Boolean secondCheck = false;
	
	Font normalFont = new Font("",Font.BOLD, 30);
	public ControlPanel() {
		setBounds(700, 5, 250, 400);
		setLayout(null);
		createPanel();
	}
	
	private void createPanel() {
		numberOfDisabledPointTextField.setBounds(15, 15, 210, 50);
		numberOfDisabledPointTextField.setFont(normalFont);
		numberOfDisabledPointTextField.addCaretListener(new CaretListener() {
			@Override
			public void caretUpdate(CaretEvent e) {
				try {
					numberOfDisabledPoint = Integer.parseInt(numberOfDisabledPointTextField.getText());
					if(numberOfDisabledPoint < 0 || 5 <numberOfDisabledPoint ) {
						startButton.setColor("Start", Color.GRAY);
						startButton.setEnabled(false);
						return;
					}
					startButton.setColor("Start", new Color(61,205,91));
					startButton.setEnabled(true);
				}catch(Exception e1) {
					startButton.setColor("Start", Color.GRAY);
					startButton.setEnabled(false);
				}
			}
		});
		
		reDoButton.setFont(normalFont);
		reDoButton.setBounds(15, 80, 210, 50);
		
		restartButton.setFont(normalFont);
		restartButton.setBounds(15, 145, 210, 50);
		
		startButton.setEnabled(false);
		startButton.setFont(normalFont);
		startButton.setBounds(15, 220, 210, 50);
		
		firstButton.setFont(normalFont);
		firstButton.setBackground(Color.GRAY);
		firstButton.setForeground(Color.BLACK);
		firstButton.addItemListener(new ItemListener(){
	          @Override
	          public void itemStateChanged(ItemEvent e) {
	              if(e.getStateChange()==ItemEvent.SELECTED) {
	            	secondButton.setForeground(Color.BLACK);
	            	firstButton.setForeground(new Color(61,205,91));
	  				firstCheck = true;
	  				secondCheck = false; 
	              }              
	          }
	      });
		
		secondButton.setFont(normalFont);
		secondButton.setBackground(Color.GRAY);
		secondButton.setForeground(Color.BLACK);
		secondButton.addItemListener(new ItemListener(){
	          @Override
	          public void itemStateChanged(ItemEvent e) {
	              if(e.getStateChange()==ItemEvent.SELECTED) {
	  				firstButton.setForeground(Color.BLACK);
	            	secondButton.setForeground(new Color(61,205,91));
	  				firstCheck = false;
	  				secondCheck = true;  
	              }              
	          }
	      });
		
		group = new ButtonGroup();
	    container = new Container();
		group.add(firstButton);
		group.add(secondButton);
	    container.add(firstButton);
	    container.add(secondButton);
	    container.setBounds(15, 285, 210, 50);
	    container.setLayout(new FlowLayout());
	    this.add(container);
		this.add(restartButton);
		this.add(reDoButton);
		this.add(startButton);
		this.add(numberOfDisabledPointTextField);
	}
	
	public int getNumberOfDisabledPoint() {
		return numberOfDisabledPoint;
	}

	public void setNumberOfDisabledPoint(int numberOfDisabledPoint) {
		this.numberOfDisabledPoint = numberOfDisabledPoint;
	}

	public RoundButton getStartButton() {
		return startButton;
	}

	public void setStartButton(RoundButton startButton) {
		this.startButton = startButton;
	}

	public RoundButton getReDoButton() {
		return reDoButton;
	}

	public void setReDoButton(RoundButton reDoButton) {
		this.reDoButton = reDoButton;
	}

	public RoundButton getRestartButton() {
		return restartButton;
	}

	public void setRestartButton(RoundButton restartButton) {
		this.restartButton = restartButton;
	}

	public JTextField getNumberOfDisabledPointTextField() {
		return numberOfDisabledPointTextField;
	}

	public void setNumberOfDisabledPointTextField(JTextField numberOfDisabledPointTextField) {
		this.numberOfDisabledPointTextField = numberOfDisabledPointTextField;
	}
	
	public JToggleButton getFirstButton() {
		return firstButton;
	}

	public void setgetFirstButton(JToggleButton firstButton) {
		this.firstButton = firstButton;
	}
	
	
	public JToggleButton getSecondButton() {
		return secondButton;
	}

	public void setSecondButton(JToggleButton secondButton) {
		this.secondButton = secondButton;
	}

	public Boolean getFirstCheck() {
		return firstCheck;
	}

	public void setFirstCheck(Boolean firstCheck) {
		this.firstCheck = firstCheck;
	}

	public Boolean getSecondCheck() {
		return secondCheck;
	}

	public void setSecondCheck(Boolean secondCheck) {
		this.secondCheck = secondCheck;
	}
}
