package com.corejava.tictactoe;

public enum GameState{
    IN_PROGRESS("The game is in progress."), PLAYER1_WINS("Player 1 wins!"), 
                            PLAYER2_WINS("Player 2 wins!"), TIE("It is a tie!");
    
    private String message;
    
    private GameState(String message) {
        this.message = message;
    }
    
    public String getMessage() {
        return message;
    }
    
};