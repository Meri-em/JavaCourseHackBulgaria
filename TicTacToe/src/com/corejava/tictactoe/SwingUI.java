package com.corejava.tictactoe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


import com.corejava.tictactoe.Player.PlayerType;
import com.corejava.tictactoe.UserInput.InputType;

public class SwingUI extends JFrame{
    private static final long serialVersionUID = -2506747408415600698L;
    private GameLogic gameLogic;
    private static GameLogic initialGameLogic;
    private JPanel optionsPanel = new JPanel();
    private JPanel boardPanel = new JPanel(new GridLayout(GameLogic.BOARD_LENGTH, GameLogic.BOARD_LENGTH));
    private OptionPress listener = new OptionPress();
    
    private JButton[] buttons = new JButton[9];
    private JButton undoButton = new JButton("Undo");
    private JButton redoButton = new JButton("Redo");
    private JButton saveButton = new JButton("Save");
    private JButton loadButton = new JButton("Load");
    private JButton newGameButton = new JButton("New game");
    private JTextField messageField = new JTextField("");
    
    public SwingUI(GameLogic gameLogic) {
        this.gameLogic = new GameLogic(gameLogic);
        initialGameLogic = new GameLogic(gameLogic);
        setSize(400, 400);
        setTitle("Tic tac Toe");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        undoButton.addActionListener(listener);
        redoButton.addActionListener(listener);
        saveButton.addActionListener(listener);
        loadButton.addActionListener(listener);
        newGameButton.addActionListener(listener);
        optionsPanel.add(undoButton);
        optionsPanel.add(redoButton);
        optionsPanel.add(saveButton);
        optionsPanel.add(loadButton);
        optionsPanel.add(newGameButton);
        messageField.setEditable(false);
        messageField.setFont(new Font("Arial", Font.PLAIN, 18));
        
        for (int k = 0; k < buttons.length; k++) {
            buttons[k] = new JButton("");
            buttons[k].setFont(new Font("Arial", Font.PLAIN, 45));
            buttons[k].setBackground(Color.WHITE);
            buttons[k].addActionListener(new ButtonPress(k));
            boardPanel.add(buttons[k]);
        }
        add(optionsPanel, BorderLayout.NORTH);
        add(boardPanel, BorderLayout.CENTER);
        add(messageField, BorderLayout.SOUTH);
        optionsCheck();
    }
       
    
    private class ButtonPress implements ActionListener {
        private int i, j;
        
        ButtonPress(int k) {
            i = k / GameLogic.BOARD_LENGTH;
            j = k % GameLogic.BOARD_LENGTH;
        }
        
        public void actionPerformed(ActionEvent e) {
            messageField.setText("");
            
            String message = gameLogic.playHuman(new UserInput(i, j));
            if (message != null) {
                messageField.setText(message);
            }else {
                AICheck();
            }
            updateBoard();
            endGameCheck();
            optionsCheck();
        }
    }
    
    private class OptionPress implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            messageField.setText("");
            UserInput userInput;
            if (e.getSource() == undoButton){
                userInput = new UserInput(InputType.UNDO);
            }  
            else if (e.getSource() == redoButton) {
                userInput = new UserInput(InputType.REDO);
            }
            else if (e.getSource() == saveButton) {
                userInput = new UserInput(InputType.SAVE);
            }
            else { 
                if (e.getSource() == loadButton) {
                    userInput = new UserInput(InputType.LOAD);
                }
                else {
                    userInput = new UserInput(InputType.NEWGAME);
                }
                if (!gameLogic.getState().equals(GameState.IN_PROGRESS)) {
                    gameLogic = new GameLogic(initialGameLogic);
                    enableButtons(true);
                    saveButton.setEnabled(true);
                }
            }
            
            String message = gameLogic.playHuman(userInput);
            updateBoard();
            if (message != null) {
                if(e.getSource() == saveButton || e.getSource() == loadButton) {
                    messageField.setText(message);
                }
            }
            else {
                AICheck();
                updateBoard();
            }
            optionsCheck();
        }
    }
    
    private void AICheck() {
        if (gameLogic.getState().equals(GameState.IN_PROGRESS) && gameLogic.getCurrentPlayer().getPlayerType().equals(PlayerType.AI)) {
            gameLogic.playAI();
        }
    }
    
    private void endGameCheck() {
        if (!gameLogic.getState().equals(GameState.IN_PROGRESS)) {
            messageField.setText(gameLogic.getState().getMessage());
            enableButtons(false);
            undoButton.setEnabled(false);
            redoButton.setEnabled(false);
            saveButton.setEnabled(false);
        }
    }
    
    private void updateBoard() {
        char[][] board = gameLogic.getBoard(); 
        for (int k = 0; k < buttons.length; k++) {
            char element = board[k / GameLogic.BOARD_LENGTH][k % GameLogic.BOARD_LENGTH];
            if (element == Symbols.X) {
                buttons[k].setForeground(Color.BLUE);
            }else if (element == Symbols.O) {
                buttons[k].setForeground(new Color(248, 18, 150));
            }
            buttons[k].setText(element + "");
        }
    }
    
    private void optionsCheck() {
        if (!gameLogic.canUndo()) {
            undoButton.setEnabled(false);
        }else {
            undoButton.setEnabled(true);
        }
        if (!gameLogic.canRedo()) {
            redoButton.setEnabled(false);
        }else {
            redoButton.setEnabled(true);
        }
        if (!gameLogic.canLoad()) {
            loadButton.setEnabled(false);
        }else {
            loadButton.setEnabled(true);
        }
    }
    
    private void enableButtons(boolean toEnable) {
        for (int k = 0; k < buttons.length; k++) {
            buttons[k].setEnabled(toEnable);
        }
    }
}
