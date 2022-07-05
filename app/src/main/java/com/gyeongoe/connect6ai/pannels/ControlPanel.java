package com.gyeongoe.connect6ai.pannels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private RoundButton firstButton = new RoundButton("Fir", Color.GRAY);
	private RoundButton secondButton = new RoundButton("Sec", Color.GRAY);
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
		firstButton.setBounds(15, 285, 100, 50);
		firstButton.addActionListener(listener);
		
		secondButton.setFont(normalFont);
		secondButton.setBounds(120, 285, 100, 50);
		secondButton.addActionListener(listener);
		
		this.add(restartButton);
		this.add(reDoButton);
		this.add(startButton);
		this.add(firstButton);
		this.add(secondButton);
		this.add(numberOfDisabledPointTextField);
	}
	
	ActionListener listener = new ActionListener() {
		@Override
        public void actionPerformed(ActionEvent e) {
			String input = e.getActionCommand();
			if(input.equals("Sec")) {
				firstCheck = true;
				firstButton.setColor("Fir", new Color(61,205,91));
				secondCheck = false; 
				secondButton.setColor("Sec", Color.GRAY);
			}
			
			else if(input.equals("Sec")) {
				secondCheck = true;  
				secondButton.setColor("Sec", new Color(61,205,91));
				firstCheck = false;
				firstButton.setColor("Fir", Color.GRAY);
			}
	    }
	};
	
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
	
	public RoundButton getFirstButton() {
		return firstButton;
	}

	public void setgetFirstButton(RoundButton firstButton) {
		this.firstButton = firstButton;
	}
	
	
	public RoundButton getSecondButton() {
		return secondButton;
	}

	public void setSecondButton(RoundButton secondButton) {
		this.secondButton = secondButton;
	}
}
